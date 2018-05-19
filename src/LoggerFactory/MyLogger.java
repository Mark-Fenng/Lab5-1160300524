package LoggerFactory;

import java.io.IOException;
import java.util.logging.Logger;

public class MyLogger {
    /**
     * 对logger的info方法的封装
     *
     * @param message 需要info的信息
     */
    public static void info(String message) {
        try {
            Logger logger = LoggerFactory.getLogger("Exception", "./Lab.log");
            logger.info(message + "\n");
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
            Logger logger = LoggerFactory.getLogger("Exception", "./Lab.log");
            logger.warning(message + "\n");
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
            Logger logger = LoggerFactory.getLogger("Exception", "./Lab.log");
            logger.severe(message + "\n");
        } catch (IOException ignored) {
        }
    }
}
