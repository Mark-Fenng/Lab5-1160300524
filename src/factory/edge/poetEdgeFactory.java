package factory.edge;

import Exception.Edge.EdgeLoopException;
import Exception.Edge.EdgeVertexTypeException;
import Exception.Edge.EdgeWeightException;
import edge.Edge;
import edge.WordEdge;
import vertex.Vertex;

import java.util.List;

class poetEdgeFactory {
    static Edge createEdge(String label, List<Vertex> vertices, double weight) throws EdgeVertexTypeException, EdgeLoopException, EdgeWeightException {
        Edge wordEdge = new WordEdge(label, weight);
        wordEdge.addVertices(vertices);
        return wordEdge;
    }
}
