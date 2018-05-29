package helper;

import java.io.IOException;

/**
 * 使用了strategy设计模式
 * 图的信息写入文件有几种写文件的方式
 * 通过实现这个接口就可以实现多种写文件的方式
 */
public interface WriteFile {
    /**
     * 将一个String写入文件
     *
     * @param content 要写入的内容
     */
    void write(String content) throws IOException;

    /**
     * 将文件流关闭，从内存中释放
     */
    void close() throws IOException;
}
