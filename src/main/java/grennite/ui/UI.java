package grennite.ui;

import java.util.Scanner;

import grennite.tasklist.TaskList;
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
    public void welcomeMessage() {
        System.out.println("__________________________________________");
        System.out.println(" Hi! I'm Grennite, your personal task manager!");
        System.out.println(" How can I help you?");
        System.out.println("__________________________________________");
    }

    /**
     * Displays a goodbye message to the user.
     * The message is displayed when the user types "bye" to exit the program.
     */
    public void exitMessage() {
        System.out.println("__________________________________________");
        System.out.println(" Bye! See you again soon!");
        System.out.println("__________________________________________");
    }

    /**
     * Displays an error message to the user.
     * The message is displayed when a command cannot be parsed or
     * is invalid.
     * @param message the error message
     */
    public void errorMessage(String message) {
        System.out.println("__________________________________________");
        System.out.println(" Error: " + message);
        System.out.println("__________________________________________");
    }

    /**
     * Displays a message to the user showing the current task list.
     * If the task list is empty, the message tells the user that
     * they are all caught up. Otherwise, the message shows the
     * current task list.
     * @param tasks the task list to display
     */
    public void taskListMessage(TaskList tasks) {
        System.out.println("__________________________________________");
        if (tasks.isEmpty()) {
            System.out.println(" You're all caught up!");
        } else {
            System.out.println(" Here's your task list:");
            tasks.printTasks();
        }
        System.out.println("__________________________________________");
    }

    /**
     * Displays a message to the user indicating that a task has been added
     * to the task list, along with the updated number of tasks.
     *
     * @param task the task that was added
     * @param taskCount the current number of tasks in the list after addition
     */

    public void addTaskMessage(Task task, int taskCount) {
        System.out.println("__________________________________________");
        System.out.println(" Adding task:" + task);
        System.out.println(" You currently have " + taskCount + " tasks.");
        System.out.println("__________________________________________");
    }

    /**
     * Displays a message to the user indicating that a task has been removed
     * from the task list, along with the updated number of tasks.
     *
     * @param task the task that was removed
     * @param taskCount the current number of tasks in the list after removal
     */

    public void deleteTaskMessage(Task task, int taskCount) {
        System.out.println("__________________________________________");
        System.out.println(" Removing task:" + task);
        System.out.println(" You currently have " + taskCount + " tasks.");
        System.out.println("__________________________________________");
    }

    /**
     * Displays a message to the user indicating that a task has been marked as done.
     * 
     * @param task the task that was marked as done
     */
    public void markTaskMessage(Task task) {
        System.out.println("__________________________________________");
        System.out.println(" Yay! You just completed:" + task);
        System.out.println("__________________________________________");
    }

    /**
     * Displays a message to the user indicating that a task has been unmarked as done.
     *
     * @param task the task that was unmarked as done
     */
    public void unmarkTaskMessage(Task task) {
        System.out.println("__________________________________________");
        System.out.println(" You have yet to complete complete:" + task);
        System.out.println("__________________________________________");
    }
}