package Exception.Vertex;

public class VertexAttributeException extends Exception {
    /**
     * 在点的参数不符合这个顶点的要求时，就会抛出这个异常
     *
     * @param VertexLabel 发生异常的点的label
     */
    public VertexAttributeException(String VertexLabel) {
        super("The Vertex : \"" + VertexLabel + "\" has wrong number of attributes");
    }
}
