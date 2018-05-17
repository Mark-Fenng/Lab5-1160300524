package factory.graph;

import Exception.Edge.*;
import Exception.FormatException;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexTypeException;
import LoggerFactory.LoggerFactory;
import factory.edge.EdgeFactory;
import graph.Graph;
import graph.GraphPoet;
import vertex.Vertex;
import factory.vertex.VertexFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class GraphPoetFactory {
    public static Graph createGraph(String filePath) throws IOException, FormatException, EdgeNullVertexException, VertexAttributeException, VertexTypeException, EdgeTypeException, UndirectedEdgeException, DirectedEdgeException {
        Graph poet;
        // graph name
        String graphName = GraphFactory.GraphLabel(filePath);
        poet = new GraphPoet(graphName);
        // get Vertices from the file
        List<List<String>> vertexCut = GraphFactory.getVertices(filePath);
        List<Vertex> vertices = new ArrayList<>();
        for (List<String> list : vertexCut) {
            Vertex newVertex = VertexFactory.createVertex(list.get(0), list.get(1), new String[0]);
            vertices.add(newVertex);
            poet.addVertex(newVertex);
        }
        List<List<String>> edgeCut = GraphFactory.getEdges(filePath);
        for (List<String> list : edgeCut) {
            List<Vertex> vertexInEdge = new ArrayList<>();
            vertexInEdge.addAll(vertices.stream().filter(item -> item.getLabel().equals(list.get(3))).collect(Collectors.toList()));
            vertexInEdge.addAll(vertices.stream().filter(item -> item.getLabel().equals(list.get(4))).collect(Collectors.toList()));
            try {
                poet.addEdge(EdgeFactory.createEdge(list.get(0), list.get(1), vertexInEdge, Double.parseDouble(list.get(2))));
            } catch (EdgeVertexTypeException | EdgeLoopException e) {
                Logger logger = LoggerFactory.getLogger("Exception", "./Lab.log");
                logger.info(e.toString());
            }
        }
        return poet;
    }
}
