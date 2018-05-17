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
}
