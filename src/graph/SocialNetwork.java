package graph;

import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeVertexException;
import Exception.Vertex.VertexTypeException;
import edge.CommentConnection;
import edge.Edge;
import edge.ForwardConnection;
import edge.FriendConnection;
import vertex.Person;
import vertex.Vertex;

public class SocialNetwork extends ConcreteGraph {
    public SocialNetwork(String label) {
        super(label);
    }

    @Override
    public boolean addVertex(Vertex vertex) throws VertexTypeException {
        if (!(vertex instanceof Person))
            throw new VertexTypeException(vertex.getLabel());
        return super.addVertex(vertex);
    }

    @Override
    public boolean addEdge(Edge edge) throws EdgeVertexException, EdgeTypeException {
        if (!((edge instanceof ForwardConnection) || (edge instanceof FriendConnection) || (edge instanceof CommentConnection)))
            throw new EdgeTypeException(getLabel());
        if (!super.edges().contains(edge)) {
            double newWeight = edge.getWeight();
            super.edges().forEach(item -> item.setWeight(item.getWeight() * (1.0 - newWeight)));
            return super.addEdge(edge);
        }
        return false;
    }

    @Override
    public boolean removeEdge(Edge edge) {
        if (super.edges().contains(edge)) {
            double weight = edge.getWeight();
            if (super.edges().size() != 1) {
                super.edges().forEach(item -> item.setWeight(weight / (1.0 - weight)));
            }
            return super.removeEdge(edge);
        }
        return false;
    }
}
