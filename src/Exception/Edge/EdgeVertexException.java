package Exception.Edge;

public class EdgeVertexException extends Exception {

    /**
     * 给图中添加点时，边的顶点还未添加到图中
     *
     * @param message 异常的具体提示信息
     */
    public EdgeVertexException(String message) {
        super(message);
    }

}
