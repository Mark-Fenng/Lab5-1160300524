package edge;

import Exception.Edge.EdgeLoopException;
import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeVertexTypeException;
import vertex.Vertex;
import vertex.Word;

import java.util.List;

public class WordEdge extends DirectedEdge {
    public WordEdge(String label, double weight) {
        super(label, weight);
    }

    @Override
    public boolean addVertices(List<Vertex> vertices) throws EdgeLoopException, EdgeVertexTypeException {
        // 出现边中点的类型异常时抛出异常
        if (vertices.stream().filter(item -> !(item instanceof Word)).count() != 0)
            throw new EdgeVertexTypeException(getLabel());
        return super.addVertices(vertices);
    }
}
