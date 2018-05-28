package factory.graph;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class bufferInput implements ReadFile {
    private final BufferedReader reader;

    public bufferInput(String filePath) throws FileNotFoundException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
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
