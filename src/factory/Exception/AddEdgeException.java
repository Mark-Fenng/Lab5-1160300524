package factory.Exception;

public class AddEdgeException extends Exception {

    /**
     * 一个文件格式错误的异常
     *
     * @param message    格式错误的具体提示信息
     * @param LineNumber 格式错误发生的文件的行数
     */
    public AddEdgeException(String message, int LineNumber) {
        super("Format Occur at Line " + LineNumber + "\n" + message);
    }

    public AddEdgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddEdgeException(Throwable cause) {
        super(cause);
    }

}
