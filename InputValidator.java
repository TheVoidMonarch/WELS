import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class InputValidator {
    public static String getRequiredText(String message) {
        while (true) {
            String value = JOptionPane.showInputDialog(null, message);
            if (value == null) return null;
            if (!value.trim().isEmpty()) return value.trim();
            JOptionPane.showMessageDialog(null, "This field cannot be empty.");
        }
    }

    public static int getPositiveInteger(String message) {
        while (true) {
            String value = JOptionPane.showInputDialog(null, message);
            if (value == null) return -1;
            try {
                int number = Integer.parseInt(value.trim());
                if (number > 0) return number;
            } catch (NumberFormatException ignored) { }
            JOptionPane.showMessageDialog(null,
                    "Please enter a positive whole number.");
        }
    }

    public static LocalDate getDate(String message) {
        while (true) {
            String value = JOptionPane.showInputDialog(null,
                    message + " (format: YYYY-MM-DD)");
            if (value == null) return null;
            try {
                return LocalDate.parse(value.trim());
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null,
                        "Invalid date. Example: 2026-10-30");
            }
        }
    }
}
