package vertex;

import java.util.*;

import Exception.Vertex.VertexAttributeException;
import edge.*;

/**
 * <p>
 *     Abstraction Function:
 *     label -- 点的唯一标识
 *     inEdges -- 所有入边的集合
 *     outEdges -- 所有出边的集合
 * </p>
 *
 * <p>
 *     Rep Invariant:
 *     如果这个点在无向图中 ，则inEdges.size()=outEdges.size()
 * </p>
 *
 * <p>
 *     Safety from rep exposure:
 *     label ,inEdges,outEdges 都是private属性
 * </p>
 */
public abstract class Vertex {
    private String label;

    private final List<Edge> inEdges = new LinkedList<>(); // 入边
    private final List<Edge> outEdges = new LinkedList<>(); // 出边

    Vertex(String label) {
        this.label = label;
    }

    /**
     * 给该顶点添加附加的信息
     *
     * @param args 附加的数据项,一个String表示一个信息单元
     */
    public abstract void fillVertexInfo(String[] args) throws VertexAttributeException;

    /**
     * 给这个点添加入边,添加的是一个Edge对象
     * 如果这条边已经存在, 或者边的的终点不是这个点,都会返回false
     * 如果添加成功,则会返回true
     *
     * @param inEdge 一个Edge对象,表示一条边
     * @return true: 这条入边添加成功, false: 这条入边添加失败
     */
    public boolean addInEdge(Edge inEdge) {
        if (!inEdges.contains(inEdge) && inEdge.targetVertices().contains(this)) {
            inEdges.add(inEdge);
            return true;
        }
        return false;
    }

    /**
     * 给这个点添加出边,添加的是一个Edge对象
     * 如果这条边已经存在, 或者边的的起点不是这个点,都会返回false
     * 如果添加成功,则会返回true
     *
     * @param outEdge 一个Edge对象,表示一条边
     * @return true: 这条入边添加成功, false: 这条入边添加失败
     */
    public boolean addOutEdge(Edge outEdge) {
        if (!outEdges.contains(outEdge) && outEdge.sourceVertices().contains(this)) {
            outEdges.add(outEdge);
            return true;
        }
        return false;
    }

    /**
     * 在这个点中删除某条边的记录,入边和出边都会删除
     * 如果点的入边和出边中都不存在这条记录,则删除失败,返回false
     * 如果删除成功,则返回true
     *
     * @param edge 一个Edge对象,表示一条边
     * @return true: 这条边删除成功, false: 这条边删除失败
     */
    public boolean removeEdge(Edge edge) {
        boolean removeInEdge = false, removeOutEdge = false;
        Iterator<Edge> iterator = inEdges.iterator();
        while (iterator.hasNext()) {
            Edge item = iterator.next();
            if (item.equals(edge)) {
                iterator.remove();
                removeInEdge = true;
            }
        }
        iterator = outEdges.iterator();
        while (iterator.hasNext()) {
            Edge item = iterator.next();
            if (item.equals(edge)) {
                iterator.remove();
                removeOutEdge = true;
            }
        }
        return removeInEdge || removeOutEdge;
    }

    /**
     * 返回这个点包含的所有入边
     *
     * @return 这个点包含的所有入边
     */
    public Set<Edge> getInEdges() {
        return new HashSet<>(inEdges);
    }

    /**
     * 返回这个点包含的所有出边
     *
     * @return 这个点包含的所有出边
     */
    public Set<Edge> getOutEdges() {
        return new HashSet<>(outEdges);
    }

    /**
     * 获得这个点的 唯一标识 label 的值
     * 这是一个 observer函数
     *
     * @return String对象 label的值
     */
    public String getLabel() {
        return label;
    }

    /**
     * 修改这个节点的label的信息，同时返回旧的label信息
     *
     * @param newLabel 传入新的label信息
     * @return 返回旧的label信息
     */
    public String setLabel(String newLabel) {
        String oldLabel = this.label;
        this.label = newLabel;
        return oldLabel;
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Vertex && ((Vertex) obj).getLabel().equals(this.getLabel());
    }

    @Override
    public int hashCode() {
        return this.getLabel().hashCode();
    }
}
