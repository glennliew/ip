package grennite.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate date;
    private LocalTime from;
    private LocalTime to;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("h:mm a");

    public Event(String description, String date, String from, String to) {
        super(description, TaskType.EVENT);
        this.date = LocalDate.parse(date);
        this.from = LocalTime.parse(from);
        this.to = LocalTime.parse(to);
    }

    public Event(String description, String date, String from, String to, boolean isDone) {
        super(description, TaskType.EVENT);
        this.date = LocalDate.parse(date);
        this.from = LocalTime.parse(from);
        this.to = LocalTime.parse(to);
        this.isDone = isDone;
    }

/**
 * Factory method to create an Event object from file data.
 * The line should be in the format "E | isDone | description | date | from | to".
 *
 * @param description the description of the event
 * @param day the date of the event in the format "yyyy-MM-dd"
 * @param from the start time of the event in the format "HH:mm"
 * @param to the end time of the event in the format "HH:mm"
 * @param isDone the completion status of the event, where true means completed
 * @return An Event object
 */

    public static Event fromFileFormat(String description, String day, String from, String to, boolean isDone) {
        return new Event(description, day, from, to, isDone);
    }

/**
 * Saves the event task to a line in a file.
 * The line will be in the format "E | isDone | description | date | from | to".
 * @return A string representing this event task in the file format
 */

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + date + " | " + from + " | " + to;
    }

/**
 * Converts the event task to a string in the format 
 * "[E] description (on: MMM d yyyy from: h:mm a to: h:mm a)".
 * @return A string representing this event task
 */

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + date.format(DATE_FORMAT)
                + " from: " + from.format(TIME_FORMAT) + " to: " + to.format(TIME_FORMAT) + ")";
    }
}