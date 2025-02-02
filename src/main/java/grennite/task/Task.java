package grennite.task;

import grennite.exception.GrenniteException;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Returns a string that can be used to represent the status of this task
     * in a user interface.  The string returned will be either "X" or " ",
     * depending on whether the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the task's done status to true.
     * This method is intended to be used by the UI to mark a task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Sets the task's done status to false.
     * This method is intended to be used by the UI to unmark a task as done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns a Task object that represents the given line from the data file.
     * The format of the line is expected to be:
     * [T|D|E] [0|1] [description] [optional deadline info]
     * 
     * If the line is not in this format, a GrenniteException is thrown.
     * 
     * The optional deadline info is:
     * [yyyy-MM-dd HHmm] for deadlines, or [yyyy-MM-dd] [HHmm] [HHmm] for events.
     *
     * If the line is not in this format, a GrenniteException is thrown.
     * @param line the line from the file
     * @return a Task object
     * @throws GrenniteException if the line is not in the expected format
     */
    public static Task fromFileFormat(String line) throws GrenniteException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new GrenniteException("Corrupted data file: " + line);
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                Todo todo = new Todo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                if (parts.length < 4)
                    throw new GrenniteException("Corrupted deadline data: " + line);
                Deadline deadline = new Deadline(description, parts[3]);
                if (isDone) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                if (parts.length < 5)
                    throw new GrenniteException("Corrupted event data: " + line);
                Event event = new Event(description, parts[3], parts[4], parts[5]);
                if (isDone) {
                    event.markAsDone();
                }
                return event;
            default:
                throw new GrenniteException("Unknown task type: " + type);
        }
    }

    public abstract String toFileFormat();

    /**
     * Returns a string representation of the task.
     * The string is in the format "[X] description" where X is a space if the task is not done,
     * or 'x' if the task is done.
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}