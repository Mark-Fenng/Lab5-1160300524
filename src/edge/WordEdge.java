package edge;

import Exception.Edge.EdgeLoopException;
import Exception.Edge.EdgeVertexTypeException;
import Exception.Edge.EdgeWeightException;
import vertex.Vertex;
import vertex.Word;

import java.util.List;

public class WordEdge extends DirectedEdge {
    public WordEdge(String label, double weight) throws EdgeWeightException {
        super(label, weight);
        try {
            if (Math.abs((int) weight - weight) > 0.001 || weight < 0)
                throw new EdgeWeightException(getLabel(), "" + weight);
        } catch (NumberFormatException e) {
            throw new EdgeWeightException(getLabel(), "" + weight);
        }
    }

    @Override
    public double setWeight(double weight) throws EdgeWeightException {
        try {
            if (Math.abs((int) weight - weight) > 0.001 || weight < 0)
                throw new EdgeWeightException(getLabel(), "" + weight);
        } catch (NumberFormatException e) {
            throw new EdgeWeightException(getLabel(), "" + weight);
        }
        return super.setWeight(weight);
    }

    @Override
    public boolean addVertices(List<Vertex> vertices) throws EdgeLoopException, EdgeVertexTypeException {
        // 出现边中点的类型异常时抛出异常
        if (vertices.stream().filter(item -> !(item instanceof Word)).count() != 0)
            throw new EdgeVertexTypeException(getLabel());
        return super.addVertices(vertices);
    }
}
