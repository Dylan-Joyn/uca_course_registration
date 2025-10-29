//Unit
package src.util;

import org.junit.jupiter.api.Test;
import src.exception.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilTest {
    @Test void validateStudent_good() {
        assertDoesNotThrow(() -> ValidationUtil.validateStudent("B00123","Alice","a@x.com"));
    }
    @Test void validateStudent_badBanner() {
        assertThrows(ValidationException.class, () -> ValidationUtil.validateStudent("123","A","a@x.com"));
    }
    @Test void validateStudent_badEmail() {
        assertThrows(ValidationException.class, () -> ValidationUtil.validateStudent("B1234","A","nope"));
    }
    @Test void validateCourse_good() {
        assertDoesNotThrow(() -> ValidationUtil.validateCourse("CSCI101","Intro",30));
    }
    @Test void validateCourse_badCapacity() {
        assertThrows(ValidationException.class, () -> ValidationUtil.validateCourse("CSCI101","Intro",0));
        assertThrows(ValidationException.class, () -> ValidationUtil.validateCourse("CSCI101","Intro",9999));
    }
}

