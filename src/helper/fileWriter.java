package helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 使用strategy设计模式
 * 这个对WriteFile接口 使用 fileWriter 的实现
 */
public class fileWriter implements WriteFile {
    private final FileWriter writer;

    fileWriter(String filePath) throws IOException {
        writer = new FileWriter(new File(filePath));
    }

    @Override
    public void write(String content) throws IOException {
        writer.write(content);
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
