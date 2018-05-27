package graph;

import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeNullVertexException;
import Exception.Edge.EdgeWeightException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
import edge.Edge;
import edge.WordNeighborhood;
import vertex.Vertex;
import vertex.Word;

public class GraphPoet extends ConcreteGraph {
    public GraphPoet(String label) {
        super(label);
    }

    @Override
    public boolean addVertex(Vertex vertex) throws VertexTypeException, VertexLabelException {
        if (!(vertex instanceof Word))
            throw new VertexTypeException(vertex.getLabel());
        return super.addVertex(vertex);
    }

    @Override
    public boolean addEdge(Edge edge) throws EdgeNullVertexException, EdgeTypeException, EdgeWeightException{
        if (!(edge instanceof WordNeighborhood))
            throw new EdgeTypeException(getLabel());
        // 避免单重边中存在多充边，如果存在，就不添加这条边
        return super.edges().stream().filter(item -> item.equals(edge)).count() == 0 && super.addEdge(edge);
    }
}
