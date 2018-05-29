package helper;

import edge.Edge;
import edge.HyperEdge;
import graph.Graph;
import vertex.Vertex;

import java.io.IOException;
import java.util.stream.Collectors;

class OutputGraph {
    static void output(Graph g, WriteFile fileWriter) throws IOException {
        long startTime = System.currentTimeMillis();
        // 输出对图的描述
        fileWriter.write("GraphType = \"" + g.getClass().getName().split("\\.")[g.getClass().getName().split("\\.").length - 1] + "\"\n");
        fileWriter.write("GraphName = \"" + g.getLabel() + "\"\n\n");

        // 输出对点的描述
        // 输出点的类型
        StringBuilder vertexTypeString = new StringBuilder();
        g.vertices().stream()
                .map(Vertex::getClass)
                .map(Class::getName)
                .map(item -> item.split("\\.")[item.split("\\.").length - 1]) // 获取类名
                .collect(Collectors.toSet()) // 去掉重复的类型
                .forEach(item -> vertexTypeString.append("\"").append(item).append("\", "));
        fileWriter.write("VertexType = " + vertexTypeString.substring(0, vertexTypeString.length() - 2) + "\n");
        // 输出每个点的具体信息

        g.vertices().forEach(item -> {
            try {
                fileWriter.write("Vertex = <\"" + item.getLabel() + "\",\"" +
                        item.getClass().getName().split("\\.")[item.getClass().getName().split("\\.").length - 1]
                        + "\"" + item.getVertexInfo() + ">\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 输出对边的描述
        fileWriter.write("\n");
        // 输出对边的类型的描述
        StringBuilder edgeTypeString = new StringBuilder();
        g.edges().stream()
                .map(Edge::getClass)
                .map(Class::getName)
                .map(item -> item.split("\\.")[item.split("\\.").length - 1]) // 获取类名
                .collect(Collectors.toSet()) // 去掉重复的类型
                .forEach(item -> edgeTypeString.append("\"").append(item).append("\", "));
        fileWriter.write("EdgeType = " + edgeTypeString.substring(0, edgeTypeString.length() - 2) + "\n");
        // 输出每条边的具体类型
        // 输出普通边
        g.edges().stream().filter(item -> !(item instanceof HyperEdge)).forEach(item -> {
            try {
                fileWriter.write("Edge = <" + item.toString() + ">\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // 输出超边
        g.edges().stream().filter(item -> item instanceof HyperEdge).forEach(item -> {
            try {
                fileWriter.write("HyperEdge = <" + item.toString() + ">\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        long endTime = System.currentTimeMillis();
        System.out.println("Write file finish Using " + (endTime - startTime) / 1000.0 + " s");
        fileWriter.close();
    }
}
