package io.perfiora.util;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Utility class to retrieve application version information.
 */
public class Version {
    
    private static final Logger log = Logger.getLogger(Version.class.getName());
    private static final String VERSION_PROPERTIES = "/version.properties";
    private static String version = null;
    
    /**
     * Get the application version.
     * 
     * @return The version string (e.g., "25.12") or "unknown" if not available
     */
    public static String getVersion() {
        if (version == null) {
            version = loadVersion();
        }
        return version;
    }
    
    private static String loadVersion() {
        try (InputStream inputStream = Version.class.getResourceAsStream(VERSION_PROPERTIES)) {
            if (inputStream != null) {
                Properties properties = new Properties();
                properties.load(inputStream);
                String v = properties.getProperty("version");
                if (v != null && !v.isEmpty() && !v.startsWith("${")) {
                    return v;
                }
            }
        } catch (Exception e) {
            log.fine("Could not load version from properties: " + e.getMessage());
        }
        return "unknown";
    }
}

