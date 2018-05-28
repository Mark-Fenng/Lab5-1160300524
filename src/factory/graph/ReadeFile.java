package factory.graph;

import java.io.IOException;

interface ReadeFile {
    String readLine() throws IOException;

    void close() throws IOException;
}
