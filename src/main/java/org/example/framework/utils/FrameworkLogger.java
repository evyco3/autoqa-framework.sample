package org.example.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FrameworkLogger {

    private static final ThreadLocal<Logger> logger = ThreadLocal.withInitial(() -> LogManager.getLogger(FrameworkLogger.class));

    private FrameworkLogger() {
    }

    public static void log(Class<?> callingClass, LogType type, String message) {
        String className = callingClass.getSimpleName();
        switch (type) {
            case INFO:
                logger.get().info("[{}] {}", className, message);
                break;
            case DEBUG:
                logger.get().debug("[{}] {}", className, message); // Add debug logging
                break;
            case ERROR:
                logger.get().error("[{}] {}", className, message);
                break;
            default:
                throw new IllegalArgumentException("Unsupported log type: " + type);
        }
    }

    public enum LogType {
        INFO, DEBUG, ERROR
    }

}
