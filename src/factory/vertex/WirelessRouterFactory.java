package factory.vertex;

import Exception.Vertex.VertexAttributeException;
import vertex.Vertex;
import vertex.WirelessRouter;

class WirelessRouterFactory {
    static Vertex createVertex(String label, String[] args) throws VertexAttributeException {
        Vertex newVertex = new WirelessRouter(label);
        newVertex.fillVertexInfo(args);
        return newVertex;
    }
}
