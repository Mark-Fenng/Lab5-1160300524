package helper;

import Exception.Command.UnsupportedException;
import edge.Edge;
import graph.Graph;
import vertex.Vertex;

import edu.uci.ics.jung.graph.AbstractGraph;
import edu.uci.ics.jung.algorithms.importance.BetweennessCentrality;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;

import java.util.*;

class GraphMetrics {
    private final static Double INFINITE = 10000000.0;

    /**
     * 计算整个图的degree, 计算公式是图中度最大的值-所有点的度，然后累加 ，再除以图中点的个数
     *
     * @param g 传入的图对象
     * @return 返回整个图的degree
     */
    static double degreeCentrality(Graph g) {
        int maxDegree = g.vertices().stream().map(item -> item.getInEdges().size()).max(Comparator.comparingInt(o -> o)).orElse(-1);
        int sum = 0;
        int size = g.vertices().size();
        for (Vertex item : g.vertices())
            sum += maxDegree - item.getInEdges().size();
        return sum / (double) (size * size - 3 * size + 2);
    }

    /**
     * 获得一个无向图中点的 点度中心性
     * 如果图中没有这个点,将会返回0
     * 如果传入的图是有向图，且点的入度和出度不相同 ，会throw UnsupportedOperationException
     *
     * @param g 传入所求点所在的图
     * @param v 需要求度的点
     * @return 所求点的度，如果传入的图中没有这个点，就会返回0
     * @throws UnsupportedException 如果输入的图是有向图，则不支持求点的度，只支持求点的入度和出度
     */
    static double degreeCentrality(Graph g, Vertex v) throws UnsupportedException {
        if (g.vertices().contains(v)) {
            if (v.getInEdges().size() != v.getOutEdges().size()) {
                throw new UnsupportedException("Directed edge must define in or out degree centrality");
            }
            return v.getInEdges().size();
        }
        return 0;
    }

    /**
     * 获得一个有向图中点的 点入度中心性
     * 如果图中没有这个点,将会返回0
     *
     * @param g 传入所求点所在的图
     * @param v 需要求度的点
     * @return 所求点的入度值，如果传入的图中没有这个点，就会返回0
     */
    static double inDegreeCentrality(Graph g, Vertex v) {
        if (g.vertices().contains(v)) {
            return v.getInEdges().size();
        }
        return 0;
    }

    /**
     * 获得一个无向图中点的 点出度中心性
     * 如果图中没有这个点,将会返回0
     *
     * @param g 传入所求点所在的图
     * @param v 需要求度的点
     * @return 所求点的出度值，如果传入的图中没有这个点，就会返回0
     */
    static double outDegreeCentrality(Graph g, Vertex v) {
        if (g.vertices().contains(v)) {
            return v.getOutEdges().size();
        }
        return 0;
    }

    /**
     * 用于计算一个点到图中其他点的开销 因数 closenessCentrality
     *
     * @param g 图对象
     * @param v 点对象
     * @return closenessCentrality 的值
     */
    static double closenessCentrality(Graph g, Vertex v) {
        int index; // 保存v在数组中的下标
        double centrality = 0;
        List<Vertex> vertices = new ArrayList<>();
        vertices.add(v);
        vertices.addAll(g.vertices());
        double[][] e = new double[vertices.size() + 1][vertices.size() + 1];
        List<List<List<Integer>>> path = new ArrayList<>();
        floyd(vertices, e, path);
        // 寻找指定的点在数组中的下标值
        index = getIndex(vertices, v);
        for (int i = 1; i < vertices.size(); i++) {
            if (i != index) {
                centrality += e[index][i] == INFINITE ? 0 : 1.0 / e[index][i];
            }
        }
        return centrality;
    }

    /**
     * 求某个点的 BetweennessCentrality 值，这个值表示这个点在图中促进其他节点进行通信的能力
     *
     * @param g 图对象
     * @param v 点对象
     * @return BetweennessCentrality 的值
     */
    static double betweennessCentrality(Graph g, Vertex v) {
        AbstractGraph<Vertex, Edge> graph = GraphVisualizationHelper.transferGraph(g);
        BetweennessCentrality<Vertex, Edge> ranker = new BetweennessCentrality<>(graph);
        ranker.step();
        return ranker.getVertexRankScore(v);
    }

    /**
     * 用于求图中两个点之间的最短路径的边的个数
     *
     * @param g     传入目标的图对象
     * @param start 起始的顶点
     * @param end   终止的顶点
     * @return 两个点在图g中的最短路径的边的个数
     */
    static double distance(Graph g, Vertex start, Vertex end) {
        if (start.equals(end))
            return 0;
        AbstractGraph<Vertex, Edge> graph = GraphVisualizationHelper.transferGraph(g);
        DijkstraShortestPath<Vertex, Edge> dijkstraShortestPath = new DijkstraShortestPath<>(graph, Edge::getWeight);
        int size = dijkstraShortestPath.getPath(start, end).size();
        return size == 0 ? Double.POSITIVE_INFINITY : size;
    }

