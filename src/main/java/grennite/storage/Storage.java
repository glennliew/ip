package grennite.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import grennite.exception.GrenniteException;
import grennite.task.Deadline;
import grennite.task.Event;
import grennite.task.Task;
import grennite.task.Todo;

public class Storage {

    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/grennite.txt";

    public Storage(String filepath) {
    }

    /**
     * Loads tasks from a file and returns them as a list.
     * 
     * <p>
     * This method reads each line of the file and parses it into a task. If the line is
     * not a valid task, it prints an error message and skips the line.
     * 
     * @return a list of tasks
     * @throws IOException if there is an error reading the file
     * @throws GrenniteException 
     */
    public ArrayList<Task> loadTasks() throws IOException, GrenniteException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return tasks; 
        }

        Scanner scanner = new Scanner(file);


        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");


            try {
                if (parts.length < 3) {
                    throw new IllegalArgumentException("Invalid task format: " + line);
                }

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                    case "T":
                        tasks.add(new Todo(description, isDone));
                        break;
                    case "D":
                        if (parts.length < 4) throw new IllegalArgumentException("Invalid Deadline format: " + line);
                        tasks.add(new Deadline(description, parts[3]));
                        break;
                    case "E":
                        if (parts.length < 6) throw new IllegalArgumentException("Invalid Event format: " + line);
                        tasks.add(new Event(description, parts[3], parts[4], parts[5], isDone));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown task type: " + type);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Skipped corrupted line: " + e.getMessage());
            }
        }
        scanner.close();
        return tasks;
    }

    /**
     * Saves the given tasks to the file at {@link #FILE_PATH}.
     * If the file does not exist, it will be created.
     * If the file exists, it will be overwritten.
     * The file is written as a text file with each task on a new line.
     *
     * @throws IOException if there is an error writing to the file.
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs(); 
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        for (Task task : tasks) {
            writer.write(task.toFileFormat());
            writer.newLine();
        }
        writer.close();
        System.out.println("Tasks saved to: " + new File(FILE_PATH).getAbsolutePath());
    }
}