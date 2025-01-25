import java.util.ArrayList;
import java.util.Scanner;

public class grennite {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Grennite");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();

            try {
                if (input.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (input.equals("list")) {
                    handleListCommand();
                } else if (input.startsWith("todo")) {
                    handleTodoCommand(input);
                } else if (input.startsWith("deadline")) {
                    handleDeadlineCommand(input);
                } else if (input.startsWith("event")) {
                    handleEventCommand(input);
                } else {
                    throw new DukeException("Oops! I don't recognize that command.");
                }
            } catch (DukeException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" Oops! It seems like your command is incomplete. Please check the format.");
                System.out.println("____________________________________________________________");
            }
        }
        sc.close();
    }

    private static void handleListCommand() throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("Oops! Your task list is currently empty.");
        }
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    private static void handleTodoCommand(String input) throws DukeException {
        if (input.trim().equals("todo")) {
            throw new DukeException("Oops! The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        Todo todo = new Todo(description);
        tasks.add(todo);
        printAddTaskMessage(todo);
    }

    private static void handleDeadlineCommand(String input) throws DukeException {
        if (!input.contains("/by")) {
            throw new DukeException("Oops! A deadline must have a '/by' keyword.");
        }
        String[] parts = input.substring(9).split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new DukeException("Oops! The description and deadline date cannot be empty.");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        printAddTaskMessage(deadline);
    }

    private static void handleEventCommand(String input) throws DukeException {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new DukeException("Oops! An event must have both '/from' and '/to' keywords.");
        }
        String[] parts = input.substring(6).split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to")) {
            throw new DukeException("Oops! The description, start time, and end time cannot be empty.");
        }
        String description = parts[0].trim();
        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new DukeException("Oops! The event times cannot be empty.");
        }
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        Event event = new Event(description, from, to);
        tasks.add(event);
        printAddTaskMessage(event);
    }

    private static void printAddTaskMessage(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
