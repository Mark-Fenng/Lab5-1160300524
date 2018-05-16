package Exception.Vertex;

public class VertexTypeException extends Exception {
    /**
     * 当前这个顶点的类型不符合这个图的要求，抛出此异常
     *
     * @param VertexLabel 抛出异常的点的label值
     */
    public VertexTypeException(String VertexLabel) {
        super("The Vertex : \"" + VertexLabel + "\" should not in this Graph");
    }
}
