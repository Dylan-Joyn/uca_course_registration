package src.util;

import src.exception.ValidationException;
import java.util.regex.Pattern;

public class ValidationUtil {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern BANNER_PATTERN =
            Pattern.compile("^[Bb]\\d{3,}$");  // Example: B001, b123

    public static void validateStudent(String id, String name, String email) {
        if (id == null || id.isBlank() || !BANNER_PATTERN.matcher(id).matches())
            throw new ValidationException("Invalid Banner ID format.");
        if (name == null || name.isBlank())
            throw new ValidationException("Name cannot be empty.");
        if (email == null || !EMAIL_PATTERN.matcher(email).matches())
            throw new ValidationException("Invalid email address.");
    }

    public static void validateCourse(String code, String title, int capacity) {
        if (code == null || code.isBlank())
            throw new ValidationException("Course code cannot be empty.");
        if (title == null || title.isBlank())
            throw new ValidationException("Course title cannot be empty.");
        if (capacity < 1 || capacity > 500)
            throw new ValidationException("Capacity must be between 1 and 500.");
    }
}

