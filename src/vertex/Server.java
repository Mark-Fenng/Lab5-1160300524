package vertex;

import Exception.Vertex.VertexAttributeException;

import java.util.Arrays;

public class Server extends Vertex {
    //    private int[] ip = new int[4];
    private String ip = "";

    public Server(String label) {
        super(label);
    }

    String getIp() {
        return ip;
    }

    @Override
    public void fillVertexInfo(String[] args) throws NumberFormatException, VertexAttributeException {
        if (args.length == 1) {
            try {
                String values[] = args[0].split(".");
                for (String value : values) {
                    int ipValue = Integer.parseInt(value);
                    if (ipValue < 0 || ipValue > 255) // ip的每一个部分的值都在[0,255]的范围内
                        throw new VertexAttributeException(getLabel());
                }
            } catch (NumberFormatException e) {
                throw new VertexAttributeException(getLabel());
            }
        } else {
            throw new VertexAttributeException(getLabel());
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Server && ((Server) obj).getLabel().equals(this.getLabel()) && ((Server) obj).getIp().equals(this.getIp());
    }

    @Override
    public int hashCode() {
        Object[] objects = new Object[2];
        objects[0] = this.getLabel();
        objects[1] = ip;
        return Arrays.hashCode(objects);
    }
}
