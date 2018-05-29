package factory.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * strategy设计模式
 * 负责文件读入的一种 使用 nio.Files 读入方式
 */
public class filesInput implements ReadFile {
    private final BufferedReader reader;

    public filesInput(String filePath) throws IOException {
        this.reader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8);
    }

    @Override
    public String readLine() throws IOException {
        return reader.readLine();
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
