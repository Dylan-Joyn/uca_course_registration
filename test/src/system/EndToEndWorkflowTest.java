package src.system;

import org.junit.jupiter.api.Test;
import src.service.RegistrationService;

import static org.junit.jupiter.api.Assertions.*;

class EndToEndWorkflowTest {
    @Test void addEnrollDropPromoteAndList() {
        var svc = new RegistrationService();
        svc.addStudent("B1","Alice","a@x.com");
        svc.addStudent("B2","Bob","b@x.com");
        svc.addStudent("B3","Wendy","w@x.com");
        svc.addCourse("CSCI101","Intro",2);

        svc.enroll("B1","CSCI101");
        svc.enroll("B2","CSCI101");
        svc.enroll("B3","CSCI101");   // waitlist

        var c = svc.courses.get("CSCI101");
        assertEquals(2, c.roster.size());
        assertEquals(1, c.waitlist.size());

        svc.drop("B2","CSCI101");     // promote B3
        assertEquals(2, c.roster.size());
        assertTrue(c.roster.contains("B1"));
        assertTrue(c.roster.contains("B3"));
        assertTrue(c.waitlist.isEmpty());
    }
}

