package factory.vertex;

import Exception.Vertex.VertexAttributeException;
import vertex.Vertex;

abstract public class VertexFactory {
    public static Vertex createVertex(String label, String type, String[] args) throws VertexAttributeException {
        switch (type) {
            case "Word":
                return WordVertexFactory.createVertex(label, args);
            case "Computer":
                return ComputerVertexFactory.createVertex(label, args);
            case "Router":
                return RouterVertexFactory.createVertex(label, args);
            case "Server":
                return ServerVertexFactory.createVertex(label, args);
            case "Person":
                return PersonVertexFactory.createVertex(label, args);
            case "Actor":
                return ActorVertexFactory.createVertex(label, args);
            case "Movie":
                return MovieVertexFactory.createVertex(label, args);
            case "Director":
                return DirectorVertexFactory.createVertex(label, args);
            case "":
                return WirelessRouterFactory.createVertex(label, args);
            default:
                return null;
        }
    }
}
