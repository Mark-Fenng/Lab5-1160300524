package Exception.Edge;

public class EdgeVertexTypeException extends Exception {
    /**
     * 当边中的点的类型不符合此边的要求时，抛出此异常
     *
     * @param EdgeLabel 发生异常的边的label
     */
    public EdgeVertexTypeException(String EdgeLabel) {
        super("The Edge : \"" + EdgeLabel + "\" has wrong type of vertices");
    }
}
