package helper;

import edge.Edge;
import edge.HyperEdge;
import graph.Graph;
import vertex.Vertex;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputGraph {
    public static void output(Graph g, String filePath) throws IOException {
        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter(file);
        // 输出对图的描述
        fileWriter.write("GraphType = " + g.getClass().getName() + "\n");
        fileWriter.write("GraphName = " + g.getLabel() + "\n\n");

        // 输出对点的描述
        // 输出点的类型
        StringBuilder vertexTypeString = new StringBuilder();
        g.vertices().stream()
                .map(Vertex::getClass)
                .map(Class::getName)
                .forEach(item -> vertexTypeString.append("\"").append(item).append("\", "));
        fileWriter.write("VertexType = " + vertexTypeString.substring(0, vertexTypeString.length() - 2) + "\n");
        // 输出每个点的具体信息

        g.vertices().forEach(item -> {
            try {
                fileWriter.write("Vertex = <\"" + item.getLabel() + "\",\"" + item.getClass().getName() + "\"" + item.getVertexInfo() + ">");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 输出对边的描述
        // 输出对边的类型的描述
        StringBuilder edgeTypeString = new StringBuilder();
        g.edges().stream()
                .map(Edge::getClass)
                .map(Class::getName)
                .forEach(item -> edgeTypeString.append("\"").append(item).append("\", "));
        fileWriter.write("EdgeType = " + edgeTypeString.substring(0, edgeTypeString.length() - 2) + "\n");
        // 输出每条边的具体类型
        // 输出普通边
        g.edges().stream().filter(item -> !(item instanceof HyperEdge)).forEach(item -> {
            try {
                fileWriter.write(item.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // 输出超边
        g.edges().stream().filter(item -> item instanceof HyperEdge).forEach(item -> {
            try {
                fileWriter.write(item.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
