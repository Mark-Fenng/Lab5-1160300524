package edge;

import Exception.Edge.EdgeLoopException;
import Exception.Edge.EdgeVertexTypeException;
import vertex.*;

import java.util.List;

public class NetworkConnection extends UndirectedEdge {
    public NetworkConnection(String label, double weight) {
        super(label, weight);
    }

    @Override
    public boolean addVertices(List<Vertex> vertices) throws EdgeLoopException, EdgeVertexTypeException {
        // 出现loop时抛出异常
        if (vertices.get(0).equals(vertices.get(1))) {
            throw new EdgeLoopException(getLabel());
        }
        // 除了Router和WirelessRouter类型的两个相同类型的点可以出现在边中，其他的相同类型的两个点出现在边中时，抛出异常
        if (vertices.get(0).getClass().getName()
                .equals(vertices.get(1).getClass().getName()) && !(vertices.get(0) instanceof Router) && !(vertices.get(0) instanceof WirelessRouter))
            throw new EdgeVertexTypeException(getLabel());
        // 出现边中点的类型异常时抛出异常
        if (vertices.stream()
                .filter(item -> !((item instanceof Computer) || (item instanceof Router) || (item instanceof Server) || (item instanceof WirelessRouter)))
                .count() != 0)
            throw new EdgeVertexTypeException(getLabel());
        return super.addVertices(vertices);
    }
}
