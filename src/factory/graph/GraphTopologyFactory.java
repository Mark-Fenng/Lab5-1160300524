package factory.graph;

import Exception.*;
import Exception.Edge.*;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
import LoggerFactory.*;
import factory.edge.EdgeFactory;
import graph.Graph;
import graph.NetworkTopology;
import vertex.Vertex;
import factory.vertex.VertexFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphTopologyFactory {
    public static Graph createGraph(String filePath) throws IOException, FormatException, EdgeNullVertexException, VertexAttributeException, VertexTypeException, EdgeTypeException, DirectedEdgeException, HyperEdgeException, EdgeWeightException, VertexLabelException {
        Graph NetworkTopology;
        Pattern regex;
        Matcher matcher;
        // get graph name
        String graphName = GraphFactory.GraphLabel(filePath);
        NetworkTopology = new NetworkTopology(graphName);
        streamInput reader = new streamInput(filePath);
        // get Vertices from the file
        List<List<String>> vertexCut = GraphFactory.getVertices(reader);
        for (List<String> list : vertexCut) {
            regex = Pattern.compile("^\"(.*)\"$");
            matcher = regex.matcher(list.get(2));
            String attr[] = new String[1];
            if (matcher.find()) {
                attr[0] = matcher.group(1);
            }
            Vertex newVertex = VertexFactory.createVertex(list.get(0), list.get(1), attr);
            NetworkTopology.addVertex(newVertex);
        }
        reader = new streamInput(filePath);
        List<List<String>> edgeCut = GraphFactory.getEdges(reader);
        for (List<String> list : edgeCut) {
            try {
                NetworkTopology.addEdge(EdgeFactory.createEdge(list.get(0), list.get(1), Arrays.asList(NetworkTopology.getVertex(list.get(3)), NetworkTopology.getVertex(list.get(4))), Double.parseDouble(list.get(2))));
                if (Double.parseDouble(list.get(2)) < 0)
                    throw new EdgeWeightException(list.get(0), list.get(2));
            } catch (EdgeVertexTypeException | EdgeLoopException e) {
                MyLogger.warning(e.toString() + "\ngo on read the file");
            }
        }
        return NetworkTopology;
    }
}
