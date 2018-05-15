package factory.vertex;

import Exception.Vertex.VertexAttributeException;
import vertex.Movie;
import vertex.Vertex;

class MovieVertexFactory {
    static Vertex createVertex(String label, String[] args) throws VertexAttributeException {
        Vertex newVertex = new Movie(label);
        newVertex.fillVertexInfo(args);
        return newVertex;
    }
}
