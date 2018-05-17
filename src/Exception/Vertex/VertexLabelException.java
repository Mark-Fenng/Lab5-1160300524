package Exception.Vertex;

public class VertexLabelException extends Exception {
    /**
     * 当向图中添加新的点时，如果新的点的label已经存在，则会抛出此异常
     *
     * @param VertexLabel 发生异常的点的label值
     */
    public VertexLabelException(String VertexLabel) {
        super("The Vertex : \"" + VertexLabel + "\" Has Repeated Label in the Graph");
    }
}
