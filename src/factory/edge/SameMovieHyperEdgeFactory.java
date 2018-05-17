package factory.edge;

import Exception.Edge.EdgeLoopException;
import Exception.Edge.EdgeVertexTypeException;
import edge.Edge;
import edge.SameMovieHyperEdge;
import vertex.Actor;
import vertex.Vertex;

import java.util.List;

class SameMovieHyperEdgeFactory {
    static Edge createEdge(String label, List<Vertex> vertices, double weight) throws EdgeLoopException, EdgeVertexTypeException {
        Edge SameMovieHyper = new SameMovieHyperEdge(label, weight);
        // 出现边中点的类型异常时抛出异常
        if (vertices.stream().filter(item -> !(item instanceof Actor)).count() != 0)
            throw new EdgeVertexTypeException(label);
        SameMovieHyper.addVertices(vertices);
        return SameMovieHyper;
    }
}
