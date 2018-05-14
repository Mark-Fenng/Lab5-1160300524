package LoggerFactory;

import java.io.IOException;
import java.util.logging.*;

public class LoggerFactory {
    /**
     * 返回一个可以满足自己要求的logger对象
     * 这个对象就是java.util.logging中的对象
     * 基于它进行了一层封装
     *
     * @param name     logger的唯一标识
     * @param FilePath 输出日志文件的路径
     * @return 满足自己要求的logger对象
     * @throws IOException 输入的日志路径不合法异常
     */
    public static Logger getLogger(String name, String FilePath) throws IOException {
        Logger logger = Logger.getLogger(name);
        logger.setUseParentHandlers(false);
        FileHandler fileHandler = new FileHandler(FilePath);
        logger.addHandler(fileHandler);
        return logger;
    }
}
