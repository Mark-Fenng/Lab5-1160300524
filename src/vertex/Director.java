package vertex;

import Exception.Vertex.VertexAttributeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * RI  person的age在[0,150] gender必须是"M"|"F"
 */
public class Director extends Vertex {
    private int age = 0; // 导演的年龄
    private String gender = ""; // 导演的性别

    private void checkRep() {
        assert (age < 0 || age > 150);
        assert (!gender.equals("M") || !gender.equals("F"));
    }

    public Director(String label) {
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

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Director && ((Director) obj).getLabel().equals(this.getLabel()) && ((Director) obj).getGender().equals(this.getGender()) && ((Director) obj).getAge() == this.age;
    }

    /**
     * 获得导演的性别
     *
     * @return 导演的性别
     */
    String getGender() {
        return gender;
    }

    /**
     * 获得导演的年龄
     *
     * @return 导演的年龄
     */
    int getAge() {
        return age;
    }

    @Override
    public int hashCode() {
        Object[] objects = new Object[3];
        objects[0] = this.getLabel();
        objects[1] = this.getAge();
        objects[2] = this.getGender();
        return Arrays.hashCode(objects);
    }

    /**
     * 返回点中的信息
     *
     * @return 包含所有点中信息的字符串
     */
    @Override
    public String getVertexInfo() {
        return ",<\"" + gender + "\",\"" + age + "\">";
    }
}
