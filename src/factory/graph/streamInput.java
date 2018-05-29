package factory.graph;

import java.io.*;

/**
 * strategy设计模式
 * 负责文件读入的一种 以stream 的读入方式
 */
public class streamInput implements ReadFile {
    private final InputStreamReader reader;

    streamInput(String filePath) throws IOException {
        this.reader = new InputStreamReader(new FileInputStream(new File(filePath)));
    }

    @Override
    public String readLine() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        char[] line = new char[1];
        line[0] = '\0';
        int END;
        do {
            END = reader.read(line);
            if (line[0] != '\n' && line[0] != '\r')
                stringBuilder.append(line[0]);
        } while (END != -1 && line[0] != '\n');
        return stringBuilder.toString().equals("\0") ? "" : stringBuilder.toString();
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
