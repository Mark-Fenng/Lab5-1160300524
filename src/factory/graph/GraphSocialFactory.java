package factory.graph;

import Exception.Edge.*;
import Exception.FormatException;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
import LoggerFactory.*;
import factory.edge.EdgeFactory;
import graph.Graph;
import graph.SocialNetwork;
import vertex.Vertex;
import factory.vertex.VertexFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GraphSocialFactory {
    public static Graph createGraph(String filePath) throws IOException, FormatException, EdgeNullVertexException, VertexAttributeException, VertexTypeException, EdgeTypeException, DirectedEdgeException, HyperEdgeException, EdgeWeightException, VertexLabelException {
        Graph socialNetwork;
        Pattern regex;
        Matcher matcher;
        // get graph name
        String graphName = GraphFactory.GraphLabel(filePath);
        socialNetwork = new SocialNetwork(graphName);
        streamInput reader = new streamInput(filePath);
        // get Vertices from the file
        List<List<String>> vertexCut = GraphFactory.getVertices(reader);
        for (List<String> list : vertexCut) {
            regex = Pattern.compile("^\"(.*)\",\\s*\"(.*)\"$");
            matcher = regex.matcher(list.get(2));
            String attr[] = new String[2];
            if (matcher.find()) {
                attr[0] = matcher.group(1);
                attr[1] = matcher.group(2);
            }
            Vertex newVertex = VertexFactory.createVertex(list.get(0), list.get(1), attr);
            socialNetwork.addVertex(newVertex);
        }
        reader = new streamInput(filePath);
        List<List<String>> edgeCut = GraphFactory.getEdges(reader);
        for (List<String> list : edgeCut) {
            try {
                socialNetwork.addEdge(EdgeFactory.createEdge(list.get(0), list.get(1), Arrays.asList(socialNetwork.getVertex(list.get(3)), socialNetwork.getVertex(list.get(4))), Double.parseDouble(list.get(2))));
                if (Double.parseDouble(list.get(2)) < 0)
                    throw new EdgeWeightException(list.get(0), list.get(2));
            } catch (EdgeVertexTypeException | EdgeLoopException e) {
                MyLogger.warning(e.toString() + "\ngo on read the file");
            }
        }
        return socialNetwork;
    }
}
