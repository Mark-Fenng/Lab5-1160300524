package application;

import Exception.*;
import Exception.Edge.*;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
import graph.Graph;
import factory.graph.GraphTopologyFactory;
import helper.ParseCommandHelper;

import java.io.IOException;
import java.util.Collection;

class NetworkTopologyApp {
    private Graph NetworkTopolopyApp;

    public static void main(String[] args) throws IOException, FormatException, EdgeNullVertexException, VertexAttributeException, TypeException, VertexTypeException, EdgeTypeException, UndirectedEdgeException, DirectedEdgeException, HyperEdgeException, EdgeWeightException, VertexLabelException {
        new NetworkTopologyApp("test/graph/data/GraphTopology.txt");
    }

    NetworkTopologyApp(String filePath) throws IOException, FormatException, VertexAttributeException, EdgeNullVertexException, TypeException, VertexTypeException, EdgeTypeException, UndirectedEdgeException, DirectedEdgeException, HyperEdgeException, EdgeWeightException, VertexLabelException {
        NetworkTopolopyApp = GraphTopologyFactory.createGraph(filePath);
        ParseCommandHelper.Command(filePath);
//        iterator("vertices", NetworkTopolopyApp.vertices());
//        iterator("edges", NetworkTopolopyApp.edges());
//        for (Vertex a : NetworkTopolopyApp.vertices()) {
//            iterator("sources " + a.getLabel() + " :", NetworkTopolopyApp.sources(a).keySet());
//            iterator("targets " + a.getLabel() + " :", NetworkTopolopyApp.targets(a).keySet());
//        }
    }

    private static void iterator(String title, Collection<?> a) {
        System.out.println(title);
        for (Object item : a) {
            System.out.println(item);
        }
    }
}
