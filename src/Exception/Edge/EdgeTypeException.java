package Exception.Edge;

public class EdgeTypeException extends Exception {
    /**
     * 当前这个边的类型不符合这个图的要求，抛出此异常
     *
     * @param EdgeLabel 格式错误的具体提示信息
     */
    public EdgeTypeException(String EdgeLabel) {
        super("The Edge : \"" + EdgeLabel + "\" has wrong number of attributes");
    }
}
