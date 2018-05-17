package application;

import Exception.*;
import Exception.Edge.*;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexTypeException;
import helper.ParseCommandHelper;

import java.io.IOException;

class SocialNetworkApp {

    public static void main(String[] args) throws IOException, VertexAttributeException, EdgeNullVertexException, TypeException, FormatException, VertexTypeException, EdgeTypeException, UndirectedEdgeException, DirectedEdgeException, HyperEdgeException {
        new NetworkTopologyApp("test/graph/data/GraphSocial.txt");
    }

    SocialNetworkApp(String filePath) throws IOException, EdgeNullVertexException, VertexAttributeException, TypeException, FormatException, VertexTypeException, EdgeTypeException, UndirectedEdgeException, DirectedEdgeException, HyperEdgeException {
        ParseCommandHelper.Command(filePath);
    }
}
