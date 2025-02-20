package grennite;

import java.io.IOException;

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
    private String commandType;

    public Grennite(String filepath) throws GrenniteException, IOException {
        this.ui = new UI();
        this.storage = new Storage(filepath); 
        this.taskList = new TaskList(storage, ui);
        this.parser = new Parser(taskList, ui, storage);
        this.commandType = "";
    }

    public Grennite() throws GrenniteException, IOException {
        this("grennite.txt");
    }

    /**
     * Starts the Grennite application.
     * Displays a welcome message and continuously processes user commands
     * until "bye" is entered.
          * @throws IOException 
          */
         public void run() throws IOException {
        ui.welcomeMessage();
        while (true) {
            try {
                String input = ui.readCommand().trim();
                String response = parser.processCommand(input);
                System.out.println(response); // Display response to user
                
                if (input.equalsIgnoreCase("bye")) {
                    break; // Exit after showing the message
                }
            } catch (GrenniteException e) {
                ui.errorMessage(e.getMessage());
            }
        }
    }

    /**
     * The main entry point of Grennite.
     */
    public static void main(String[] args) {
        try {
            new Grennite("grennite.txt").run();
        } catch (GrenniteException | IOException e) {
            System.err.println("Error initializing Grennite: " + e.getMessage());
        }
    }

    /**
     * Generates a response for the user's chat message.
     * 
     * @param input User's command input.
     * @return Response message.
     * @throws IOException 
     */
         public String getResponse(String input) throws IOException {
        try {
            String result = parser.processCommand(input);
            commandType = input.trim().split(" ", 2)[0]; // Extract first word as command type
            return result;
        } catch (GrenniteException e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Returns the last executed command type.
     * 
     * @return Last command type as a string.
     */
    public String getCommandType() {
        return commandType;
    }
}
