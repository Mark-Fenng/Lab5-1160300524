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

    /**
     * 一个文件格式错误，但无法提供具体出错的行数
     *
     * @param message 异常的具体提示信息
     */
    public FormatException(String message) {
        super("Format Error Occur \n" + message);
    }
}
