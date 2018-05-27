package edge;

import Exception.Edge.EdgeVertexTypeException;
import Exception.Edge.EdgeWeightException;
import vertex.Vertex;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HyperEdge extends Edge {

    HyperEdge(String label, double weight) throws EdgeWeightException {
        super(label, weight);
        super.setWeight(-1);
    }

    @Override
    public boolean addVertices(List<Vertex> vertices) throws EdgeVertexTypeException {
        if (vertices.size() > 0) {
            super.vertices.addAll(vertices.stream().filter(item -> !super.vertices.contains(item)).collect(Collectors.toList()));
            return true;
        } else {
            throw new RuntimeException("the hyper edge must have one more vertex");
        }
    }

    @Override
    public Set<Vertex> sourceVertices() {
        return super.vertices();
    }

    @Override
    public Set<Vertex> targetVertices() {
        return super.vertices();
    }

    public boolean removeVertex(Vertex vertex) {
        return vertices.remove(vertex);
    }

    @Override
    public String toString() {
        StringBuilder vertices = new StringBuilder();
        super.vertices.forEach(item -> vertices.append("\"").append(item).append("\", "));
        return "\"" + getLabel() + "\", \"" +
                getClass().getName().split("\\.")[getClass().getName().split("\\.").length - 1]
                + "\", " + "{" + vertices.substring(0, vertices.length() - 2) + "}";
    }
}
