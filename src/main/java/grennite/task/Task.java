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

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

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

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getDescription() {
        return description;
    }
}