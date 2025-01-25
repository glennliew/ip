public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public abstract String getStatusIcon(); // Abstract method for subclasses to define

    @Override
    public String toString() {
        return "[" + taskType.name().charAt(0) + "]" + (isDone ? "[X]" : "[ ]") + " " + description;
    }
}
