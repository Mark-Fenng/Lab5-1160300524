package factory.graph;

import factory.Exception.FormatException;
import factory.edge.EdgeFactory;
import graph.Graph;
import graph.GraphPoet;
import vertex.Vertex;
import factory.vertex.VertexFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GraphPoetFactory {
    public static Graph createGraph(String filePath) throws IOException, FormatException {
        Graph poet;
        // graph name
        String graphName = GraphFactory.GraphLabel(filePath);
        poet = new GraphPoet(graphName);
        // get Vertices from the file
        List<List<String>> vertexCut = GraphFactory.getVertices(filePath);
        List<Vertex> vertices = new ArrayList<>();
        for (List<String> list : vertexCut) {
            Vertex newVertex = VertexFactory.createVertex(list.get(0), list.get(1), null);
            vertices.add(newVertex);
            poet.addVertex(newVertex);
        }
        List<List<String>> edgeCut = GraphFactory.getEdges(filePath);
        for (List<String> list : edgeCut) {
            List<Vertex> vertexInEdge = new ArrayList<>();
            vertexInEdge.addAll(vertices.stream().filter(item -> item.getLabel().equals(list.get(3))).collect(Collectors.toList()));
            vertexInEdge.addAll(vertices.stream().filter(item -> item.getLabel().equals(list.get(4))).collect(Collectors.toList()));
            poet.addEdge(EdgeFactory.createEdge(list.get(0), list.get(1), vertexInEdge, Double.parseDouble(list.get(2))));
        }
        return poet;
    }
}
