package src.nonfunctional;

import org.junit.jupiter.api.Test;
import src.service.RegistrationService;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class PerformanceAndRobustnessTest {
    @Test void addManyStudentsUnderTime() {
        assertTimeoutPreemptively(Duration.ofMillis(400), () -> {
            var svc = new RegistrationService();
            for (int i = 0; i < 10_000; i++) {
                String id = String.format("B%03d", i); // B000, B001, ... B9999
                svc.addStudent(id, "N"+i, i+"@x.com");
            }
            assertEquals(10_000, svc.students.size());
        });
    }

    @Test void enrollUnknownsThrows() {
        var svc = new RegistrationService();
        svc.addCourse("CSCI101","Intro",1);
        assertThrows(RuntimeException.class, () -> svc.enroll("NOPE", "CSCI101"));
        svc.addStudent("B001","A","a@x.com");
        assertThrows(RuntimeException.class, () -> svc.enroll("B1", "NOCOURSE"));
    }
}

