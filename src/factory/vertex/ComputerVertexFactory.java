package factory.vertex;

import Exception.Vertex.VertexAttributeException;
import vertex.Computer;
import vertex.Vertex;

class ComputerVertexFactory {
    static Vertex createVertex(String label, String[] args) throws VertexAttributeException {
        Vertex newVertex = new Computer(label);
        newVertex.fillVertexInfo(args);
        return newVertex;
    }
}
