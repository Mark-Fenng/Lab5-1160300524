package factory.graph;

import Exception.*;
import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeVertexException;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexTypeException;
import factory.edge.EdgeFactory;
import graph.Graph;
import graph.NetworkTopology;
import vertex.Vertex;
import factory.vertex.VertexFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GraphTopologyFactory {
    public static Graph createGraph(String filePath) throws IOException, FormatException, EdgeVertexException, VertexAttributeException, VertexTypeException, EdgeTypeException {
        Graph NetworkTopology;
        Pattern regex;
        Matcher matcher;
        // get graph name
        String graphName = GraphFactory.GraphLabel(filePath);
        NetworkTopology = new NetworkTopology(graphName);
        // get Vertices from the file
        List<List<String>> vertexCut = GraphFactory.getVertices(filePath);
        List<Vertex> vertices = new ArrayList<>();
        for (List<String> list : vertexCut) {
            regex = Pattern.compile("^\"(.*)\"$");
            matcher = regex.matcher(list.get(2));
            String attr[] = new String[1];
            if (matcher.find()) {
                attr[0] = matcher.group(1);
            }
            Vertex newVertex = VertexFactory.createVertex(list.get(0), list.get(1), attr);
            vertices.add(newVertex);
            NetworkTopology.addVertex(newVertex);
        }
        List<List<String>> edgeCut = GraphFactory.getEdges(filePath);
        for (List<String> list : edgeCut) {
            List<Vertex> vertexInEdge = new ArrayList<>();
            vertexInEdge.addAll(vertices.stream().filter(item -> item.getLabel().equals(list.get(3))).collect(Collectors.toList()));
            vertexInEdge.addAll(vertices.stream().filter(item -> item.getLabel().equals(list.get(4))).collect(Collectors.toList()));
            NetworkTopology.addEdge(EdgeFactory.createEdge(list.get(0), list.get(1), vertexInEdge, Double.parseDouble(list.get(2))));
        }
        return NetworkTopology;
    }
}
