package src.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
    private static final Properties props = new Properties();

    static {
        try (InputStream in = ConfigUtil.class.getResourceAsStream("/application.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (Exception ignored) {}
    }

    public static String getProperty(String key, String defaultValue) {
        String sysProp = System.getProperty(key);
        if (sysProp != null) return sysProp;
        String env = System.getenv(key.toUpperCase().replace('.', '_'));
        if (env != null) return env;
        return props.getProperty(key, defaultValue);
    }
}

