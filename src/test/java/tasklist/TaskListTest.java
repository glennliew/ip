package grennite.tasklist;

import grennite.exception.GrenniteException;
import grennite.task.Task;
import grennite.task.Todo;
import grennite.ui.UI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    private TaskList taskList;
    private UI ui;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        ui = new UI();
    }

    @Test
    public void testAddTodoTask() throws GrenniteException {
        int initialSize = taskList.getTasks().size();

        taskList.addTodo("todo Read book", ui);

        assertEquals(initialSize + 1, taskList.getTasks().size(), "Task list size should increase by 1.");

        Task lastTask = taskList.getTasks().get(taskList.getTasks().size() - 1);
        assertTrue(lastTask instanceof Todo, "Last task should be a Todo task.");
    }
}
