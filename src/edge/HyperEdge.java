package edge;

import Exception.Edge.EdgeVertexTypeException;
import vertex.Vertex;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HyperEdge extends Edge {

    HyperEdge(String label, double weight) {
        super(label, weight);
        super.setWeight(-1);
    }

    @Override
    public boolean addVertices(List<Vertex> vertices) throws EdgeVertexTypeException {
        if (vertices.size() > 0) {
            if (super.vertices.size() == 0) {
                super.vertices.addAll(vertices.stream().filter(item -> !super.vertices.contains(item)).collect(Collectors.toList()));
                return true;
            }
            return false;
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
}
