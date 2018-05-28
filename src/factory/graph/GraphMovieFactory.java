package factory.graph;

import Exception.Edge.*;
import Exception.FormatException;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
import factory.edge.EdgeFactory;
import factory.vertex.VertexFactory;
import graph.Graph;
import graph.MovieGraph;
import vertex.Vertex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import LoggerFactory.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphMovieFactory {
    public static Graph createGraph(String filePath) throws IOException, FormatException, EdgeNullVertexException, VertexAttributeException, VertexTypeException, EdgeTypeException, DirectedEdgeException, HyperEdgeException, EdgeWeightException, VertexLabelException {
        Graph movie;
        Pattern regex;
        Matcher matcher;
        // graph name
        String graphName = GraphFactory.GraphLabel(filePath);
        movie = new MovieGraph(graphName);
        streamInput reader = new streamInput(filePath);
        // get Vertices from the file
        List<List<String>> vertexCut = GraphFactory.getVertices(reader);
        for (List<String> list : vertexCut) {
            regex = Pattern.compile("^\"(.*?)\",\\s*\"(.*?)\"(?:,\\s*\"(.*)\")?$");
            matcher = regex.matcher(list.get(2));
            String[] args = new String[3];
            if (matcher.find()) {
                if (matcher.group(3) != null) {
                    args[2] = matcher.group(3);
                } else {
                    args = new String[2];
                }
                args[0] = matcher.group(1);
                args[1] = matcher.group(2);
            }
            Vertex newVertex = VertexFactory.createVertex(list.get(0), list.get(1), args);
            movie.addVertex(newVertex);
        }
        reader = new streamInput(filePath);
        List<List<String>> edgeCut = GraphFactory.getEdges(reader);
        for (List<String> list : edgeCut) {
            if (list.size() == 6) {
                try {
                    movie.addEdge(EdgeFactory.createEdge(list.get(0), list.get(1), Arrays.asList(movie.getVertex(list.get(3)), movie.getVertex(list.get(4))), Double.parseDouble(list.get(2))));
                } catch (EdgeVertexTypeException | EdgeLoopException e) {
                    MyLogger.warning(e.toString() + "\ngo on read the file");
                }
            } else if (list.size() == 3) {
                String hyperStr = list.get(2);
                hyperStr = hyperStr.replace(" ", "");
                String[] hyper = hyperStr.split(",");
                List<Vertex> vertexInEdge = new ArrayList<>();
                Arrays.stream(hyper).forEach(item -> vertexInEdge.add(movie.getVertex(item.substring(1, item.length() - 1))));
                try {
                    movie.addEdge(EdgeFactory.createEdge(list.get(0), list.get(1), vertexInEdge, -1));
                } catch (EdgeVertexTypeException | EdgeLoopException e) {
                    MyLogger.warning(e.toString() + "\ngo on read the file");
                }
            }
        }
        return movie;
    }
}
