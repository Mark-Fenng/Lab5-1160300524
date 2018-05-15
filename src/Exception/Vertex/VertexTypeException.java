package Exception.Vertex;

public class VertexTypeException extends Exception {
    public VertexTypeException(String message) {
        super(message);
    }

    public VertexTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public VertexTypeException(Throwable cause) {
        super(cause);
    }
}
