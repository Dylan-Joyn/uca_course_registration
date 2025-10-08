package src.util;

import java.util.logging.*;

public class LoggerUtil {
    private static final Logger logger = Logger.getLogger("CourseRegistration");

    static {
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.INFO);
        logger.addHandler(handler);
        logger.setLevel(Level.INFO);
    }

    public static void info(String msg) { logger.info(msg); }
    public static void warn(String msg) { logger.warning(msg); }
    public static void error(String msg) { logger.severe(msg); }
}

