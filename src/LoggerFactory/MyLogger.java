package LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class MyLogger {
    /**
     * 对logger的info方法的封装
     *
     * @param message 需要info的信息
     */
    public static void info(String message) {
        try {
            List<Object> logger = LoggerFactory.getLogger("Exception", "./Lab.log");
            ((Logger) logger.get(0)).info(message + "\n");
            ((FileHandler) logger.get(1)).close();
        } catch (IOException ignored) {
        }
    }

    /**
     * 对logger的warning方法的封装
     *
     * @param message 需要warning的信息
     */
    public static void warning(String message) {
        try {
            List<Object> logger = LoggerFactory.getLogger("Exception", "./Lab.log");
            ((Logger) logger.get(0)).warning(message + "\n");
            ((FileHandler) logger.get(1)).close();
        } catch (IOException ignored) {
        }
    }

    /**
     * 对logger的severe方法的封装
     *
     * @param message 需要severe的信息
     */
    public static void severe(String message) {
        try {
            List<Object> logger = LoggerFactory.getLogger("Exception", "./Lab.log");
            ((Logger) logger.get(0)).severe(message + "\n");
            ((FileHandler) logger.get(1)).close();
        } catch (IOException ignored) {
        }
    }

    public static String toString(Exception e) {
        StringBuilder StackTrace = new StringBuilder();
        for (StackTraceElement item : e.getStackTrace()) {
            StackTrace.append(item).append("\n");
        }
        return StackTrace.toString();
    }
}
