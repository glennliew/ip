package grennite.tasklist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.IOException;

import grennite.exception.GrenniteException;
import grennite.ui.UI;
import grennite.task.Deadline;
import grennite.task.Event;
import grennite.task.Task;
import grennite.task.Todo;
import grennite.storage.Storage;

public class TaskList {

    private ArrayList<Task> tasks;
    private UI ui;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");

    public TaskList(Storage storage, UI ui) throws GrenniteException, IOException {
        this.ui = ui;
        this.tasks = storage.loadTasks();
    }

    public String addTodo(String input) throws GrenniteException {
        assert input != null : "Input for Todo task should not be null";

        if (input.length() <= 5) {
            throw new GrenniteException("Oops! The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        Task task = new Todo(description);
        tasks.add(task);
        return ui.addTaskMessage(task, tasks.size());
    }

    public String addDeadline(String input) throws GrenniteException {
        assert input != null : "Input for Deadline task should not be null";

        if (!input.contains("/by")) {
            throw new GrenniteException("Oops! Deadlines need a description and a '/by' date.");
        }

        String[] parts = input.substring(9).split(" /by ");
        if (parts.length < 2) {
            throw new GrenniteException("Invalid deadline format. Use: deadline [description] /by [yyyy-MM-dd HHmm]");
        }

        try {
            LocalDateTime deadlineDateTime = LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            Task task = new Deadline(parts[0].trim(), deadlineDateTime.toString());
            tasks.add(task);
            return ui.addTaskMessage(task, tasks.size());
        } catch (DateTimeParseException e) {
            throw new GrenniteException("Invalid date-time format! Use: yyyy-MM-dd HHmm (e.g., 2025-02-14 1430)");
        }
    }

    public String addEvent(String input) throws GrenniteException {
        try {
            String description = input.substring(6).trim();
            String[] parts = description.split(" /on | /from | /to ");
            if (parts.length < 4) {
                throw new GrenniteException("Invalid event format. " +
                        "Use: event [description] /on [yyyy-MM-dd] /from [HHmm] /to [HHmm]");
            }

            String eventDescription = parts[0].trim();
            String dateString = parts[1].trim();
            String fromTimeString = parts[2].trim();
            String toTimeString = parts[3].trim();

            LocalDate date;
            try {
                date = LocalDate.parse(dateString, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                throw new GrenniteException("Invalid date format! Use: yyyy-MM-dd (e.g., 2025-02-01)");
            }

            LocalTime fromTime, toTime;
            try {
                fromTime = LocalTime.parse(fromTimeString, TIME_FORMAT);
                toTime = LocalTime.parse(toTimeString, TIME_FORMAT);
            } catch (DateTimeParseException e) {
                throw new GrenniteException("Invalid time format! Use: HHmm (e.g., 0930)");
            }

            if (fromTime.isAfter(toTime)) {
                throw new GrenniteException("Invalid time range! Start time must be before end time.");
            }

            Task event = new Event(eventDescription, date.toString(), fromTime.toString(), toTime.toString());
            tasks.add(event);
            return ui.addTaskMessage(event, tasks.size());

        } catch (StringIndexOutOfBoundsException e) {
            throw new GrenniteException(
                    "Invalid input! Use: event [description] /on [yyyy-MM-dd] /from [HHmm] /to [HHmm]");
        }
    }

    public String deleteTask(String input) throws GrenniteException {
        int taskNumber = parseTaskNumber(input);
        Task removedTask = tasks.remove(taskNumber);
        return ui.deleteTaskMessage(removedTask, tasks.size());
    }

    public String markTask(String input, boolean isDone) throws GrenniteException {
        int taskNumber = parseTaskNumber(input);

        Task task = tasks.get(taskNumber);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
        return ui.markTaskMessage(task, isDone);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     *
     * @param input The user input containing the keyword to search for.
     */
    public String findTasksByKeyword(String input) {
        try {
            String keyword = input.split(" ", 2)[1].trim();
            ArrayList<Task> matchingTasks = new ArrayList<>();

            for (Task task : tasks) {
                if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    matchingTasks.add(task);
                }
            }
            return ui.showMatchingTasks(matchingTasks, keyword);
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.errorMessage("Oops! Please enter a keyword after 'find'. Example: find book");
        }
    }

    private int parseTaskNumber(String input) throws GrenniteException {
        assert input != null : "Task number input should not be null";

        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new GrenniteException("Oops! Please provide a valid task number.");
        }

        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new GrenniteException("Oops! That task number doesn't exist. Please check your list.");
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new GrenniteException("Oops! Please provide a valid task number.");
        }
    }
}
