package factory.graph;

import java.io.IOException;

interface ReadFile {
    /**
     * 从文件中读取一行内容
     *
     * @return 文件一行内容的字符串，如果读到了文件的结尾，就返回空串
     * @throws IOException 文件读取的异常
     */
    String readLine() throws IOException;

    /**
     * 将文件从内存中释放
     *
     * @throws IOException 文件释放的异常
     */
    void close() throws IOException;
}
