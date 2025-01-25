public class Todo extends Task {

    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }
}
