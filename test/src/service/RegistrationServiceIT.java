package src.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceIT {
    private RegistrationService svc;

    @BeforeEach void setUp() { svc = new RegistrationService(); }

    @Test void addStudent_and_findInMap() {
        svc.addStudent("B001","Alice","a@x.com");
        assertTrue(svc.students.containsKey("B001"));
        assertEquals("Alice", svc.students.get("B001").name);
    }

    @Test void addCourse_and_findInMap() {
        svc.addCourse("CSCI101","Intro",1);
        assertTrue(svc.courses.containsKey("CSCI101"));
        assertEquals(1, svc.courses.get("CSCI101").capacity);
    }

    @Test void enroll_overCapacity_waitlist_thenDropPromotes() {
        svc.addStudent("B001","A","a@x.com");
        svc.addStudent("B002","B","b@x.com");
        svc.addCourse("CSCI101","Intro",1);

        svc.enroll("B001","CSCI101"); // roster
        svc.enroll("B002","CSCI101"); // waitlist

        var c = svc.courses.get("CSCI101");
        assertEquals(1, c.roster.size());
        assertEquals(1, c.waitlist.size());

        svc.drop("B001","CSCI101");   // promote B2
        assertEquals(1, c.roster.size());
        assertEquals(0, c.waitlist.size());
        assertEquals("B002", c.roster.get(0));
    }
}

