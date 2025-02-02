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
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a todo task with the given description to the task list.
     * 
     * @param input the user input, which should be in the format "todo [description]"
     * @param ui the UI object to use for displaying error messages
     * @throws GrenniteException if the input is invalid
     */
    public void addTodo(String input, UI ui) throws GrenniteException {
        try {
            String description = input.substring(5).trim();
            if (description.isEmpty()) {
                throw new GrenniteException("The description of a todo cannot be empty!");
            }
            Task todo = new Todo(description);
            tasks.add(todo);
            ui.addTaskMessage(todo, tasks.size());
        } catch (StringIndexOutOfBoundsException e) {
            throw new GrenniteException("Invalid input! Use: todo [description]");
        }
    }

    /**
     * Adds a deadline task with the given description and deadline to the task list.
     * 
     * @param input the user input, which should be in the format "deadline [description] /by [yyyy-MM-dd HHmm]"
     * @param ui the UI object to use for displaying error messages
     * @throws GrenniteException if the input is invalid
     */
    public void addDeadline(String input, UI ui) throws GrenniteException {
        try {
            String description = input.substring(9).trim();
            String[] parts = description.split(" /by ");
            if (parts.length < 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                throw new GrenniteException(
                        "Invalid deadline format. Use: deadline [description] /by [yyyy-MM-dd HHmm]");
            }

            try {
                LocalDateTime deadline = LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                Task deadlineTask = new Deadline(parts[0], parts[1]);
                tasks.add(deadlineTask);
                ui.addTaskMessage(deadlineTask, tasks.size());
            } catch (DateTimeParseException e) {
                throw new GrenniteException("Invalid format! Use: deadline [description] /by [yyyy-MM-dd HHmm]");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new GrenniteException("Invalid input! Use: deadline [description] /by [date]");
        }
    }

    /**
     * Adds an event task with the given description, date, start time, and end time to the task list.
     * 
     * @param input the user input, which should be in the format "event [description] /on [yyyy-MM-dd] /from [HHmm] /to [HHmm]"
     * @param ui the UI object to use for displaying error messages
     * @throws GrenniteException if the input is invalid
     */
    public void addEvent(String input, UI ui) throws GrenniteException {
        try {
            String description = input.substring(6).trim();
            String[] parts = description.split(" /on | /from | /to ");
            if (parts.length < 4) {
                throw new GrenniteException("Invalid event format. " +
                        "Use: event [description] /on [yyyy-MM-dd] /from [HHmm] /to [HHmm]");
            }

            // Validate date format
            LocalDate date;
            try {
                date = LocalDate.parse(parts[1], DATE_FORMAT);
            } catch (DateTimeParseException e) {
                throw new GrenniteException("Invalid date format! Use: yyyy-MM-dd (e.g., 2025-02-01)");
            }

            // Validate start and end times
            LocalTime fromTime, toTime;
            try {
                fromTime = LocalTime.parse(parts[2], TIME_FORMAT);
                toTime = LocalTime.parse(parts[3], TIME_FORMAT);
            } catch (DateTimeParseException e) {
                throw new GrenniteException("Invalid time format! Use: /on [yyyy-MM-dd] /from [HHmm] /to [HHmm]");
            }

            // Ensure start time is before end time
            if (fromTime.isAfter(toTime)) {
                throw new GrenniteException("Invalid time range! Start time must be before end time.");
            }

            Task event = new Event(parts[0], date.toString(), fromTime.toString(), toTime.toString());
            tasks.add(event);
            ui.addTaskMessage(event, tasks.size());

        } catch (StringIndexOutOfBoundsException e) {
            throw new GrenniteException(
                    "Invalid input! Use: event [description] /on [yyyy-MM-dd] /from [HHmm] /to [HHmm]");
        }
    }

/**
 * Deletes the task at the specified index from the task list.
 * 
 * @param index the index of the task to be deleted
 * @param ui the UI object used to display a message about the deleted task
 * @throws GrenniteException if the index is out of range
 */

    public void deleteTask(int index, UI ui) throws GrenniteException {
        if (index < 0 || index >= tasks.size()) {
            throw new GrenniteException("Task number out of range!");
        }
        Task removedTask = tasks.remove(index);
        ui.deleteTaskMessage(removedTask, tasks.size());
    }

    /**
     * Marks the task at the specified index as done or not done.
     * 
     * @param index the index of the task to be marked
     * @param isDone true to mark the task as done, false to mark the task as not done
     * @param ui the UI object used to display a message about the task being marked
     * @throws GrenniteException if the index is out of range
     */
    public void markTask(int index, boolean isDone, UI ui) throws GrenniteException {
        if (index < 0 || index >= tasks.size()) {
            throw new GrenniteException("Task number out of range!");
        }
        Task task = tasks.get(index);
        if (isDone) {
            task.markAsDone();
            ui.markTaskMessage(task);
        } else {
            task.markAsNotDone();
            ui.unmarkTaskMessage(task);
        }
    }

    public void listTasks(UI ui) {
        ui.taskListMessage(this);
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
}