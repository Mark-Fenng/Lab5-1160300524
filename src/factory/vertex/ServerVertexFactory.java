package factory.vertex;

import Exception.Vertex.VertexAttributeException;
import vertex.Server;
import vertex.Vertex;

class ServerVertexFactory {
    static Vertex createVertex(String label, String[] args) throws VertexAttributeException {
        Vertex newVertex = new Server(label);
        newVertex.fillVertexInfo(args);
        return newVertex;
    }
}
