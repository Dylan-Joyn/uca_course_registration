package src.util;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsoleUtil {
    private static final List<String> auditLog = new ArrayList<>();

    public static void print(String s) {
        System.out.print(s);
    }

    public static void println(String s) {
        System.out.println(s);
    }

    public static void audit(String ev) {
        auditLog.add(LocalDateTime.now() + " | " + ev);
    }

    public static List<String> getAuditLog() {
        return auditLog;
    }
}

