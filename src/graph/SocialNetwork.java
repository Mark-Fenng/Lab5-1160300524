package graph;

import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeNullVertexException;
import Exception.Edge.EdgeWeightException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
import edge.CommentTie;
import edge.Edge;
import edge.ForwardTie;
import edge.FriendTie;
import vertex.Person;
import vertex.Vertex;

public class SocialNetwork extends ConcreteGraph {
    public SocialNetwork(String label) {
        super(label);
    }

    private void checkRep() {
        double sum = 0;
        if (edges.size() != 0) {
            for (Edge item : edges()) {
                sum += item.getWeight();
            }
            assert !(Math.abs(sum - 1) > 0.001);
        }
    }

    @Override
    public boolean addVertex(Vertex vertex) throws VertexTypeException, VertexLabelException {
        if (!(vertex instanceof Person))
            throw new VertexTypeException(vertex.getLabel());
//        checkRep();
        return super.addVertex(vertex);
    }

    @Override
    public boolean addEdge(Edge edge) throws EdgeNullVertexException, EdgeTypeException, EdgeWeightException {
//        checkRep();
        if (!((edge instanceof ForwardTie) || (edge instanceof FriendTie) || (edge instanceof CommentTie)))
            throw new EdgeTypeException(getLabel());

        // 避免单重边中存在多充边，如果存在，就不添加这条边
        if (super.edges().stream().filter(item -> item.equals(edge)).count() != 0)
            return false;

        if (!super.edges().contains(edge)) {
            double newWeight = edge.getWeight();
            if (edges.size() == 0) { // 如果图中只有一条边，那么这条边的weight必须是1
                edge.setWeight(1);
            } else {
                for (Edge item : super.edges)
                    item.setWeight(item.getWeight() * (1.0 - newWeight));
                for (Edge item : super.edges) {
                    if (item.getWeight() < 0 || item.getWeight() > 1)
                        throw new EdgeWeightException(getLabel(), "" + item.getWeight());
                }
            }
            return super.addEdge(edge);
        }
        return false;
    }

    @Override
    public boolean removeEdge(Edge edge) throws EdgeWeightException {
//        checkRep();
        if (super.edges().contains(edge)) {
            double weight = edge.getWeight();
            if (super.edges().size() != 1) {
                for (Edge item : super.edges())
                    item.setWeight(weight / (1.0 - weight));
            }
            return super.removeEdge(edge);
        }
//        checkRep();
        return false;
    }
}
