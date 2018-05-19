package helper;

import Exception.Command.CommandException;
import Exception.Command.UnsupportedException;
import Exception.Edge.*;
import Exception.FormatException;
import Exception.Graph.GraphNullException;
import Exception.TypeException;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
import graph.Graph;
import LoggerFactory.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 点的命令
 * <p>vertex --add label type  向图中添加名为label，类型为type的点</p>
 * <p>vertex --delete regex 删除所有label符合regex规则的点</p>
 * <p>vertex --update label</p>
 * <ul>
 * <li>label=    改变点的label值</li>
 * <li>argument=   改变点的argument值，即点的属性</li>
 * </ul>
 * <p>vertex --show label</p>
 * <ul>
 * <li>eccentricity 求点的eccentricity值</li>
 * <li>degree 求点的degree值</li>
 * <li>indegree 求点的indegree值</li>
 * <li>outdegree 求点的outdegree值</li>
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
 * <p>graph --add filepath  通过输入一个固定格式的文件，根据文件中提供的信息建立一个新的图</p>
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
     */
    public static void Command() {
        Graph graph = null;
        List<String> params;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Graph>>>");
            if (in.hasNextLine()) {
                String input = in.nextLine();
                params = new LinkedList<>(Arrays.asList(input.split(" ")));
                if (input.equals("exit"))
                    System.exit(0);
                try {
                    MyLogger.info(input);
                    graph = type(params, graph);
                } catch (IOException | FormatException | TypeException | EdgeNullVertexException | VertexAttributeException | VertexTypeException | EdgeTypeException | DirectedEdgeException | HyperEdgeException | EdgeWeightException | VertexLabelException e) {
                    MyLogger.severe(e.toString() + "\nInput the file again");
                    System.out.println(e.toString());
                    System.out.println("Please establish the graph again");
                } catch (CommandException e) {
                    if (!e.getMessage().equals("")) {
                        MyLogger.severe(e.toString());
                        System.out.println(e.getMessage());
                    }
                    PrintUsage();
                } catch (UnsupportedException e) {
                    MyLogger.severe(e.toString());
                    System.out.println(e.getMessage());
                } catch (GraphNullException e) {
                    MyLogger.severe(e.toString());
                    System.out.println("You must Establish Graph First!\nUsage : Graph --add filepath");
                }
            }
        }
    }

    private static Graph command(List<String> args, Command cmd) throws EdgeNullVertexException, VertexAttributeException, VertexTypeException, EdgeTypeException, FormatException, IOException, HyperEdgeException, EdgeWeightException, VertexLabelException, TypeException, DirectedEdgeException, CommandException, UnsupportedException, GraphNullException {
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
                    throw new CommandException("Invalid Command");
            }
        }
        return cmd.graph;
    }

    private static Graph type(List<String> args, Graph graph) throws EdgeNullVertexException, VertexAttributeException, VertexTypeException, EdgeTypeException, FormatException, IOException, HyperEdgeException, EdgeWeightException, VertexLabelException, TypeException, DirectedEdgeException, CommandException, UnsupportedException, GraphNullException {
        if (args.size() < 3)
            throw new CommandException("The Argument is not Enough");
        String type = args.get(0);
        args.remove(0);
        switch (type) {
            case "vertex":
                VertexCommand vertexCommand = new VertexCommand(graph);
                return command(args, vertexCommand);
            case "edge":
                EdgeCommand edgeCommand = new EdgeCommand(graph);
                return command(args, edgeCommand);
            case "hyperedge":
                HyperEdgeCommand hyperEdgeCommand = new HyperEdgeCommand(graph);
                return command(args, hyperEdgeCommand);
            case "graph":
                GraphCommand graphCommand = new GraphCommand(graph);
                return command(args, graphCommand);
            default:
                throw new CommandException("");
        }
    }

    static private void PrintUsage() {
        System.out.println("Invalid Command!");
        System.out.println("Usage:");
        System.out.println("vertex --add label type");
        System.out.println("vertex --delete regex");
        System.out.println("vertex --update label");
        System.out.println("\tlabel=[]");
        System.out.println("\targument=[]");
        System.out.println("vertex --show");
        System.out.println("\teccentricity 求点的eccentricity值");
        System.out.println("\tdegree");
        System.out.println("\tindegree");
        System.out.println("\toutdegree");
        System.out.println("\tclosenessCentrality");
        System.out.println("\tbetweennessCentrality");

        System.out.println("edge --add label type [weighted=Y|N] [weight] [directed=Y|N] v1, v2");
        System.out.println("edge --delete regex");

        System.out.println("hyperedge --add");
        System.out.println("\tlabel type vertex1, ..., vertexn");
        System.out.println("\tlabel vertex1, ..., vertexn");
        System.out.println("hyperedge --delete label regex");

        System.out.println("graph --add filepath");
        System.out.println("graph --show");
        System.out.println("\tdegreeCentrality");
        System.out.println("\tradius");
        System.out.println("\tdiameter");
        System.out.println("\tvisiable");
        System.out.println("\tdistance=\"vertex1\"\"vertex2\"");
    }
}
