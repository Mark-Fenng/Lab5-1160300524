package graph;

import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeNullVertexException;
import Exception.Edge.EdgeWeightException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
import LoggerFactory.LoggerFactory;
import edge.Edge;
import vertex.Vertex;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/**
 * <p>
 * Abstraction Function:
 * label -- 点的唯一标识
 * vertices -- 图中的所有点的集合
 * edges -- 图中所有边的集合
 * </p>
 * <p>
 * Rep Invariant:
 * 无
 * </p>
 * <p>
 * Safety from rep exposure:
 * label ,vertices,edges 都是private属性
 * </p>
 */
public class ConcreteGraph implements Graph {
    private final String label;
    protected final List<Vertex> vertices = new LinkedList<>();
    final List<Edge> edges = new LinkedList<>();

    ConcreteGraph(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public boolean addVertex(Vertex vertex) throws VertexTypeException, VertexLabelException {
        if (vertices.contains(vertex))
            throw new VertexLabelException(label);
        return vertices.add(vertex);
    }

    @Override
    public boolean removeVertex(Vertex vertex) throws EdgeWeightException {
        if (vertices.remove(vertex)) {
            edges.removeIf(item -> item.vertices().contains(vertex));
            return true;
        }
        return false;
    }

    @Override
    public Set<Vertex> vertices() {
        return new HashSet<>(vertices);
    }


    @Override
    public Map<Vertex, List<Double>> sources(Vertex target) {
        if (vertices.contains(target)) {
            Set<Edge> inEdges = target.getInEdges();
            Map<Vertex, List<Double>> result = new HashMap<>();
            for (Edge item : inEdges) {
                Vertex source = item.sourceVertices().stream().filter(o -> !o.equals(target)).findFirst().orElse(null);
                if (result.keySet().contains(source)) {
                    result.get(source).add(item.getWeight());
                } else {
                    result.put(source, Collections.singletonList(item.getWeight()));
                }
            }
            return result;
        }
        return null;
    }

    @Override
    public Map<Vertex, List<Double>> targets(Vertex source) {
        if (vertices.contains(source)) {
            Set<Edge> outEdges = source.getOutEdges();
            Map<Vertex, List<Double>> result = new HashMap<>();
            for (Edge item : outEdges) {
                Vertex target = item.targetVertices().stream().filter(o -> !o.equals(source)).findFirst().orElse(null);
                if (result.keySet().contains(target)) {
                    result.get(target).add(item.getWeight());
                } else {
                    result.put(target, Collections.singletonList(item.getWeight()));
                }
            }
            return result;
        }
        return null;
    }


    @Override
    public boolean addEdge(Edge edge) throws EdgeNullVertexException, EdgeTypeException, EdgeWeightException {
        for (Vertex item : edge.vertices()) {
            if (!vertices.contains(item))
                throw new EdgeNullVertexException("The Vertex : " + item + " have not been define before");
        }
        // 如果edge的label在图中已经存在，则自动在边的label后面填完后缀
        if (edges.contains(edge)) {
            String TempLabel = edge.getLabel();
            for (int i = 0; i < 10000; i++) {
                edge.setLabel(edge.getLabel() + "_" + i);
                if (!edges.contains(edge)) {
                    try {
                        Logger logger = LoggerFactory.getLogger("Exception", "./Lab.log");
                        logger.info("The Edge : \"" + TempLabel + "\" Has Repeated Label in the Graph\n" +
                                "But the New Label : \"" + TempLabel + "_" + i + "\" is Added to The Graph");
                        System.out.println("The Edge : \"" + TempLabel + "\" Has Repeated Label in the Graph\n" +
                                "But the New Label : \"" + TempLabel + "_" + i + "\" is Added to The Graph");
                        break;
                    } catch (IOException ignored) {
                    }
                }
            }
        }

        edges.add(edge);
        // add edge to the vertex,as out edges
        this.vertices.stream().filter(item -> edge.sourceVertices().contains(item)).forEach(item -> item.addOutEdge(edge));
        // add edge to the vertex as in edges
        this.vertices.stream().filter(item -> edge.targetVertices().contains(item)).forEach(item -> item.addInEdge(edge));
        return true;
    }

    @Override
    public boolean removeEdge(Edge edge) throws EdgeWeightException {
        if (edges.remove(edge)) {
            // add edge to the vertex,as out edges
            this.vertices.stream().filter(item -> edge.sourceVertices().contains(item)).forEach(item -> item.removeEdge(edge));
            // add edge to the vertex as in edges
            this.vertices.stream().filter(item -> edge.targetVertices().contains(item)).forEach(item -> item.removeEdge(edge));
            return true;
        }
        return false;
    }

    @Override
    public Set<Edge> edges() {
        return new HashSet<>(edges);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof ConcreteGraph && ((ConcreteGraph) obj).getLabel().equals(this.getLabel());
    }
}
