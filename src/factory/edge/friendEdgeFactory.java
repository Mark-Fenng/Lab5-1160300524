package factory.edge;

import Exception.Edge.EdgeLoopException;
import Exception.Edge.EdgeVertexTypeException;
import edge.Edge;
import edge.FriendTie;
import vertex.Person;
import vertex.Vertex;

import java.util.List;

class friendEdgeFactory {
    static Edge createEdge(String label, List<Vertex> vertices, double weight) throws EdgeLoopException, EdgeVertexTypeException {
        Edge friendConnection = new FriendTie(label, weight);
        if (vertices.get(0).equals(vertices.get(1))) {
            throw new EdgeLoopException(label);
        }
        // 出现边中点的类型异常时抛出异常
        if (vertices.stream().filter(item -> !(item instanceof Person)).count() != 0)
            throw new EdgeVertexTypeException(label);
        friendConnection.addVertices(vertices);
        return friendConnection;
    }
}
