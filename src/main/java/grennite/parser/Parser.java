package grennite.parser;

import grennite.exception.GrenniteException;
import grennite.tasklist.TaskList;
import grennite.ui.UI;

public class Parser {

    private TaskList taskList;
    private UI ui;

    public Parser(TaskList taskList, UI ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Parses the given input string and performs the corresponding action
     * on the task list then displays the result to the user.
     * 
     * @param input the input string
     * @throws GrenniteException if the input is invalid
     */
    public void processCommand(String input) throws GrenniteException {
        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
            case "bye":
                ui.exitMessage();
                System.exit(0);
                break;
            case "list":
                taskList.listTasks(ui);
                break;
            case "mark":
                taskList.markTask(parseIndex(words), true, ui);
                break;
            case "unmark":
                taskList.markTask(parseIndex(words), false, ui);
                break;
            case "delete":
                taskList.deleteTask(parseIndex(words), ui);
                break;
            case "todo":
                taskList.addTodo(input, ui);
                break;
            case "deadline":
                taskList.addDeadline(input, ui);
                break;
            case "event":
                taskList.addEvent(input, ui);
                break;
            case "find":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new GrenniteException("Invalid input! Use: find [keyword]");
                }
                taskList.findTasks(words[1].trim(), ui);
                break;
            default:
                throw new GrenniteException("Invalid command");
        }
    }

    /**
     * Parse the index of the task to be affected by the given command.
     * 
     * @param words the split input string
     * @return the index of the task
     * @throws GrenniteException if the input is invalid
     */
    private int parseIndex(String[] words) throws GrenniteException {
        if (words.length < 2) {
            throw new GrenniteException("Invalid input, command requires an index");
        }
        try {
            return Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new GrenniteException("Invalid task number");
        }
    }
}