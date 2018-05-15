package Exception.Vertex;

public class VertexAttributeException extends Exception {
    public VertexAttributeException(String message) {
        super(message);
    }

    public VertexAttributeException(String message, Throwable cause) {
        super(message, cause);
    }

    public VertexAttributeException(Throwable cause) {
        super(cause);
    }
}
