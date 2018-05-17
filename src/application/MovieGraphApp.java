package application;

import Exception.*;
import Exception.Edge.*;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
import helper.ParseCommandHelper;

import java.io.IOException;

class MovieGraphApp {

    public static void main(String[] args) throws IOException, EdgeNullVertexException, VertexAttributeException, TypeException, FormatException, VertexTypeException, EdgeTypeException, UndirectedEdgeException, DirectedEdgeException, HyperEdgeException, EdgeWeightException, VertexLabelException {
        new MovieGraphApp("test/graph/data/GraphMovie.txt");
    }

    MovieGraphApp(String filePath) throws IOException, EdgeNullVertexException, VertexAttributeException, TypeException, FormatException, VertexTypeException, EdgeTypeException, UndirectedEdgeException, DirectedEdgeException, HyperEdgeException, EdgeWeightException, VertexLabelException {
        ParseCommandHelper.Command(filePath);
    }
}
