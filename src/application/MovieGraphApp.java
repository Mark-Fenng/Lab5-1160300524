package application;

import Exception.*;
import Exception.Edge.EdgeVertexException;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexTypeException;
import helper.ParseCommandHelper;

import java.io.IOException;

class MovieGraphApp {

    public static void main(String[] args) throws IOException, EdgeVertexException, VertexAttributeException, TypeException, FormatException, VertexTypeException {
        new MovieGraphApp("test/graph/data/GraphMovie.txt");
    }

    MovieGraphApp(String filePath) throws IOException, EdgeVertexException, VertexAttributeException, TypeException, FormatException, VertexTypeException {
        ParseCommandHelper.Command(filePath);
    }
}
