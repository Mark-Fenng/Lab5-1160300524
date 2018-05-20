package edge;

import Exception.Edge.EdgeLoopException;
import Exception.Edge.EdgeVertexTypeException;
import Exception.Edge.EdgeWeightException;
import vertex.Actor;
import vertex.Movie;
import vertex.Vertex;

import java.util.List;

/**
 * RI: weight>0
 */
public class MovieActorRelation extends UndirectedEdge {
    public MovieActorRelation(String label, double weight) throws EdgeWeightException {
        super(label, weight);
        if ((int) weight <= 0)
            throw new EdgeWeightException(label, "" + weight);
    }

    private void checkRep() {
        assert (getWeight() > 0);
    }

    @Override
    public boolean addVertices(List<Vertex> vertices) throws EdgeLoopException, EdgeVertexTypeException {
        // 出现loop时抛出异常
        if (vertices.get(0).equals(vertices.get(1))) {
            throw new EdgeLoopException(getLabel());
        }
        // 如果两个点的类型相同，抛出异常
        if (vertices.get(0).getClass().getName().equals(vertices.get(1).getClass().getName()))
            throw new EdgeVertexTypeException(getLabel());
        // 出现边中点的类型异常时抛出异常
        if (vertices.stream().filter(item -> !((item instanceof Actor) || (item instanceof Movie))).count() != 0)
            throw new EdgeVertexTypeException(getLabel());
        return super.addVertices(vertices);
    }
}
