package Exception.Graph;

public class GraphNullException extends Exception {
    /**
     * 当执行图中的命令时，图对象还是null(图还未建立)，则抛出此异常
     *
     * @param message 异常的具体信息
     */
    public GraphNullException(String message) {
        super(message);
    }
}
