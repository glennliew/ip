import java.util.ArrayList;
import java.util.Scanner;

public class grennite {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm grennite");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + "." + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                Todo todo = new Todo(description);
                tasks.add(todo);
                printAddTaskMessage(todo);
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ", 2);
                String description = parts[0];
                String by = parts[1];
                Deadline deadline = new Deadline(description, by);
                tasks.add(deadline);
                printAddTaskMessage(deadline);
            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from ", 2);
                String description = parts[0];
                String[] timeParts = parts[1].split(" /to ", 2);
                String from = timeParts[0];
                String to = timeParts[1];
                Event event = new Event(description, from, to);
                tasks.add(event);
                printAddTaskMessage(event);
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" Oops! I don't recognize that command.");
                System.out.println("____________________________________________________________");
            }
        }
        sc.close();
    }

    private static void printAddTaskMessage(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
