package Exception;

public class FormatException extends Exception {

    /**
     * 一个文件格式错误的异常
     *
     * @param message    格式错误的具体提示信息
     * @param LineNumber 格式错误发生的文件的行数
     */
    public FormatException(String message, int LineNumber) {
        super("Format Error Occur at Line " + LineNumber + "\n" + message);
    }

    public FormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormatException(Throwable cause) {
        super(cause);
    }

}
