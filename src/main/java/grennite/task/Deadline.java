package grennite.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import grennite.exception.GrenniteException; 

public class Deadline extends Task {
    protected LocalDateTime deadline;
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Deadline(String description, String deadline) throws GrenniteException {
        super(description, TaskType.DEADLINE);
        try {
            this.deadline = LocalDateTime.parse(deadline, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new GrenniteException("Invalid date-time format! Use: yyyy-MM-dd HHmm (e.g., 2025-02-14 1300)");
        }
    }

    /**
     * Factory method to create a Deadline object from a line from a file.
     * The line should be in the format "description | isDone | dateTime".
     * 
     * @param description the description of the task
     * @param dateTime    the date and time of the deadline in the format
     *                    "yyyy-MM-dd HHmm"
     * @return A Deadline object
     * @throws GrenniteException if the parsing fails
     */
    public static Deadline fromFileFormat(String description, String dateTime) throws GrenniteException {
        return new Deadline(description, dateTime);
    }

    /**
     * Saves the deadline task to a line in a file.
     * Format: "D | isDone | description | dateTime".
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | "
                + description + " | "
                + deadline.format(INPUT_FORMAT);
    }

    /**
     * Converts the deadline task to a user-friendly string.
     * Example: "[D] description (by: Feb 14 2025, 01:00 PM)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + deadline.format(OUTPUT_FORMAT) + ")";
    }
}