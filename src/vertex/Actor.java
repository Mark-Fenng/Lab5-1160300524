package vertex;

import Exception.Vertex.VertexAttributeException;

import java.util.Arrays;

public class Actor extends Vertex {
    private int age; // 演员的年龄
    private String gender;

    public Actor(String label) {
        super(label);
    }

    @Override
    public void fillVertexInfo(String[] args) throws VertexAttributeException {
        if (args.length == 2) {
            gender = args[0];
            if (!(gender.equals("M") || gender.equals("F"))) // 非正常的性别
                throw new VertexAttributeException(getLabel());
            try {
                age = Integer.parseInt(args[1]);
                if (age < 0 || age > 150) // 非正常的年龄输入
                    throw new VertexAttributeException(getLabel());
            } catch (NumberFormatException e) { // 非正常的年龄输入，不是一个整数
                throw new VertexAttributeException(getLabel());
            }
        } else {
            throw new VertexAttributeException(getLabel());
        }
    }

    /**
     * 获得演员的性别
     *
     * @return 演员的性别
     */
    String getGender() {
        return gender;
    }

    /**
     * 获得演员的年龄
     *
     * @return 演员的年龄
     */
    int getAge() {
        return age;
    }


    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Actor && ((Actor) obj).getLabel().equals(this.getLabel()) && ((Actor) obj).getGender().equals(this.getGender()) && ((Actor) obj).getAge() == this.age;
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
