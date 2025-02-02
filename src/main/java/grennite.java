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
        new Grennite("grennite.txt").run();
    }
}