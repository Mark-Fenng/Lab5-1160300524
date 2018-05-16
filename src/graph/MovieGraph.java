package graph;

import Exception.Vertex.VertexTypeException;
import vertex.*;

public class MovieGraph extends ConcreteGraph {
    public MovieGraph(String label) {
        super(label);
    }

    @Override
    public boolean addVertex(Vertex vertex) throws VertexTypeException {
        if (!((vertex instanceof Actor) || (vertex instanceof Director) || (vertex instanceof Movie)))
            throw new VertexTypeException(vertex.getLabel());
        return super.addVertex(vertex);
    }
}
