package factory.edge;

import Exception.Edge.EdgeLoopException;
import Exception.Edge.EdgeVertexTypeException;
import edge.Edge;
import edge.MovieActorRelation;
import vertex.Actor;
import vertex.Movie;
import vertex.Vertex;

import java.util.List;

class MovieActorEdgeFactory {
    static Edge createEdge(String label, List<Vertex> vertices, double weight) throws EdgeLoopException, EdgeVertexTypeException {
        Edge MovieActorEdge = new MovieActorRelation(label, weight);
        if (vertices.get(0).equals(vertices.get(1))) {
            throw new EdgeLoopException(label);
        }
        // 如果两个点的类型相同，抛出异常
        if (vertices.get(0).getClass().getName().equals(vertices.get(1).getClass().getName()))
            throw new EdgeVertexTypeException(label);
        // 出现边中点的类型异常时抛出异常
        if (vertices.stream().filter(item -> !((item instanceof Actor) || (item instanceof Movie))).count() != 0)
            throw new EdgeVertexTypeException(label);
        MovieActorEdge.addVertices(vertices);
        return MovieActorEdge;
    }
}
