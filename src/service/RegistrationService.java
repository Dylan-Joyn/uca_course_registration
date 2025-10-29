package src.service;

import src.exception.EnrollmentException;
import src.model.Course;
import src.model.Student;
import src.util.ConsoleUtil;
import src.util.LoggerUtil;
import src.util.ValidationUtil;

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

        ValidationUtil.validateStudent(id, name, email);
        if (students.containsKey(id))
            throw new EnrollmentException("Student already exists.");

        students.put(id, new Student(id, name, email));
        LoggerUtil.info("Added student " + id);
    }

    public void addCourseUI(Scanner sc) {
        ConsoleUtil.print("Course Code: ");
        String code = sc.nextLine().trim();
        ConsoleUtil.print("Title: ");
        String title = sc.nextLine().trim();
        ConsoleUtil.print("Capacity: ");
        int cap = Integer.parseInt(sc.nextLine().trim());

        ValidationUtil.validateCourse(code, title, cap);
        if (courses.containsKey(code))
            throw new EnrollmentException("Course already exists.");

        courses.put(code, new Course(code, title, cap));
        LoggerUtil.info("Added course " + code);
    }

    public void enrollUI(Scanner sc) {
        ConsoleUtil.print("Student ID: ");
        String sid = sc.nextLine().trim();
        ConsoleUtil.print("Course Code: ");
        String cc = sc.nextLine().trim();

        Course c = courses.get(cc);
        if (c == null) throw new EnrollmentException("No such course.");
        if (!students.containsKey(sid)) throw new EnrollmentException("No such student.");

        if (c.roster.contains(sid)) throw new EnrollmentException("Already enrolled.");
        if (c.waitlist.contains(sid)) throw new EnrollmentException("Already waitlisted.");

        if (c.roster.size() >= c.capacity) {
            c.waitlist.add(sid);
            LoggerUtil.warn("Waitlisted " + sid + " for " + cc);
            ConsoleUtil.println("Course full. Added to waitlist.");
        } else {
            c.roster.add(sid);
            LoggerUtil.info("Enrolled " + sid + " in " + cc);
            ConsoleUtil.println("Enrolled successfully.");
        }
    }

    public void dropUI(Scanner sc) {
        ConsoleUtil.print("Student ID: ");
        String sid = sc.nextLine().trim();
        ConsoleUtil.print("Course Code: ");
        String cc = sc.nextLine().trim();

        Course c = courses.get(cc);
        if (c == null) throw new EnrollmentException("No such course.");

        if (c.roster.remove(sid)) {
            LoggerUtil.info("Dropped " + sid + " from " + cc);
            if (!c.waitlist.isEmpty()) {
                String promote = c.waitlist.remove(0);
                c.roster.add(promote);
                LoggerUtil.info("Promoted " + promote + " from waitlist.");
                ConsoleUtil.println("Promoted " + promote + " from waitlist.");
            } else {
                ConsoleUtil.println("Dropped successfully.");
            }
        } else if (c.waitlist.remove(sid)) {
            LoggerUtil.info("Removed " + sid + " from waitlist for " + cc);
            ConsoleUtil.println("Removed from waitlist.");
        } else {
            throw new EnrollmentException("Student not enrolled or waitlisted.");
        }
    }

    public void listStudents() {
        ConsoleUtil.println("Students:");
        for (Student s : students.values()) ConsoleUtil.println(" - " + s);
    }

    public void listCourses() {
        ConsoleUtil.println("Courses:");
        for (Course c : courses.values()) {
            ConsoleUtil.println(" - " + c.code + " " + c.title + " cap=" + c.capacity +
                    " enrolled=" + c.roster.size() + " wait=" + c.waitlist.size());
        }
    }

    // Add these implementations in src/service/RegistrationService.java

    public void addStudent(String id, String name, String email) {
        ValidationUtil.validateStudent(id, name, email);
        if (students.containsKey(id)) {
            throw new EnrollmentException("Student already exists.");
        }
        students.put(id, new Student(id, name, email));
    }

    public void addCourse(String code, String title, int capacity) {
        ValidationUtil.validateCourse(code, title, capacity);
        if (courses.containsKey(code)) {
            throw new EnrollmentException("Course already exists.");
        }
        courses.put(code, new Course(code, title, capacity));
    }

    public void enroll(String studentId, String courseCode) {
        Course c = courses.get(courseCode);
        if (c == null) throw new EnrollmentException("No such course.");
        if (!students.containsKey(studentId)) throw new EnrollmentException("No such student.");

        if (c.roster.contains(studentId)) throw new EnrollmentException("Already enrolled.");
        if (c.waitlist.contains(studentId)) throw new EnrollmentException("Already waitlisted.");

        if (c.roster.size() >= c.capacity) {
            c.waitlist.add(studentId);           // course full → waitlist
        } else {
            c.roster.add(studentId);             // seat available → enroll
        }
    }

    public void drop(String studentId, String courseCode) {
        Course c = courses.get(courseCode);
        if (c == null) throw new EnrollmentException("No such course.");

        boolean removed = c.roster.remove(studentId);
        if (removed) {
            // freed a seat: promote the first from waitlist if present
            if (!c.waitlist.isEmpty()) {
                String promote = c.waitlist.remove(0);
                c.roster.add(promote);
            }
            return;
        }
        if (c.waitlist.remove(studentId)) {
            return; // removed from waitlist; nothing else to do
        }
        throw new EnrollmentException("Student not enrolled or waitlisted.");
    }


}