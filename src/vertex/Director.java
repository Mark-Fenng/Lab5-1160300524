package vertex;

import Exception.Vertex.VertexAttributeException;

public class Director extends Vertex {
    private int age;
    private String gender;

    public Director(String label) {
        super(label);
    }

    @Override
    public void fillVertexInfo(String[] args) throws VertexAttributeException {
        if (args.length == 2) {
            age = Integer.parseInt(args[0]);
            gender = args[1];
        } else {
            throw new VertexAttributeException("The Vertex : " + getLabel() + " doesn't have wrong number of attributes");
        }
    }
}
