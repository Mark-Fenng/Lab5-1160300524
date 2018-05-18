package helper;

import Exception.Command.CommandException;
import Exception.Command.UnsupportedException;
import Exception.Edge.EdgeWeightException;
import Exception.Graph.GraphNullException;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
import factory.vertex.VertexFactory;
import graph.Graph;
import vertex.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import java.util.stream.Collectors;

class VertexCommand extends Command {
    VertexCommand(Graph g) {
        super(g);
    }

    @Override
    void add(List<String> args) throws VertexAttributeException, VertexTypeException, VertexLabelException, CommandException {
        if (graph == null) {
            System.out.println("You must Establish Graph First!\nUsage : Graph --add filepath");
            return;
        }
        Pattern Rule = Pattern.compile("\"(.*)\"");
        Matcher matcher = Rule.matcher(args.get(0));
        String label;
        if (matcher.find())
            label = matcher.group(1);
        else
            throw new CommandException("Lack the Vertex Label");
        matcher = Rule.matcher(args.get(1));
        String type;
        if (matcher.find()) {
            type = matcher.group(1);
            Vertex newVertex = VertexFactory.createVertex(label, type, null);
            if (graph.addVertex(newVertex))
                System.out.println("Add vertex successfully!");
            else
                System.out.println("Add fail!");
        }
    }

    @Override
    void delete(List<String> args) throws EdgeWeightException, CommandException, GraphNullException {
        if (graph == null) {
            throw new GraphNullException("");
        }
        Pattern Rule = Pattern.compile("\"(.*)\"");
        Matcher matcher = Rule.matcher(args.get(0));
        String regex;
        if (matcher.find()) {
            regex = matcher.group(1);
            Pattern InputRule = Pattern.compile(regex);
            List<Vertex> vertices = graph.vertices().stream().filter(item -> InputRule.matcher(item.getLabel()).find()).collect(Collectors.toList());
            if (vertices.size() != 0) {
                System.out.println(vertices.size() + " vertices are found:");
                vertices.forEach(item -> System.out.println(item.getLabel()));
                // 请用户确认是否删除这些内容
                if (Command.confirm()) {
                    for (Vertex vertex : vertices) {
                        graph.removeVertex(vertex);
                    }
                    System.out.println("Delete them successfully");
                }
            } else {
                System.out.println("Not found the specific vertex");
            }
        } else
            throw new CommandException("Lack \" at Regex");
    }

    @Override
    void update(List<String> args) throws VertexAttributeException, EdgeWeightException, CommandException, GraphNullException {
        if (graph == null) {
            throw new GraphNullException("");
        }
        Pattern Rule = Pattern.compile("\"(.*)\"");
        Matcher matcher = Rule.matcher(args.get(0));
        String label;
        Vertex vertex;
        if (matcher.find()) {
            label = matcher.group(1);
            vertex = graph.vertices().stream().filter(item -> item.getLabel().equals(label)).findFirst().orElse(null);
            if (vertex == null)
                throw new CommandException("The Vertex Label can't be Found");
        } else
            throw new CommandException("Lack the Vertex Label");
        StringBuilder OptionalCommand = new StringBuilder();
        for (String arg : args) {
            OptionalCommand.append(arg);
        }
        Rule = Pattern.compile("label=(.*)");
        matcher = Rule.matcher(OptionalCommand);
        String newLabel;
        if (matcher.find()) {
            newLabel = matcher.group(1);
            String oldLabel = vertex.setLabel(newLabel);
            System.out.println("Update label successfully , old label is " + oldLabel);
        } else
            throw new CommandException("Lack the Vertex Label");
        Rule = Pattern.compile("argument=(.*)");
        matcher = Rule.matcher(OptionalCommand);
        String argument;
        String[] arguments;
        if (matcher.find()) {
            argument = matcher.group(1);
            arguments = argument.split(",");
            vertex.fillVertexInfo(arguments);
            System.out.println("The argument of the vertex update successfully");
        } else {
            throw new CommandException("Lack the Vertex Argument");
        }
    }

    @Override
    void show(List<String> args) throws UnsupportedException, CommandException, GraphNullException {
        if (graph == null) {
            throw new GraphNullException("");
        }
        Pattern Rule = Pattern.compile("\"(.*)\"");
        Matcher matcher = Rule.matcher(args.get(0));
        String label;
        Vertex vertex;
        if (matcher.find()) {
            label = matcher.group(1);
            vertex = graph.vertices().stream().filter(item -> item.getLabel().equals(label)).findFirst().orElse(null);
            if (vertex == null)
                throw new CommandException("The Vertex Label can't be Found");
            args.remove(0);
        } else
            throw new CommandException("Lack the Vertex Label");
        List<Pattern> Rules = new ArrayList<>();
        Rules.add(Pattern.compile("^eccentricity$"));
        Rules.add(Pattern.compile("^degree$"));
        Rules.add(Pattern.compile("^indegree$"));
        Rules.add(Pattern.compile("^outdegree$"));
        Rules.add(Pattern.compile("^closenessCentrality$"));
        Rules.add(Pattern.compile("^betweennessCentrality$"));
        boolean CommandFlag = false; // 用于标记以上的命令是否有执行过，如果在函数结束时，值还是false，则抛出异常
        matcher = Rules.get(0).matcher(args.get(0));
        if (matcher.find()) {
            System.out.println("The eccentricity of the vertex : " + GraphMetrics.eccentricity(graph, vertex));
            CommandFlag = true;
        }
        matcher = Rules.get(1).matcher(args.get(0));
        if (matcher.find()) {
            System.out.println("The degree of the vertex : " + GraphMetrics.degreeCentrality(graph, vertex));
            CommandFlag = true;
        }
        matcher = Rules.get(2).matcher(args.get(0));
        if (matcher.find()) {
            System.out.println("The inDegree of the vertex : " + GraphMetrics.inDegreeCentrality(graph, vertex));
            CommandFlag = true;
        }
        matcher = Rules.get(3).matcher(args.get(0));
        if (matcher.find()) {
            System.out.println("The outDegree of the vertex : " + GraphMetrics.outDegreeCentrality(graph, vertex));
            CommandFlag = true;
        }
        matcher = Rules.get(4).matcher(args.get(0));
        if (matcher.find()) {
            System.out.println("The closenessCentrality of the vertex : " + GraphMetrics.closenessCentrality(graph, vertex));
            CommandFlag = true;
        }
        matcher = Rules.get(5).matcher(args.get(0));
        if (matcher.find()) {
            System.out.println("The betweennessCentrality of the vertex : " + GraphMetrics.betweennessCentrality(graph, vertex));
            CommandFlag = true;
        }
        if (!CommandFlag)
            throw new CommandException(""); // 命令输入的参数有误
    }
}
