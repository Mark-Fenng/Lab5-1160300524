package application;

import Exception.*;
import Exception.Edge.EdgeVertexException;
import Exception.Vertex.VertexAttributeException;
import helper.ParseCommandHelper;

import java.io.IOException;

class SocialNetworkApp {

    public static void main(String[] args) throws IOException, VertexAttributeException, EdgeVertexException, TypeException, FormatException {
        new NetworkTopologyApp("test/graph/data/GraphSocial.txt");
    }

    SocialNetworkApp(String filePath) throws IOException, EdgeVertexException, VertexAttributeException, TypeException, FormatException {
        ParseCommandHelper.Command(filePath);
    }
}
