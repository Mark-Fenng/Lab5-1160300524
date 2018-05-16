package Exception;

public class TypeException extends Exception {

    /**
     * 读取包含图的文件中，获取点，边，图的类型时，发生的异常
     *
     * @param message 发生异常的具体信息
     */
    public TypeException(String message) {
        super(message);
    }
}
