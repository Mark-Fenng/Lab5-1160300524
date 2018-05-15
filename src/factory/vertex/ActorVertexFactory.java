package factory.vertex;

import Exception.Vertex.VertexAttributeException;
import vertex.Actor;
import vertex.Computer;
import vertex.Vertex;

class ActorVertexFactory {
    static Vertex createVertex(String label, String[] args) throws VertexAttributeException {
        Vertex newVertex = new Actor(label);
        newVertex.fillVertexInfo(args);
        return newVertex;
    }
}
