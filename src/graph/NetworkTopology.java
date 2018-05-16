package graph;

import Exception.Vertex.VertexTypeException;
import vertex.*;

public class NetworkTopology extends ConcreteGraph {
    public NetworkTopology(String label) {
        super(label);
    }

    @Override
    public boolean addVertex(Vertex vertex) throws VertexTypeException {
        if (!((vertex instanceof Computer) || (vertex instanceof Router) || (vertex instanceof Server) || (vertex instanceof WirelessRouter)))
            throw new VertexTypeException(vertex.getLabel());
        return super.addVertex(vertex);
    }
}
