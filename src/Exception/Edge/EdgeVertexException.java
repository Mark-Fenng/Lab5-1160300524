package Exception.Edge;

public class EdgeVertexException extends Exception {

    /**
     * 给图中添加点时
     *
     * @param message 格式错误的具体提示信息
     */
    public EdgeVertexException(String message) {
        super(message);
    }

    public EdgeVertexException(String message, Throwable cause) {
        super(message, cause);
    }

    public EdgeVertexException(Throwable cause) {
        super(cause);
    }

}
