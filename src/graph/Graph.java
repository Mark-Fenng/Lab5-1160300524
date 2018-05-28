/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeNullVertexException;
import Exception.Edge.EdgeWeightException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
import edge.Edge;
import vertex.Vertex;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Graph {

    /**
     * 向图中添加一个Vertex对象
     *
     * @param vertex Vertex对象,表示一个点
     * @return true: 向图中成功的添加了这个点 false: 图中已经包含了这个点
     * @throws VertexTypeException  如果添加的点的类型不应该出现在这个边中，则会抛出此异常
     * @throws VertexLabelException 如果图中已经存在一个Label相同的点，则会抛出此异常
     */
    boolean addVertex(Vertex vertex) throws VertexTypeException, VertexLabelException;

    /**
     * 从图中移除这个点，任何包含这个点的边都会同时被删除
     *
     * @param vertex 需要移除的点对象
     * @return true ：移除成功 false: 图中不包含这个点，删除失败
     * @throws EdgeWeightException 在删除边时，如果引起了其他边的weight值不符合图的要求，则抛出这个异常，主要应对SocialGraph的异常
     */
    boolean removeVertex(Vertex vertex) throws EdgeWeightException;

    /**
     * 得到图中的所有点对象
     *
     * @return 图中所有点的一个集合
     */
    Set<Vertex> vertices();

    /**
     * 得到以传入点为终点的所有边的起点，以及所有边的权重值
     *
     * @param target 一个点的对象
     * @return 一个点和一个权重值的数组形成的map, 点是边的起点，边的终点就是传入的点
     */
    Map<Vertex, List<Double>> sources(Vertex target);

    /**
     * Get the target vertices with directed edges from a source vertex and the
     * weights of those edges.
     * 得到以传入点为起点的所有边的终点，以及所有边的权重值
     *
     * @param source 一个点的对象
     * @return 一个点和一个权重值的数组形成的map, 点是边的终点，边的起点就是传入的点
     */
    Map<Vertex, List<Double>> targets(Vertex source);

    /**
     * 向图中添加一条边
     *
     * @param edge 一个边的对象，表示一条边
     * @return true: 图中成功添加了这条边 false: 图中已经有了这条边，添加失败
     * @throws EdgeNullVertexException 如果添加的边中含有还未添加的点，会抛出此错误
     * @throws EdgeTypeException       在向图中添加边时，如果添加的边的类型不符合这种图的要求，则会抛出此异常
     * @throws EdgeWeightException     在向图中添加边时，如果边的权重不符合图的要求，则抛出此异常
     */
    boolean addEdge(Edge edge) throws EdgeNullVertexException, EdgeTypeException, EdgeWeightException;

    /**
     * 从图中移除指定的边对象
     *
     * @param edge 一个边的对象，表示一条边
     * @return true: 成功的从图中删除了这条边 false: 图中没有传入的边对象，删除失败
     * @throws EdgeWeightException 将图中的某条边删除时，如果引起了其他边的权重不符合图的要求，则抛出此异常，主要应对SocialGraph
     */
    boolean removeEdge(Edge edge) throws EdgeWeightException;

    /**
     * 得到图中的所有边对象
     *
     * @return 图中所有边对象的集合
     */
    Set<Edge> edges();

    /**
     * 获得图的label值
     *
     * @return 图的label值
     */
    String getLabel();

    /**
     * 通过传入某个label，获得这个label对应的vertex对象
     *
     * @param label
     * @return
     */
    public Vertex getVertex(String label);
}
