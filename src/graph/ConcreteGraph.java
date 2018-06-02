package graph;

import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeNullVertexException;
import Exception.Edge.EdgeWeightException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
import LoggerFactory.*;
import edge.Edge;
import edge.HyperEdge;
import vertex.Vertex;

import java.util.*;
import java.util.stream.Collectors;

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
    protected final Map<String, Vertex> vertices = new HashMap<>();
    final Map<String, Edge> edges = new HashMap<>();

    private void checkRep() {
        for (Edge item : edges.values())
            assert !(item instanceof HyperEdge) || item.vertices().size() >= 2;
    }

    ConcreteGraph(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public Vertex getVertex(String label) {
        return vertices.get(label);
    }

    @Override
    public String setVertexLabel(String label, String newLabel) {
        if (vertices.containsKey(newLabel)) // 要新修改的Label值已经被其他节点使用了
            return null;
        Vertex vertex;
        vertex = vertices.get(label);
        if (vertex != null) {
            vertex.setLabel(newLabel);
            vertices.remove(label);
            vertices.put(newLabel, vertex);
            return label;
        }
        return null;
    }

    @Override
    public boolean addVertex(Vertex vertex) throws VertexTypeException, VertexLabelException {
//        checkRep();
        if (vertices.containsKey(vertex))
            throw new VertexLabelException(label);
//        checkRep();
        vertices.put(vertex.getLabel(), vertex);
        return true;
    }

    @Override
    public boolean removeVertex(Vertex vertex) throws EdgeWeightException {
//        checkRep();
        if (vertices.containsValue(vertex)) {
            vertices.remove(vertex.getLabel());
            edges.remove(vertex.getLabel(), vertex);
            return true;
        }
//        checkRep();
        return false;
    }

    @Override
    public Set<Vertex> vertices() {
        return new HashSet<>(vertices.values());
    }


    @Override
    public Map<Vertex, List<Double>> sources(Vertex target) {
        if (vertices.get(target.getLabel()) != null) {
            Set<Edge> inEdges = new HashSet<>(target.getInEdges().values());
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
        if (vertices.get(source.getLabel()) != null) {
            Set<Edge> outEdges = new HashSet<>(source.getOutEdges().values());
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
//        checkRep();
        for (Vertex item : edge.vertices()) {
            if (!vertices.containsKey(item.getLabel()))
                throw new EdgeNullVertexException("The Vertex : " + item + " have not been define before");
        }
        //如果edge的label在图中已经存在，则自动在边的label后面填完后缀
        if (edges.containsKey(edge.getLabel())) {
            String TempLabel = edge.getLabel();
            for (int i = 0; i < 10000; i++) {
                edge.setLabel(edge.getLabel() + "_" + i);
                if (!edges.containsKey(edge.getLabel())) {
                    MyLogger.info("The Edge : \"" + TempLabel + "\" Has Repeated Label in the Graph\n" +
                            "But the New Label : \"" + TempLabel + "_" + i + "\" is Added to The Graph");
                    System.out.println("The Edge : \"" + TempLabel + "\" Has Repeated Label in the Graph\n" +
                            "But the New Label : \"" + TempLabel + "_" + i + "\" is Added to The Graph");
                    break;

                }
            }
        }

        edges.put(edge.getLabel(), edge);
        // add edge to the vertex,as out edges
        edge.sourceVertices().forEach(item -> vertices.get(item.getLabel()).addOutEdge(edge));
        // add edge to the vertex as in edges
        edge.targetVertices().forEach(item -> vertices.get(item.getLabel()).addInEdge(edge));
//        checkRep();
        return true;
    }

    @Override
    public boolean removeEdge(Edge edge) throws EdgeWeightException {
//        checkRep();
        if (edges.remove(edge.getLabel(), edge)) {
            // add edge to the vertex,as out edges
            edge.sourceVertices().forEach(item -> vertices.get(item.getLabel()).removeEdge(edge));
            // add edge to the vertex as in edges
            edge.targetVertices().forEach(item -> vertices.get(item.getLabel()).removeEdge(edge));
            return true;
        }
//        checkRep();
        return false;
    }

    @Override
    public Set<Edge> edges() {
        return new HashSet<>(edges.values());
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof ConcreteGraph && ((ConcreteGraph) obj).getLabel().equals(this.getLabel());
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }
}