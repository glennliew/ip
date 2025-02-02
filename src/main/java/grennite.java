import grennite.exception.GrenniteException;
import grennite.parser.Parser;
import grennite.storage.Storage;
import grennite.tasklist.TaskList;
import grennite.ui.UI;

public class grennite {

    private UI ui;
    private TaskList taskList;
    private Parser parser;
    private Storage storage;

    public grennite(String filepath) {
        this.ui = new UI();
        this.taskList = new TaskList();
        this.parser = new Parser(taskList, ui);
        this.storage = new Storage(filepath);
    }

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

    public static void main(String[] args) {
        new grennite("grennite.txt").run();
    }
}