package src.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    @Test void constructor_setsFields() {
        Course c = new Course("CSCI101","Intro",2);
        assertEquals("CSCI101", c.code);
        assertEquals("Intro", c.title);
        assertEquals(2, c.capacity);
        assertNotNull(c.roster);
        assertNotNull(c.waitlist);
    }
}

