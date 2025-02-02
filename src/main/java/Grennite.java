package grennite;
import grennite.exception.GrenniteException;
import grennite.parser.Parser;
import grennite.storage.Storage;
import grennite.tasklist.TaskList;
import grennite.ui.UI;

public class Grennite {

    private UI ui;
    private TaskList taskList;
    private Parser parser;
    private Storage storage;

    public Grennite(String filepath) {
        this.ui = new UI();
        this.taskList = new TaskList();
        this.parser = new Parser(taskList, ui);
        this.storage = new Storage(filepath);
    }

    /**
     * Starts the Grennite application.
     * 
     * 
     * This method will not return until the user types "bye" to exit the application.
     * 
     * 
     * It displays a welcome message, then enters an infinite loop. In each iteration of the loop, it reads a command
     * from the user and processes it. If the command is invalid, it displays an error message and continues to the next
     * iteration.
     */
    public void run() {
        ui.welcomeMessage();
        while (true) {
            try {
                String input = ui.readCommand();
                parser.processCommand(input);
            } catch (GrenniteException e) {
                ui.errorMessage(e.getMessage());
            }
        }
    }

    /**
     * The main entry point of the Grennite application.
     * 
     * 
     * The command line arguments are ignored.
     * 
     * 
     * This method creates a new instance of the Grennite class and starts the application by calling the run method.
     */
    public static void main(String[] args) {
        new Grennite("grennite.txt").run();
    }
}