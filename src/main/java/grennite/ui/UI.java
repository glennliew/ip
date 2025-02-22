package grennite.ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import grennite.task.Task;

public class UI {

    private Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from the standard input.
     * 
     * @return the command as a string input by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     * The message is displayed when the program starts.
     */
    public String welcomeMessage() {
        return "__________________________________________\n"
                + " Hello! I'm Grennite\n" + " What can I do for you today?\n"
                + "__________________________________________";
    }

    /**
     * Displays a goodbye message to the user.
     * The message is displayed when the user types "bye" to exit the program.
     */
    public String exitMessage() {
        return "__________________________________________\n"
                + " Bye! Hope to see you again soon!\n"
                + "__________________________________________";

    }

    /**
     * Displays an error message to the user.
     * The message is displayed when a command cannot be parsed or
     * is invalid.
     * 
     * @param message the error message
     */
    public String errorMessage(String message) {
        return "__________________________________________\n"
                + "Error: "
                + message
                + "\n__________________________________________";
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public String showTaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Task list should not be null";

        StringBuilder output = new StringBuilder();
        if (tasks.isEmpty()) {
            output.append("Hmm... Your task list is empty. Ready to add something?");
        } else {
            output.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                output.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        return output.toString().trim();
    }

    /**
     * Displays a message to the user indicating that a task has been added
     * to the task list, along with the updated number of tasks.
     *
     * @param task      the task that was added
     * @param taskCount the current number of tasks in the list after addition
     */
    public String addTaskMessage(Task task, int taskCount) {
        return "__________________________________________\n" +
                " Adding task:\n"
                + " "
                + task
                + "\n"
                + " You currently have "
                + taskCount
                + " tasks.\n" + "__________________________________________";
    }

    /**
     * Displays a message to the user indicating that a task has been removed
     * from the task list, along with the updated number of tasks.
     *
     * @param task      the task that was removed
     * @param taskCount the current number of tasks in the list after removal
     */
    public String deleteTaskMessage(Task task, int taskCount) {
        return "__________________________________________\n"
                + " Removing task:" + task + " You currently have "
                + taskCount
                + " tasks.\n"
                + "__________________________________________";
    }

    /**
     * Displays a message to the user indicating that a task has been marked as
     * done.
     * 
     * @param task the task that was marked as done
     */
    public String markTaskMessage(Task task, Boolean isDone) {
        if (isDone == true) {
            return "__________________________________________\n"
                    + " Yay! You just completed:" + task
                    + "\n__________________________________________";
        } else {
            return "__________________________________________\n"
                    + "You have yet to complete complete:" + task
                    + "\n__________________________________________";
        }
    }

    /**
     * Displays a message to the user indicating that a task has been unmarked as
     * done.
     *
     * @param task the task that was unmarked as done
     */
    public String unmarkTaskMessage(Task task) {
        return "__________________________________________\n"
                + "You have yet to complete complete:" + task +
                "\n__________________________________________";
    }

    /**
     * Displays tasks that match the given keyword.
     *
     * @param matchingTasks The list of tasks that match the keyword.
     * @param keyword       The keyword used for the search.
     */
    public String showMatchingTasks(ArrayList<Task> matchingTasks, String keyword) {
        assert matchingTasks != null : "Matching tasks list should not be null";
        assert keyword != null && !keyword.isBlank() : "Search keyword should not be null or empty";

        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks for \"").append(keyword).append("\":\n");

        if (matchingTasks.isEmpty()) {
            output.append("  No matching tasks found.");
        } else {
            for (int i = 0; i < matchingTasks.size(); i++) {
                output.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
            }
        }
        return output.toString().trim();
    }

    /**
     * Displays a message to the user indicating that a task that is already in the task list
     * is being attempted to be added again.
     *
     * @param task the task that is being attempted to be added
     */
        public String duplicateTaskMessage(Task task) {
        return "__________________________________________\n"
                + "It seems this task is already in your list!\n"
                + "Duplicate task: " + task
                + "\n__________________________________________";
    }
}