import java.util.Scanner;

public class grennite {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm grennite, your friendly assistant!");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye! Have an amazing day! See you soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            System.out.println("____________________________________________________________");
            System.out.println(" " + input);
            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
