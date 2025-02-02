package grennite.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime deadline;
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma");
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = LocalDateTime.parse(deadline, INPUT_FORMAT);
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, TaskType.DEADLINE);
        this.deadline = LocalDateTime.parse(deadline, INPUT_FORMAT);
        this.isDone = isDone;
    }

    /**
     * Factory method to create a Deadline object from a line from a file.
     * The line should be in the format "description | isDone | dateTime".
     * @param description the description of the task
     * @param dateTime the date and time of the deadline in the format "yyyy-MM-dd HHmm"
     * @return A Deadline object
     */
    public static Deadline fromFileFormat(String description, String dateTime) {
        return new Deadline(description, dateTime);
    }

    /**
     * Saves the deadline task to a line in a file.
     * The line will be in the format "D | isDone | description | dateTime".
     * @return A string representing this deadline task in the file format
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline.format(INPUT_FORMAT);
    }


    /**
     * Converts the deadline task to a string in the format "[D] description (by: MMM dd yyyy, hh:mma)".
     * @return A string representing this deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(OUTPUT_FORMAT) + ")";
    }
}