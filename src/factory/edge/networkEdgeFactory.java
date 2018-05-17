package factory.edge;

import Exception.Edge.EdgeLoopException;
import Exception.Edge.EdgeVertexTypeException;
import edge.Edge;
import edge.NetworkConnection;
import vertex.*;

import java.util.List;

class networkEdgeFactory {
    static Edge createEdge(String label, List<Vertex> vertices, double weight) throws EdgeLoopException, EdgeVertexTypeException {
        Edge networkEdge = new NetworkConnection(label, weight);
        if (vertices.get(0).equals(vertices.get(1))) {
            throw new EdgeLoopException(label);
        }
        // 除了Router和WirelessRouter类型的两个相同类型的点可以出现在边中，其他的相同类型的两个点出现在边中时，抛出异常
        if (vertices.get(0).getClass().getName()
                .equals(vertices.get(1).getClass().getName()) && !(vertices.get(0) instanceof Router) && !(vertices.get(0) instanceof WirelessRouter))
            throw new EdgeVertexTypeException(label);
        // 出现边中点的类型异常时抛出异常
        if (vertices.stream()
                .filter(item -> !((item instanceof Computer) || (item instanceof Router) || (item instanceof Server) || (item instanceof WirelessRouter)))
                .count() != 0)
            throw new EdgeVertexTypeException(label);
        networkEdge.addVertices(vertices);
        return networkEdge;
    }
}
