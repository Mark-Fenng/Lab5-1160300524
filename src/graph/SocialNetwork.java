package graph;

import Exception.Edge.EdgeVertexException;
import edge.Edge;

public class SocialNetwork extends ConcreteGraph {
    public SocialNetwork(String label) {
        super(label);
    }

    @Override
    public boolean addEdge(Edge edge) throws EdgeVertexException {
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
