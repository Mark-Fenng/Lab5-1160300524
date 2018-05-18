package Exception.Edge;

public class UndirectedEdgeException extends Exception {
    /**
     * 在无向图中引入了有向边
     *
     * @param EdgeLabel  发生异常的边的label值
     * @param LineNumber 发生异常的文件行数
     */
    public UndirectedEdgeException(String EdgeLabel, int LineNumber) {
        super("Edge Attribute Error Occur at Line " + LineNumber + "\n"
                + "The Edge : \"" + EdgeLabel + "\" can't be directed");
    }
}
