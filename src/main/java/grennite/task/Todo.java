package grennite.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    public Todo(String description, boolean isDone) {
        super(description, TaskType.TODO);
        this.isDone = isDone;
    }

/**
 * Converts the Todo task to a string in the format "T | isDone | description".
 * This format is used for saving the task to a file.
 * The "isDone" part is represented as "1" if the task is done, otherwise "0".
 * 
 * @return A string representing this Todo task in the file format.
 */

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Converts the Todo task to a string in the format "[T] description".
     * The string is in the format "[X] description" where X is a space if the task is not done,
     * or 'x' if the task is done.
     * @return A string representing this Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}