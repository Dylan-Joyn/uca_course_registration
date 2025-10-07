package service;

import model.Course;
import model.Student;
import util.ConsoleUtil;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class RegistrationService {
    public Map<String, Student> students = new LinkedHashMap<>();
    public Map<String, Course> courses = new LinkedHashMap<>();

    public void addStudentUI(Scanner sc) {
        ConsoleUtil.print("Banner ID: ");
        String id = sc.nextLine().trim();
        ConsoleUtil.print("Name: ");
        String name = sc.nextLine().trim();
        ConsoleUtil.print("Email: ");
        String email = sc.nextLine().trim();
        Student s = new Student(id, name, email);
        students.put(id, s);
        ConsoleUtil.audit("ADD_STUDENT " + id);
    }

    public void addCourseUI(Scanner sc) {
        ConsoleUtil.print("Course Code: ");
        String code = sc.nextLine().trim();
        ConsoleUtil.print("Title: ");
        String title = sc.nextLine().trim();
        ConsoleUtil.print("Capacity: ");
        int cap = Integer.parseInt(sc.nextLine().trim());
        Course c = new Course(code, title, cap);
        courses.put(code, c);
        ConsoleUtil.audit("ADD_COURSE " + code);
    }

    public void enrollUI(Scanner sc) {
        ConsoleUtil.print("Student ID: ");
        String sid = sc.nextLine().trim();
        ConsoleUtil.print("Course Code: ");
        String cc = sc.nextLine().trim();
        Course c = courses.get(cc);
        if (c == null) { ConsoleUtil.println("No such course"); return; }
        if (c.roster.contains(sid)) { ConsoleUtil.println("Already enrolled"); return; }
        if (c.waitlist.contains(sid)) { ConsoleUtil.println("Already waitlisted"); return; }

        if (c.roster.size() >= c.capacity) {
            c.waitlist.add(sid);
            ConsoleUtil.audit("WAITLIST " + sid + "->" + cc);
            ConsoleUtil.println("Course full. Added to WAITLIST.");
        } else {
            c.roster.add(sid);
            ConsoleUtil.audit("ENROLL " + sid + "->" + cc);
            ConsoleUtil.println("Enrolled.");
        }
    }

    public void dropUI(Scanner sc) {
        ConsoleUtil.print("Student ID: ");
        String sid = sc.nextLine().trim();
        ConsoleUtil.print("Course Code: ");
        String cc = sc.nextLine().trim();
        Course c = courses.get(cc);
        if (c == null) { ConsoleUtil.println("No such course"); return; }

        if (c.roster.remove(sid)) {
            ConsoleUtil.audit("DROP " + sid + " from " + cc);
            if (!c.waitlist.isEmpty()) {
                String promote = c.waitlist.remove(0);
                c.roster.add(promote);
                ConsoleUtil.audit("PROMOTE " + promote + "->" + cc);
                ConsoleUtil.println("Promoted " + promote + " from waitlist.");
            } else {
                ConsoleUtil.println("Dropped.");
            }
        } else if (c.waitlist.remove(sid)) {
            ConsoleUtil.audit("WAITLIST_REMOVE " + sid + " " + cc);
            ConsoleUtil.println("Removed from waitlist.");
        } else {
            ConsoleUtil.println("Not enrolled or waitlisted.");
        }
    }

    public void listStudents() {
        ConsoleUtil.println("Students:");
        for (Student s : students.values()) ConsoleUtil.println(" - " + s);
    }

    public void listCourses() {
        ConsoleUtil.println("Courses:");
        for (Course c : courses.values())
            ConsoleUtil.println(" - " + c.code + " " + c.title + " cap=" + c.capacity
                    + " enrolled=" + c.roster.size() + " wait=" + c.waitlist.size());
    }
}

