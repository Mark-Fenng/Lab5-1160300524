package Exception.Command;

public class UnsupportedException extends Exception {
    /**
     * 如果执行的命令在当前图中不支持，则抛出此异常
     *
     * @param message 异常的具体信息
     */
    public UnsupportedException(String message) {
        super(message);
    }
}
