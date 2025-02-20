package grennite.parser;

import java.io.IOException;

import grennite.exception.GrenniteException;
import grennite.tasklist.TaskList;
import grennite.ui.UI;
import grennite.storage.Storage;


public class Parser {

    private TaskList taskList;
    private UI ui;
    private Storage storage;

    public Parser(TaskList taskList, UI ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Represents the list of supported commands in the Grennite application.
     */
    public enum Command {
        BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND_DATE, FIND, CLEAR;
    }

    /**
     * Parses the given command string and returns a valid Command enum.
     * 
     * @param input the user input string
     * @return the parsed Command
     * @throws GrenniteException if the command is not recognized
     */
    public static Command parseCommand(String input) throws GrenniteException {
        String commandKeyword = extractCommandKeyword(input);
        try {
            return Command.valueOf(commandKeyword);
        } catch (IllegalArgumentException e) {
            throw new GrenniteException("Sorry! I don't recognize that command.");
        }
    }

    private static String extractCommandKeyword(String input) {
        return input.split(" ")[0].toUpperCase();
    }

    /**
     * Processes the user command, performs the corresponding action, and returns a response.
     * 
     * @param input the user input string
     * @return the response message
     * @throws GrenniteException if the input is invalid
     * @throws IOException 
     */
    public void processCommand(String input) throws GrenniteException {
        assert input != null && !input.isBlank() : "Command input should not be null or empty";
    
        if (input.isEmpty()) {
            throw new GrenniteException("Waiting for your next command!");
        }

        Command command = Parser.parseCommand(input);
        String response;

        switch (command) {
            case BYE -> response = ui.exitMessage();
            case LIST -> response = ui.showTaskList(taskList.getTasks());
            case MARK -> response = taskList.markTask(input, true);
            case UNMARK -> response = taskList.markTask(input, false);
            case DELETE -> response = taskList.deleteTask(input);
            case TODO -> response = taskList.addTodo(input);
            case DEADLINE -> response = taskList.addDeadline(input);
            case EVENT -> response = taskList.addEvent(input);
            default -> throw new GrenniteException("Invalid command!");
        }

        if (command != Command.LIST && command != Command.BYE) {
            storage.saveTasks(taskList.getTasks());
        }

        return response;
    }
}
