package graph;

import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeNullVertexException;
import Exception.Vertex.VertexTypeException;
import edge.*;
import vertex.*;

public class MovieGraph extends ConcreteGraph {
    public MovieGraph(String label) {
        super(label);
    }

    @Override
    public boolean addVertex(Vertex vertex) throws VertexTypeException {
        if (!((vertex instanceof Actor) || (vertex instanceof Director) || (vertex instanceof Movie)))
            throw new VertexTypeException(vertex.getLabel());
        return super.addVertex(vertex);
    }

    @Override
    public boolean addEdge(Edge edge) throws EdgeNullVertexException, EdgeTypeException {
        if (!((edge instanceof DirectedEdge) || (edge instanceof MovieActorRelation) || (edge instanceof MovieDirectorRelation) || (edge instanceof SameMovieHyperEdge)))
            throw new EdgeTypeException(getLabel());
        // 避免单重边中存在多充边，如果存在，就不添加这条边
        return super.edges().stream().filter(item -> item.equals(edge)).count() == 0 && super.addEdge(edge);
    }

    @Override
    public boolean removeVertex(Vertex vertex) {
        if (vertices.remove(vertex)) {
            // 删除超边中的相应顶点，但不删除这条超边
            edges.stream().filter(item -> (item instanceof HyperEdge) && item.vertices().contains(vertex)).forEach(item -> ((HyperEdge) item).removeVertex(vertex));
            // 删除不是超边的 包含要删除点的 边 或者边中点的个数小于2的边
            edges.removeIf(item -> (!(item instanceof HyperEdge) && item.vertices().contains(vertex)) || item.vertices().size() < 2);
            return true;
        }
        return false;
    }
}
