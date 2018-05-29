package helper;

import java.io.*;

/**
 * 使用strategy设计模式
 * 这个对WriteFile接口 使用 bufferedWriter 的实现
 */
public class bufferWriter implements WriteFile {
    private final BufferedWriter writer;

    bufferWriter(String filePath) throws FileNotFoundException {
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath))));
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
