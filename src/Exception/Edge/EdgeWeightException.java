package Exception.Edge;

public class EdgeWeightException extends Exception {
    /**
     * 当向图中添加的边的 weight 值不符合图类型的要求时，就抛出此异常
     *
     * @param EdgeLabel 发生异常的边的Label值
     * @param weight    边的不符合要求的 weight 值
     */
    public EdgeWeightException(String EdgeLabel, String weight) {
        super("The Edge : \"" + EdgeLabel + "\" Has Wrong Weight value : \"" + weight + " \"");
    }
}
