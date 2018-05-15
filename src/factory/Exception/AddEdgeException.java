package factory.Exception;

public class AddEdgeException extends Exception {

    /**
     * 给图中添加点时
     *
     * @param message 格式错误的具体提示信息
     */
    public AddEdgeException(String message) {
        super(message);
    }

    public AddEdgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddEdgeException(Throwable cause) {
        super(cause);
    }

}
