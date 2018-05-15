package factory.vertex;

import Exception.Vertex.VertexAttributeException;
import vertex.Computer;
import vertex.Director;
import vertex.Vertex;

class DirectorVertexFactory {
    static Vertex createVertex(String label, String[] args) throws VertexAttributeException {
        Vertex newVertex = new Director(label);
        newVertex.fillVertexInfo(args);
        return newVertex;
    }
}
