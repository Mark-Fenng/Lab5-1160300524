package graph;

import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeVertexException;
import Exception.Vertex.VertexTypeException;
import edge.Edge;
import edge.WordEdge;
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

    @Override
    public boolean addEdge(Edge edge) throws EdgeVertexException, EdgeTypeException {
        if (!(edge instanceof WordEdge))
            throw new EdgeTypeException(getLabel());
        return super.addEdge(edge);
    }
}
