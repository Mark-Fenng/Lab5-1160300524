package factory.graph;

import Exception.Edge.EdgeVertexException;
import Exception.FormatException;
import Exception.Vertex.VertexAttributeException;
import factory.edge.EdgeFactory;
import graph.Graph;
import graph.SocialNetwork;
import vertex.Vertex;
import factory.vertex.VertexFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GraphSocialFactory {
    public static Graph createGraph(String filePath) throws IOException, FormatException, EdgeVertexException, VertexAttributeException {
        Graph socialNetwork;
        Pattern regex;
        Matcher matcher;
        // get graph name
        String graphName = GraphFactory.GraphLabel(filePath);
        socialNetwork = new SocialNetwork(graphName);
        // get Vertices from the file
        List<List<String>> vertexCut = GraphFactory.getVertices(filePath);
        List<Vertex> vertices = new ArrayList<>();
        for (List<String> list : vertexCut) {
            regex = Pattern.compile("^\"(.*)\",\\s*\"(.*)\"$");
            matcher = regex.matcher(list.get(2));
            String attr[] = new String[2];
            if (matcher.find()) {
                attr[0] = matcher.group(1);
                attr[1] = matcher.group(2);
            }
            Vertex newVertex = VertexFactory.createVertex(list.get(0), list.get(1), attr);
            vertices.add(newVertex);
            socialNetwork.addVertex(newVertex);
        }
        List<List<String>> edgeCut = GraphFactory.getEdges(filePath);
        for (List<String> list : edgeCut) {
            List<Vertex> vertexInEdge = new ArrayList<>();
            vertexInEdge.addAll(vertices.stream().filter(item -> item.getLabel().equals(list.get(3))).collect(Collectors.toList()));
            vertexInEdge.addAll(vertices.stream().filter(item -> item.getLabel().equals(list.get(4))).collect(Collectors.toList()));
            socialNetwork.addEdge(EdgeFactory.createEdge(list.get(0), list.get(1), vertexInEdge, Double.parseDouble(list.get(2))));
        }
        return socialNetwork;
    }
}
