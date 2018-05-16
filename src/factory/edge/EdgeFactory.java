package factory.edge;

import Exception.Edge.DirectedEdgeException;
import Exception.*;
import edge.Edge;
import vertex.Vertex;

import java.util.List;

public class EdgeFactory {
    public static Edge createEdge(String label, String type, List<Vertex> vertices, double weight) throws FormatException {
        switch (type) {
            case "WordNeighborhood":
                return poetEdgeFactory.createEdge(label, vertices, weight);
            case "NetworkConnection":
                return networkEdgeFactory.createEdge(label, vertices, weight);
            case "ForwardTie":
                return forwardEdgeFactory.createEdge(label, vertices, weight);
            case "FriendTie":
                return friendEdgeFactory.createEdge(label, vertices, weight);
            case "CommentTie":
                return commentEdgeFactory.createEdge(label, vertices, weight);
            case "MovieDirectorRelation":
                return MovieDirectorEdgeFactory.createEdge(label, vertices, weight);
            case "MovieActorRelation":
                return MovieActorEdgeFactory.createEdge(label, vertices, weight);
            case "SameMovieHyperEdge":
                return SameMovieHyperEdgeFactory.createEdge(label, vertices, weight);
            default:
                throw new FormatException("The Edge Type is not Supported");
        }
    }

    public static boolean EdgeType(String label, String type, boolean directed) throws DirectedEdgeException, FormatException {
        switch (type) {
            case "WordNeighborhood":
                return directed;
            case "NetworkConnection":
            case "ForwardTie":
            case "FriendTie":
            case "CommentTie":
                if (directed)
                    throw new DirectedEdgeException(label);
                return true;
            case "MovieDirectorRelation":
            case "MovieActorRelation":
                return directed;
            case "SameMovieHyperEdge":
                return true;
            default:
                throw new FormatException("The Edge Type is not Supported");
        }
    }
}
