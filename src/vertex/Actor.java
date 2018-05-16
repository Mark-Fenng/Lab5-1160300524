package vertex;

import Exception.Vertex.VertexAttributeException;

public class Actor extends Vertex {
    private int age; // 演员的年龄
    private String gender;

    public Actor(String label) {
        super(label);
    }

    @Override
    public void fillVertexInfo(String[] args) throws VertexAttributeException {
        if (args.length == 2) {
            age = Integer.parseInt(args[0]);
            gender = args[1];
        } else {
            throw new VertexAttributeException(getLabel());
        }
    }
}
