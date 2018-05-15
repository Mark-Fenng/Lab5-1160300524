package vertex;

import Exception.Vertex.VertexAttributeException;
import edge.Edge;

import java.util.Arrays;

public class Person extends Vertex {
    private String gender;
    private int age;
    private double weight = 0;

    @Override
    public boolean addInEdge(Edge inEdge) {
        if (super.addInEdge(inEdge)) {
            weight = this.getInEdges().size();
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEdge(Edge edge) {
        if (super.removeEdge(edge)) {
            weight = this.getInEdges().size();
            return true;
        }
        return false;
    }

    /**
     * 返回person的权重，表示这个用户的社交影响力
     *
     * @return person节点的权重
     */
    public double getWeight() {
        return weight;
    }

    public Person(String label) {
        super(label);
    }

    private String getGender() {
        return gender;
    }

    private int getAge() {
        return age;
    }

    @Override
    public void fillVertexInfo(String[] args) throws NumberFormatException, VertexAttributeException {
        if (args.length == 2) {
            gender = args[0];
            age = Integer.parseInt(args[1]);
        } else {
            throw new VertexAttributeException("The Vertex : " + getLabel() + " doesn't have wrong number of attributes");
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Person && ((Person) obj).getLabel().equals(this.getLabel()) && ((Person) obj).getGender().equals(this.getGender()) && ((Person) obj).getAge() == this.age;
    }

    @Override
    public int hashCode() {
        Object[] objects = new Object[3];
        objects[0] = this.getLabel();
        objects[1] = this.getAge();
        objects[2] = this.getGender();
        return Arrays.hashCode(objects);
    }
}