    /**
     * 计算给点在给定图中的eccentricity值(这个点到其他所有点的distance的最大值)
     *
     * @param g 给定的图
     * @param v 给定的点
     * @return 点v在图g中的eccentricity值
     */
    static double eccentricity(Graph g, Vertex v) {
        return g.vertices()
                .stream()
                .mapToDouble(item -> distance(g, v, item))
                .max()
                .orElse(Double.POSITIVE_INFINITY);
    }

    /**
     * 求给定图的radius值
     *
     * @param g 给定的图对象
     * @return 图g的radius值
     */
    static double radius(Graph g) {
        return g.vertices()
                .stream()
                .mapToDouble(item -> eccentricity(g, item))
                .min()
                .orElse(Double.POSITIVE_INFINITY);
    }

    /**
     * 求给定图的diameter值
     *
     * @param g 给定的图对象
     * @return 图g的diameter值
     */
    static double diameter(Graph g) {
        return g.vertices()
                .stream()
                .mapToDouble(item -> eccentricity(g, item))
                .max()
                .orElse(Double.POSITIVE_INFINITY);
    }

    /**
     * @param start  起点的index值
     * @param end    终点的index值
     * @param path   保存的任意两点之间的最短路径
     * @param router 指定两点之间的最短路径
     */

    private static void getpath(int start, int end, List<List<List<Integer>>> path, List<List<Integer>> router, int count) {
        if (start == end)
            return;
        if (path.get(start).get(end).get(0) == 0) {
            if (router.size() > 0 && router.size() - 1 >= count)
                router.get(count).add(end);
            else
                router.add(new ArrayList<>(Collections.singletonList(end)));
        } else {
            for (int i = 0; i < path.get(start).get(end).size(); i++) {
                getpath(start, path.get(start).get(end).get(i), path, router, count);
                getpath(path.get(start).get(end).get(i), end, path, router, count);
                count++;
            }
        }
    }

    /**
     * 用floyd算法求最短路径
     *
     * @param vertices 图中已经排好序的所有顶点，排序是为了方便使用floyd算法求解
     * @param e        用来存储任意两个点之间的最短距离
     * @param path     用来存储任意两个点之间的最短路径
     */
    private static void floyd(List<Vertex> vertices, double[][] e, List<List<List<Integer>>> path) {
        // 初始化数组
        path.add(null);
        for (int i = 1; i < vertices.size(); i++) {
            path.add(new ArrayList<>());
            path.get(i).add(null);
            for (int j = 1; j < vertices.size(); j++) {
                Vertex source = vertices.get(i);
                Vertex target = vertices.get(j);
                Edge edge = source.getOutEdges().values().stream().filter(item -> item.vertices().size() <= 2 && item.targetVertices().contains(target)).min((o1, o2) -> (int) (o1.getWeight() - o2.getWeight())).orElse(null);
                if (edge != null) {
                    e[i][j] = edge.getWeight() == -1 ? INFINITE : edge.getWeight(); // 两点之间边的权重
                } else {
                    e[i][j] = INFINITE; // 两个点之间没有直接的边连通的情况
                }
                path.get(i).add(new ArrayList<>(Collections.singleton(0)));
            }
        }
        // floyd核心算法
        for (int k = 1; k < vertices.size(); k++) {
            for (int i = 1; i < vertices.size(); i++) {
                for (int j = 1; j < vertices.size(); j++) {
                    if (e[i][k] + e[k][j] < e[i][j]) {
                        e[i][j] = e[i][k] + e[k][j];
                        path.get(i).get(j).removeIf(item -> true);
                        path.get(i).get(j).add(k);
                    } else if (Math.abs(e[i][k] + e[k][j] - e[i][j]) < 0.0001 && e[i][j] != INFINITE) {
                        path.get(i).get(j).add(k);
                    }
                }
            }
        }
    }

    /**
     * 寻找指定的元素在一个列表中的下标值
     * 如果指定的元素不在该列表中，返回-1
     *
     * @param list 指定的列表
     * @param item 需要求下标的元素
     * @return 指定元素的下标值，如果列表中没有这个元素，返回-1
     */
    private static int getIndex(List<?> list, Vertex item) {
        for (int i = 1; i <= list.size(); i++) {
            if (list.get(i).equals(item))
                return i;
        }
        return -1;
    }
}
