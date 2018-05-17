package helper;

import Exception.Edge.*;
import Exception.FormatException;
import Exception.TypeException;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexTypeException;
import factory.graph.GraphFactory;
import graph.Graph;

import java.io.IOException;
import java.util.*;
import java.util.regex.*;

/**
 * 点的命令
 * <p>vertex --add label type  向图中添加名为label，类型为type的点</p>
 * <p>vertex --delete regex 删除所有label符合regex规则的点</p>
 * <p>vertex --update label</p>
 * <ul>
 * <li>label=    改变点的label值</li>
 * <li>argument=   改变点的argument值，即点的属性</li>
 * </ul>
 * <p>vertex --show</p>
 * <ul>
 * <li>eccentricity 求点的eccentricity值</li>
 * <li>degree 求点的degree值</li>
 * <li>indegree 求点的indegree值</li>
 * <li>求点的outdegree值</li>
 * <li>closenessCentrality 求点的closenessCentrality值</li>
 * <li>betweennessCentrality 求点的 betweennessCentrality值</li>
 * </ul>
 * <p>边的命令</p>
 * <p>edge --add label type [weighted=Y|N] [weight] [directed=Y|N] v1, v2</p>
 * <p>edge --delete regex 删除所有label符合regex的边</p>
 * <p>超边的命令</p>
 * <p>hyperedge --add</p>
 * <ul>
 * <li>label type vertex(1),    ..., vertex(n) 向图中添加一个新的超边</li>
 * <li>label vertex(1),    ..., vertex(n) 向label 为 label的超边添加点</li>
 * </ul>
 * <p>hyperedge --delete label regex 删除超边中label符合regex规则的点</p>
 * <p>图的命令</p>
 * <p>graph --show</p>
 * <ul>
 * <li>degreeCentrality 输出图的degreeCentrality值</li>
 * <li>radius 输出图的radius值</li>
 * <li>diameter 输出图的diameter值</li>
 * <li>visible 将图的可视化结果输出</li>
 * <li>distance="vertex1""vertex2" 输出vertex1 与 vertex2 在图中的 distance值</li>
 * </ul>
 */
public class ParseCommandHelper {
    /**
     * 用于图应用的命令行交互
     *
     * @param filePath 含有图语法信息的语法输入
     * @throws IOException 文件读写的异常
     */
    public static void Command(String filePath) throws IOException, FormatException, TypeException, EdgeNullVertexException, VertexAttributeException, VertexTypeException, EdgeTypeException, UndirectedEdgeException, DirectedEdgeException, HyperEdgeException {
        Graph graph = GraphFactory.createGraph(filePath);
        List<String> params;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Graph>>>");
            if (in.hasNextLine()) {
                String input = in.nextLine();
                params = new LinkedList<>(Arrays.asList(input.split(" ")));
                if (input.equals("exit"))
                    System.exit(0);
                type(params, graph);
            }
        }
    }

    private static void command(List<String> args, Command cmd) throws EdgeNullVertexException, VertexAttributeException, VertexTypeException, EdgeTypeException, FormatException, IOException, HyperEdgeException {
        Pattern commandRule = Pattern.compile("--(.*)");
        Matcher matcher = commandRule.matcher(args.get(0));
        String command;
        args.remove(0);
        if (matcher.find()) {
            command = matcher.group(1);
            switch (command) {
                case "add":
                    cmd.add(args);
                    break;
                case "update":
                    cmd.update(args);
                    break;
                case "show":
                    cmd.show(args);
                    break;
                case "delete":
                    cmd.delete(args);
                    break;
                default:
            }
        }
    }

    private static void type(List<String> args, Graph graph) throws EdgeNullVertexException, VertexAttributeException, VertexTypeException, EdgeTypeException, FormatException, IOException, HyperEdgeException {
        if (args.size() < 3)
            return;
        String type = args.get(0);
        args.remove(0);
        switch (type) {
            case "vertex":
                VertexCommand vertexCommand = new VertexCommand(graph);
                command(args, vertexCommand);
                break;
            case "edge":
                EdgeCommand edgeCommand = new EdgeCommand(graph);
                command(args, edgeCommand);
                break;
            case "hyperedge":
                HyperEdgeCommand hyperEdgeCommand = new HyperEdgeCommand(graph);
                command(args, hyperEdgeCommand);
                break;
            case "graph":
                GraphCommand graphCommand = new GraphCommand(graph);
                command(args, graphCommand);
                break;
            default:
                throw new UnsupportedOperationException("usage");
        }
    }
}
