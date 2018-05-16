package Exception.Edge;

public class DirectedEdgeException extends Exception {
    public DirectedEdgeException(String EdgeLabel, int LineNumber) {
        super("Edge Attribute Error Occur at Line " + LineNumber + "\n"
                + "The Edge : \"" + EdgeLabel + "\" can't be undirected");
    }

    public DirectedEdgeException(String EdgeLabel) {
        super("Edge Attribute Error Occur \n"
                + "The Edge : \"" + EdgeLabel + "\" can't be undirected");
    }
}
