package Exception.Edge;

public class HyperEdgeException extends Exception {
    /**
     * 当向图中添加超边时，如果超边的点的个数小于2，就会发生异常
     *
     * @param EdgeLabel 发生异常的边的label值
     */
    public HyperEdgeException(String EdgeLabel) {
        super("The Hyper Edge : \"" + EdgeLabel + "\" does't have enough vertex to create");
    }
}
