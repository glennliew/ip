import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        try {
            if (by.contains(" ")) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                this.by = LocalDateTime.parse(by, dateTimeFormatter);
            } else {
                LocalDate date = LocalDate.parse(by);
                this.by = date.atStartOfDay();
            }
        } catch (DateTimeParseException e) {
            this.by = LocalDate.now().atStartOfDay();
        }
    }

    @Override
    public String toString() {
        // Format the date in the required format (e.g., "Dec 15 2023")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = by.format(formatter);
        return "[D] " + description + " (by: " + formattedDate + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter;
        // Use ISO date format if time is midnight, else use the original input pattern
        if (by.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        } else {
            formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        }
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
}