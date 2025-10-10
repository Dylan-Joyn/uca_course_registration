package src.app;

import src.model.Course;
import src.model.Student;
import src.repo.FileRepository;
import src.service.RegistrationService;
import src.util.ConsoleUtil;
import src.util.LoggerUtil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RegistrationService service = new RegistrationService();

        boolean demo = args.length > 0 && "--demo".equalsIgnoreCase(args[0]);
        if (demo) {
            seedDemoData(service);
            ConsoleUtil.println("Demo data loaded.");
        } else {
            LoggerUtil.info("Loading CSV data...");
            FileRepository.loadStudents(service.students);
            FileRepository.loadCourses(service.courses);
            FileRepository.loadEnrollments(service.courses);
        }

        ConsoleUtil.println("=== UCA Course Registration (Clean Design) ===");
        menuLoop(service);

        LoggerUtil.info("Saving data...");
        FileRepository.saveStudents(service.students);
        FileRepository.saveCourses(service.courses);
        FileRepository.saveEnrollments(service.courses);
        ConsoleUtil.println("Goodbye!");
    }

    private static void menuLoop(RegistrationService service) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            ConsoleUtil.println("\nMenu:");
            ConsoleUtil.println("1) Add student");
            ConsoleUtil.println("2) Add course");
            ConsoleUtil.println("3) Enroll student in course");
            ConsoleUtil.println("4) Drop student from course");
            ConsoleUtil.println("5) List students");
            ConsoleUtil.println("6) List courses");
            ConsoleUtil.println("0) Exit");
            ConsoleUtil.print("Choose: ");
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> service.addStudentUI(sc);
                    case "2" -> service.addCourseUI(sc);
                    case "3" -> service.enrollUI(sc);
                    case "4" -> service.dropUI(sc);
                    case "5" -> service.listStudents();
                    case "6" -> service.listCourses();
                    case "0" -> { return; }
                    default -> ConsoleUtil.println("Invalid option.");
                }
            } catch (Exception e) {
                ConsoleUtil.println("Error: " + e.getMessage());
                LoggerUtil.error(e.getMessage());
            }
        }
    }

    private static void seedDemoData(RegistrationService s) {
        s.students.put("B001", new Student("B001", "Alice", "alice@uca.edu"));
        s.students.put("B002", new Student("B002", "Brian", "brian@uca.edu"));
        s.courses.put("CSCI4490", new Course("CSCI4490", "Software Engineering", 2));
        s.courses.put("MATH1496", new Course("MATH1496", "Calculus I", 50));
    }
}
