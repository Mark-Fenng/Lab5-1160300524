package factory.vertex;

import Exception.Vertex.VertexAttributeException;
import vertex.Router;
import vertex.Vertex;

class RouterVertexFactory {
    static Vertex createVertex(String label, String[] args) throws VertexAttributeException {
        Vertex newVertex = new Router(label);
        newVertex.fillVertexInfo(args);
        return newVertex;
    }
}
