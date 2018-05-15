package vertex;

import Exception.Vertex.VertexAttributeException;

import java.util.Arrays;

public class Server extends Vertex {
    //    private int[] ip = new int[4];
    private String ip;

    public Server(String label) {
        super(label);
    }

    private String getIp() {
        return ip;
    }

    @Override
    public void fillVertexInfo(String[] args) throws NumberFormatException, VertexAttributeException {
        if (args.length == 1) {
//            String values[] = args[0].split(".");
//            for (int i = 0; i < values.length; i++) {
//                ip[i] = Integer.parseInt(values[i]);
//            }
            ip = args[0];
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
