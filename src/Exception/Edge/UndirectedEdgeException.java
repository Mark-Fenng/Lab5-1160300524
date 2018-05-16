package Exception.Edge;

public class UndirectedEdgeException extends Exception {
    public UndirectedEdgeException(String EdgeLabel, int LineNumber) {
        super("Edge Attribute Error Occur at Line " + LineNumber + "\n"
                + "The Edge : \"" + EdgeLabel + "\" can't be directed");
    }
}
