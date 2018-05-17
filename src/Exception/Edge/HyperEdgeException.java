package Exception.Edge;

import edge.Edge;

public class HyperEdgeException extends Exception {
    public HyperEdgeException(String EdgeLabel) {
        super("The Hyper Edge : \"" + EdgeLabel + "\" does't have enough vertex to create");
    }
}
