package Exception.Command;

public class CommandException extends Exception {
    /**
     * 当输入的命令在解析时，发现不符合要求，则抛出此遗异常
     *
     * @param message 异常的具体信息
     */
    public CommandException(String message) {
        super(message);
    }
}
