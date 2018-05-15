package factory.vertex;

import Exception.Vertex.VertexAttributeException;
import vertex.Person;
import vertex.Vertex;

class PersonVertexFactory {
    static Vertex createVertex(String label, String[] args) throws VertexAttributeException {
        Vertex newVertex = new Person(label);
        newVertex.fillVertexInfo(args);
        return newVertex;
    }
}
