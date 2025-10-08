package src.repo;

import src.model.Course;
import src.model.Student;
import src.util.ConsoleUtil;

import java.io.*;
import java.util.Map;

public class FileRepository {

    public static final String STUDENTS_CSV = "students.csv";
    public static final String COURSES_CSV = "courses.csv";
    public static final String ENROLLMENTS_CSV = "enrollments.csv";

    public static void loadStudents(Map<String, Student> students) {
        File f = new File(STUDENTS_CSV);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", -1);
                if (p.length >= 3) {
                    students.put(p[0], new Student(p[0], p[1], p[2]));
                }
            }
            ConsoleUtil.audit("LOAD students=" + students.size());
        } catch (Exception e) {
            ConsoleUtil.println("Failed load students: " + e.getMessage());
        }
    }

    public static void saveStudents(Map<String, Student> students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(STUDENTS_CSV))) {
            for (Student s : students.values()) {
                pw.println(s.id + "," + s.name + "," + s.email);
            }
        } catch (Exception e) {
            ConsoleUtil.println("Failed save students: " + e.getMessage());
        }
    }

    public static void loadCourses(Map<String, Course> courses) {
        File f = new File(COURSES_CSV);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", -1);
                if (p.length >= 3) {
                    try {
                        int cap = Integer.parseInt(p[2]);
                        courses.put(p[0], new Course(p[0], p[1], cap));
                    } catch (NumberFormatException ignored) {}
                }
            }
            ConsoleUtil.audit("LOAD courses=" + courses.size());
        } catch (Exception e) {
            ConsoleUtil.println("Failed load courses: " + e.getMessage());
        }
    }

    public static void saveCourses(Map<String, Course> courses) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(COURSES_CSV))) {
            for (Course c : courses.values()) {
                pw.println(c.code + "," + c.title + "," + c.capacity);
            }
        } catch (Exception e) {
            ConsoleUtil.println("Failed save courses: " + e.getMessage());
        }
    }

    public static void loadEnrollments(Map<String, Course> courses) {
        File f = new File(ENROLLMENTS_CSV);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|", -1);
                if (p.length >= 3) {
                    String code = p[0], sid = p[1], status = p[2];
                    Course c = courses.get(code);
                    if (c == null) continue;
                    if ("ENROLLED".equalsIgnoreCase(status)) {
                        if (!c.roster.contains(sid)) c.roster.add(sid);
                    } else if ("WAITLIST".equalsIgnoreCase(status)) {
                        if (!c.waitlist.contains(sid)) c.waitlist.add(sid);
                    }
                }
            }
            ConsoleUtil.audit("LOAD enrollments");
        } catch (Exception e) {
            ConsoleUtil.println("Failed load enrollments: " + e.getMessage());
        }
    }

    public static void saveEnrollments(Map<String, Course> courses) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ENROLLMENTS_CSV))) {
            for (Course c : courses.values()) {
                for (String sid : c.roster) pw.println(c.code + "|" + sid + "|ENROLLED");
                for (String sid : c.waitlist) pw.println(c.code + "|" + sid + "|WAITLIST");
            }
        } catch (Exception e) {
            ConsoleUtil.println("Failed save enrollments: " + e.getMessage());
        }
    }
}

