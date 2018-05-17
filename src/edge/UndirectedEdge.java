package edge;

import Exception.Edge.EdgeLoopException;
import Exception.Edge.EdgeVertexTypeException;
import vertex.Vertex;

import java.util.List;
import java.util.Set;

public class UndirectedEdge extends Edge {
    UndirectedEdge(String label, double weight) {
        super(label, weight);
    }

    @Override
    public boolean addVertices(List<Vertex> vertices) throws EdgeLoopException, EdgeVertexTypeException {
        if (vertices.size() == 2) {
            if (super.vertices.size() == 0) {
                super.vertices.addAll(vertices);
                return true;
            }
            return false;
        } else {
            throw new RuntimeException("the directed edge must be two vertices");
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
}
