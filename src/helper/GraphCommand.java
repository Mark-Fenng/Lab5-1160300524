package helper;

import Exception.*;
import Exception.Command.CommandException;
import Exception.Edge.*;
import Exception.Graph.GraphNullException;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
import LoggerFactory.MyLogger;
import factory.graph.GraphFactory;
import graph.Graph;
import vertex.Vertex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GraphCommand extends Command {
    GraphCommand(Graph g) {
        super(g);
    }

    @Override
    void add(List<String> args) throws IOException, EdgeTypeException, EdgeWeightException, TypeException, VertexAttributeException, VertexTypeException, VertexLabelException, HyperEdgeException, EdgeNullVertexException, DirectedEdgeException, FormatException {
        if (args.size() != 1) {
            MyLogger.warning("Error Command!\nGraph --add filepath");
            System.out.println("Error Command!\nGraph --add filepath");
        }
        graph = GraphFactory.createGraph(args.get(0));
        MyLogger.info("Establish Graph Successfully");
        System.out.println("Establish Graph Successfully");
    }

    @Override
    void delete(List<String> args) throws EdgeWeightException, GraphNullException, CommandException {
        if (graph == null) {
            throw new GraphNullException("");
        }
        throw new CommandException("");
    }

    @Override
    void update(List<String> args) throws VertexAttributeException, EdgeWeightException, GraphNullException, CommandException {
        if (graph == null) {
            throw new GraphNullException("");
        }
        throw new CommandException("");
    }

    @Override
    void show(List<String> args) throws GraphNullException, CommandException {
        if (graph == null)
            throw new GraphNullException("");
        Matcher matcher;
        StringBuilder OptionalCommand = new StringBuilder();
        for (String arg : args)
            OptionalCommand.append(arg);
        List<Pattern> Rules = new ArrayList<>();
        Rules.add(Pattern.compile("^degreeCentrality$"));
        Rules.add(Pattern.compile("^radius$"));
        Rules.add(Pattern.compile("^diameter$"));
        Rules.add(Pattern.compile("^visible$"));
        Rules.add(Pattern.compile("^distance\\s*=\\s*\"(.*)\"\"(.*)\""));
        Rules.add(Pattern.compile("^filePath\\s*=\\s*\"(.*)\"$"));
        matcher = Rules.get(0).matcher(OptionalCommand);
        boolean CommandFlag = false; // 用于标记以上的命令是否有执行过，如果在函数结束时，值还是false，则抛出异常
        if (matcher.find()) {
            MyLogger.info("The degreeCentrality of the graph : " + GraphMetrics.degreeCentrality(graph));
            System.out.println("The degreeCentrality of the graph : " + GraphMetrics.degreeCentrality(graph));
            CommandFlag = true;
        }
        matcher = Rules.get(1).matcher(OptionalCommand);
        if (matcher.find()) {
            MyLogger.info("The radius of the graph : " + GraphMetrics.radius(graph));
            System.out.println("The radius of the graph : " + GraphMetrics.radius(graph));
            CommandFlag = true;
        }
        matcher = Rules.get(2).matcher(OptionalCommand);
        if (matcher.find()) {
            MyLogger.info("The diameter of the graph : " + GraphMetrics.diameter(graph));
            System.out.println("The diameter of the graph : " + GraphMetrics.diameter(graph));
            CommandFlag = true;
        }
        matcher = Rules.get(3).matcher(OptionalCommand);
        if (matcher.find()) {
            GraphVisualizationHelper.visualize(graph);
            CommandFlag = true;
        }
        Matcher newMatcher = Rules.get(4).matcher(OptionalCommand);
        if (matcher.find()) {
            Vertex start, end;
            start = graph.vertices().stream().filter(item -> item.getLabel().equals(newMatcher.group(1))).findFirst().orElse(null);
            end = graph.vertices().stream().filter(item -> item.getLabel().equals(newMatcher.group(2))).findFirst().orElse(null);
            if (start == null || end == null) {
                MyLogger.warning("The input vertex is not in the graph ");
                System.out.println("The input vertex is not in the graph ");
                return;
            }
            MyLogger.info("The distance between the two vertices : " + GraphMetrics.distance(graph, start, end));
            System.out.println("The distance between the two vertices : " + GraphMetrics.distance(graph, start, end));
            CommandFlag = true;
        }
        matcher = Rules.get(5).matcher(OptionalCommand);
        if (matcher.find()) {
            try {
                WriteFile writeFile = new nioWriter(matcher.group(1));
                OutputGraph.output(graph, writeFile);
                System.out.println("Output the file successfully!");
            } catch (IOException e) {
                MyLogger.severe("The filePath " + matcher.group(1) + " can't be created\n" + MyLogger.toString(e));
                System.out.println("The filePath " + matcher.group(1) + " can't be created");
            }
            CommandFlag = true;
        }
        if (!CommandFlag)
            throw new CommandException(""); // 命令输入的参数有误
    }
}
