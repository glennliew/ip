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
     * Displays a message to the user indicating the tasks found by the search.
     *
     * @param tasks the list of tasks found
     * @return the message to be displayed
     */
    public String findTaskMessage(List<Task> tasks) {
        StringBuilder message = new StringBuilder("__________________________________________\n");
        if (tasks.isEmpty()) {
            message.append(" No matching tasks found!\n");
        } else {
            message.append(" Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                message.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
            }
        }
        message.append("__________________________________________");
        return message.toString();
    }

    public String duplicateTaskMessage(Task task) {
        return "__________________________________________\n"
                + "It seems this task is already in your list!\n"
                + "Duplicate task: " + task
                + "\n__________________________________________";
    }
}