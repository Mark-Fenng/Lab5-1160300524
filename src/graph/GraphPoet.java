package graph;

import Exception.Vertex.VertexTypeException;
import vertex.Vertex;
import vertex.Word;

public class GraphPoet extends ConcreteGraph {
    public GraphPoet(String label) {
        super(label);
    }

    @Override
    public boolean addVertex(Vertex vertex) throws VertexTypeException {
        if (!(vertex instanceof Word))
            throw new VertexTypeException(vertex.getLabel());
        return super.addVertex(vertex);
    }
}
