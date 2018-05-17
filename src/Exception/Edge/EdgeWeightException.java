package Exception.Edge;

public class EdgeWeightException extends Exception {
    public EdgeWeightException(String EdgeLabel, String weight) {
        super("The Edge : \"" + EdgeLabel + "\" Has Wrong Weight value : \"" + weight + " \"");
    }
}
