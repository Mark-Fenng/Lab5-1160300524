package Exception.Edge;

public class EdgeLoopException extends Exception {
    /**
     * 当不应该出现loop的图中出现了loop，抛出此异常
     *
     * @param EdgeLabel 发生异常的图的label值
     */
    public EdgeLoopException(String EdgeLabel) {
        super("The Edge : \"" + EdgeLabel + "\" Has Wrong Type of Vertex");
    }
}
