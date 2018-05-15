package application;

import Exception.*;
import Exception.Edge.EdgeVertexException;
import Exception.Vertex.VertexAttributeException;
import helper.ParseCommandHelper;

import java.io.IOException;

class MovieGraphApp {

    public static void main(String[] args) throws IOException, EdgeVertexException, VertexAttributeException, TypeException, FormatException {
        new MovieGraphApp("test/graph/data/GraphMovie.txt");
    }

    MovieGraphApp(String filePath) throws IOException, EdgeVertexException, VertexAttributeException, TypeException, FormatException {
        ParseCommandHelper.Command(filePath);
    }
}
