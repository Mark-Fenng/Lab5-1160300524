package graph;

import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeNullVertexException;
import Exception.Edge.EdgeWeightException;
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
    public boolean addEdge(Edge edge) throws EdgeNullVertexException, EdgeTypeException, EdgeWeightException {
        if (!((edge instanceof ForwardConnection) || (edge instanceof FriendConnection) || (edge instanceof CommentConnection)))
            throw new EdgeTypeException(getLabel());

        // 避免单重边中存在多充边，如果存在，就不添加这条边
        if (super.edges().stream().filter(item -> item.equals(edge)).count() != 0)
            return false;

        if (!super.edges().contains(edge)) {
            double newWeight = edge.getWeight();
            for (Edge item : super.edges)
                item.setWeight(item.getWeight() * (1.0 - newWeight));
            for (Edge item : super.edges) {
                if (item.getWeight() < 0 || item.getWeight() > 1)
                    throw new EdgeWeightException(getLabel(), "" + item.getWeight());
            }
            return super.addEdge(edge);
        }
        return false;
    }

    @Override
    public boolean removeEdge(Edge edge) throws EdgeWeightException {
        if (super.edges().contains(edge)) {
            double weight = edge.getWeight();
            if (super.edges().size() != 1) {
                for (Edge item : super.edges())
                    item.setWeight(weight / (1.0 - weight));
            }
            return super.removeEdge(edge);
        }
        return false;
    }
}
