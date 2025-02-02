import java.util.Scanner;

public class UI {

    private Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void welcomeMessage() {
        System.out.println("__________________________________________");
        System.out.println(" Hi! I'm Grennite, your personal task manager!");
        System.out.println(" How can I help you?");
        System.out.println("__________________________________________");
    }

    public void exitMessage() {
        System.out.println("__________________________________________");
        System.out.println(" Bye! See you again soon!");
        System.out.println("__________________________________________");
    }

    public void errorMessage(String message) {
        System.out.println("__________________________________________");
        System.out.println(" Error: " + message);
        System.out.println("__________________________________________");
    }

    public void taskListMessage(TaskList tasks) {
        System.out.println("__________________________________________");
        if (tasks.isEmpty()) {
            System.out.println(" You're all caught!");
        } else {
            System.out.println(" Here's your task list:");
            tasks.printTasks();
        }
        System.out.println("__________________________________________");
    }

    public void addTaskMessage(Task task, int taskCount) {
        System.out.println("__________________________________________");
        System.out.println(" Adding task:" + task);
        System.out.println(" You currently have " + taskCount + " tasks.");
        System.out.println("__________________________________________");
    }

    public void deleteTaskMessage(Task task, int taskCount) {
        System.out.println("__________________________________________");
        System.out.println(" Removing task:" + task);
        System.out.println(" You currently have " + taskCount + " tasks.");
        System.out.println("__________________________________________");
    }

    public void markTaskMessage(Task task) {
        System.out.println("__________________________________________");
        System.out.println(" Yay! You just completed:" + task);
        System.out.println("__________________________________________");
    }

    public void unmarkTaskMessage(Task task) {
        System.out.println("__________________________________________");
        System.out.println(" You have yet to complete complete:" + task);
        System.out.println("__________________________________________");
    }
}