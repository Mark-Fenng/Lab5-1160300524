package edge;

import Exception.Edge.EdgeLoopException;
import Exception.Edge.EdgeVertexTypeException;
import vertex.Vertex;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * RI:
 * 所有点!=null
 */
public class DirectedEdge extends Edge {
    DirectedEdge(String label, double weight) {
        super(label, weight);
    }

    private void checkRep() {
        for (Vertex item : vertices) {
            assert (item != null);
        }
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
            throw new RuntimeException("the undirected edge must be two vertices");
        }
    }

    @Override
    public Set<Vertex> sourceVertices() {
        Set<Vertex> source = new HashSet<>();
        if (super.vertices.size() != 0)
            source.add(super.vertices.get(0));
        return source;
    }

    @Override
    public Set<Vertex> targetVertices() {
        Set<Vertex> target = new HashSet<>();
        if (super.vertices.size() != 0)
            target.add(super.vertices.get(1));
        return target;
    }

    @Override
    public String toString() {
        return super.toString() + ", \"" + "Yes" + "\"";
    }
}
