package edge;

import Exception.Edge.EdgeLoopException;
import Exception.Edge.EdgeVertexTypeException;
import Exception.Edge.EdgeWeightException;
import vertex.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Abstraction Function:
 * label -- 点的唯一标识
 * weight -- 边的权重值   -1表示这条边是无权重边
 * vertices -- 边中的顶点
 * 如果是有向边，vertices中的第一个点是起点，第二个点是终点
 * 如果是无向边，vertices中的两个点没有先后关系
 * 如果是超边，vertices可以是有多个点，点之间没有先后关系
 * </p>
 * <p>
 * Rep Invariant:
 * 如果是有向边和无向边，则vertices 的大小只能是2
 * 如果是超边，则vertices的大小必须大于2
 * </p>
 * <p>
 * Safety from rep exposure:
 * label weight vertices 都是private属性
 * </p>
 */
abstract public class Edge {
    protected final List<Vertex> vertices = new LinkedList<>();
    private String label;
    private double weight = -1;

    Edge(String label, double weight) {
        this.label = label;
        this.weight = weight;
    }

    /**
     * 得到边的label属性值
     * 是一个 observer函数
     *
     * @return label的值, 是String类型
     */
    public String getLabel() {
        return label;
    }

    /**
     * 得到边的权重值
     * 是一个observer函数
     *
     * @return weight的值，是double类型
     */
    public double getWeight() {
        return weight;
    }

    /**
     * 设置图的label值
     *
     * @param newLabel 需要设定的新值
     * @return 返回旧的label值
     */
    public String setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        return oldLabel;
    }

    /**
     * 向边中添加点
     * 如果这是一条普通边，List的长度应该为2
     * 如果是有向边，第一个点是有向边的起点，第二个点是有向边的终点
     * <span>&nbsp;</span>如果是无向边，两个点没有顺序
     * 如果是一条超边，List的长度 大于 2
     *
     * @param vertices list 形式的点的集合
     * @return true :添加成功 false:添加失败
     */
    abstract public boolean addVertices(List<Vertex> vertices) throws EdgeLoopException, EdgeVertexTypeException;

    /**
     * 用于判断某个点是否是这个边的顶点，如果是，返回true,如果不是，返回false
     *
     * @param v 需要判断的点
     * @return true: 点是边的顶点, false: 点不是边的顶点
     */
    boolean containVertex(Vertex v) {
        return vertices.contains(v);
    }

    /**
     * 获取边中的所有顶点
     *
     * @return 边中所有顶点的一个集合
     */
    public Set<Vertex> vertices() {
        return new HashSet<>(vertices);
    }

    /**
     * 获取边的所有起点
     * 如果是有向边，起点只有一个
     * 如果是无向边，返回边的所有顶点
     *
     * @return 边的所有起点
     */
    abstract public Set<Vertex> sourceVertices();

    /**
     * 返回边的所有终点
     * 如果是有向边，终点只有一个
     * 如果是无向边，返回边的所有终点
     *
     * @return 边的所有终点
     */
    abstract public Set<Vertex> targetVertices();

    /**
     * 设置或修改边的权重
     *
     * @param weight 权重的新值
     * @return 返回边的权重的旧值
     */
    public double setWeight(double weight) throws EdgeWeightException {
        double oldWeight = this.weight;
        this.weight = weight;
        return oldWeight;
    }

    /**
     * 将边的信息转换为字符串
     *
     * @return 转换成的字符串
     */
    @Override
    public String toString() {
        return label;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Edge && ((Edge) obj).getLabel().equals(this.getLabel());
    }

    /**
     * 判断两个边是否有相同的顶点
     *
     * @param edge 传入的新边
     * @return 是否相等的boolean值
     */
    public boolean equals(Edge edge) {
        for (Vertex item : edge.sourceVertices()) {
            if (!sourceVertices().contains(item))
                return false;
        }
        for (Vertex item : edge.targetVertices()) {
            if (!targetVertices().contains(item))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.label.hashCode();
    }
}
