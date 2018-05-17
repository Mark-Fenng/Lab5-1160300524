package application;

import Exception.*;
import Exception.Edge.DirectedEdgeException;
import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeNullVertexException;
import Exception.Edge.UndirectedEdgeException;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexTypeException;
import helper.ParseCommandHelper;

import java.io.IOException;

class SocialNetworkApp {

    public static void main(String[] args) throws IOException, VertexAttributeException, EdgeNullVertexException, TypeException, FormatException, VertexTypeException, EdgeTypeException, UndirectedEdgeException, DirectedEdgeException {
        new NetworkTopologyApp("test/graph/data/GraphSocial.txt");
    }

    SocialNetworkApp(String filePath) throws IOException, EdgeNullVertexException, VertexAttributeException, TypeException, FormatException, VertexTypeException, EdgeTypeException, UndirectedEdgeException, DirectedEdgeException {
        ParseCommandHelper.Command(filePath);
    }
}
