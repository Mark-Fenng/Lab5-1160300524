package graph;

import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeVertexException;
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
    public boolean addEdge(Edge edge) throws EdgeVertexException, EdgeTypeException {
        if (!((edge instanceof DirectedEdge) || (edge instanceof MovieActorRelation) || (edge instanceof MovieDirectorRelation) || (edge instanceof SameMovieHyperEdge)))
            throw new EdgeTypeException(getLabel());
        return super.addEdge(edge);
    }
}
