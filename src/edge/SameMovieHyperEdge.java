package edge;

import Exception.Edge.EdgeVertexTypeException;
import Exception.Edge.EdgeWeightException;
import vertex.Actor;
import vertex.Vertex;

import java.util.List;

public class SameMovieHyperEdge extends HyperEdge {
    public SameMovieHyperEdge(String label, double weight) throws EdgeWeightException {
        super(label, weight);
    }

    @Override
    public boolean addVertices(List<Vertex> vertices) throws EdgeVertexTypeException {
        // 出现边中点的类型异常时抛出异常
        if (vertices.stream().filter(item -> !(item instanceof Actor)).count() != 0)
            throw new EdgeVertexTypeException(getLabel());
        return super.addVertices(vertices);
    }
}
