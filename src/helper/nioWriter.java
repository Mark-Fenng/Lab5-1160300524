package helper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 使用strategy设计模式
 * 这个对WriteFile接口 使用 nio.Files 的实现
 */
public class nioWriter implements WriteFile {

    private final BufferedWriter writer;

    nioWriter(String filePath) throws IOException {
        writer = Files.newBufferedWriter(Paths.get(filePath), StandardCharsets.UTF_8);
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
