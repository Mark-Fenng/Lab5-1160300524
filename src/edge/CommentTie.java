package edge;

import Exception.Edge.EdgeLoopException;
import Exception.Edge.EdgeVertexTypeException;
import vertex.Person;
import vertex.Vertex;

import java.util.List;

/**
 * RI: weight在[0,1]
 */
public class CommentTie extends DirectedEdge {
    public CommentTie(String label, double weight) {
        super(label, weight);
    }

    private void checkRep() {
        assert (getWeight() < 0 || getWeight() > 1);
        assert vertices().size() == 0 || (vertices.get(0).equals(vertices.get(1)));
    }

    @Override
    public boolean addVertices(List<Vertex> vertices) throws EdgeLoopException, EdgeVertexTypeException {
        // 出现边中点的类型异常时抛出异常
        if (vertices.stream().filter(item -> !(item instanceof Person)).count() != 0)
            throw new EdgeVertexTypeException(getLabel());
        return super.addVertices(vertices);
    }
}
