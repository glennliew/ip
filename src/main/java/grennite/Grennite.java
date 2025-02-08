package grennite;

import grennite.exception.GrenniteException;
import grennite.parser.Parser;
import grennite.storage.Storage;
import grennite.tasklist.TaskList;
import grennite.ui.UI;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

public class Grennite {

    private UI ui;
    private TaskList taskList;
    private Parser parser;
    private Storage storage;
    private String commandType;

    public Grennite(String filepath) {
        this.ui = new UI();
        this.taskList = new TaskList();
        this.parser = new Parser(taskList, ui);
        this.storage = new Storage(filepath);
        this.commandType = "";
    }

    public Grennite() {
        this("grennite.txt");
    }

    /**
     * Starts the Grennite application.
     * 
     * 
     * This method will not return until the user types "bye" to exit the
     * application.
     * 
     * 
     * It displays a welcome message, then enters an infinite loop. In each
     * iteration of the loop, it reads a command
     * from the user and processes it. If the command is invalid, it displays an
     * error message and continues to the next
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
     * The main entry point of Grennite.
     * 
     * 
     * The command line arguments are ignored.
     * 
     * 
     * This method creates a new instance of the Grennite class and starts the
     * application by calling the run method.
     */
    public static void main(String[] args) {

        new Grennite("grennite.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            String result = parser.processCommand(input);
            // For simplicity, set the command type to the first word
            String[] tokens = input.trim().split(" ", 2);
            commandType = tokens[0];
            return result;
        } catch (GrenniteException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getCommandType() {
        return commandType;
    }
}