package helper;

import Exception.Vertex.VertexAttributeException;
import edge.Edge;
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
    void add(List<String> args) throws VertexAttributeException {
        Pattern Rule = Pattern.compile("\"(.*)\"");
        Matcher matcher = Rule.matcher(args.get(0));
        String label;
        if (matcher.find())
            label = matcher.group(1);
        else
            return;
        matcher = Rule.matcher(args.get(1));
        String type;
        if (matcher.find()) {
            type = matcher.group(1);
            Vertex newVertex = VertexFactory.createVertex(label, type, null);
            if (graph.addVertex(newVertex))
                System.out.println("Add vertex successfully!");
            else
                System.err.println("Add fail!");
        }
    }

    @Override
    void delete(List<String> args) {
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
                    vertices.forEach(item -> graph.removeVertex(item));
                    System.out.println("Delete them successfully");
                }
            } else {
                System.out.println("Not found the specific vertex");
            }
        }
    }

    @Override
    void update(List<String> args) throws VertexAttributeException {
        Pattern Rule = Pattern.compile("\"(.*)\"");
        Matcher matcher = Rule.matcher(args.get(0));
        String label;
        Vertex vertex;
        if (matcher.find()) {
            label = matcher.group(1);
            vertex = graph.vertices().stream().filter(item -> item.getLabel().equals(label)).findFirst().orElse(null);
            if (vertex == null)
                return;
        } else
            return;
        StringBuilder OptionalCommand = new StringBuilder();
        for (int i = 4; i < args.size(); i++) {
            OptionalCommand.append(args.get(i));
        }
        Rule = Pattern.compile("label=(.*)");
        matcher = Rule.matcher(OptionalCommand);
        String newLabel;
        if (matcher.find()) {
            newLabel = matcher.group(1);
            String oldLabel = vertex.setLabel(newLabel);
            System.out.println("Update label successfully , old label is " + oldLabel);
        }
        Rule = Pattern.compile("argument=(.*)");
        matcher = Rule.matcher(OptionalCommand);
        String argument;
        String[] arguments;
        if (matcher.find()) {
            argument = matcher.group(1);
            arguments = argument.split(",");
            vertex.fillVertexInfo(arguments);
            System.out.println("The argument of the vertex update successfully");
        }
    }

    @Override
    void show(List<String> args) {
        Pattern Rule = Pattern.compile("\"(.*)\"");
        Matcher matcher = Rule.matcher(args.get(0));
        String label;
        Vertex vertex;
        if (matcher.find()) {
            label = matcher.group(1);
            vertex = graph.vertices().stream().filter(item -> item.getLabel().equals(label)).findFirst().orElse(null);
            if (vertex == null)
                return;
        } else
            return;
        StringBuilder OptionalCommand = new StringBuilder();
        for (int i = 4; i < args.size(); i++) {
            OptionalCommand.append(args.get(i));
        }
        List<Pattern> Rules = new ArrayList<>();
        Rules.add(Pattern.compile("eccentricity"));
        Rules.add(Pattern.compile("degree"));
        Rules.add(Pattern.compile("indegree"));
        Rules.add(Pattern.compile("outdegree"));
        Rules.add(Pattern.compile("closenessCentrality"));
        Rules.add(Pattern.compile("betweennessCentrality"));
        matcher = Rules.get(0).matcher(OptionalCommand);
        if (matcher.find()) {
            System.out.println("The eccentricity of the vertex : " + GraphMetrics.eccentricity(graph, vertex));
        }
        matcher = Rules.get(1).matcher(OptionalCommand);
        if (matcher.find()) {
            System.out.println("The degree of the vertex : " + GraphMetrics.degreeCentrality(graph, vertex));
        }
        matcher = Rules.get(2).matcher(OptionalCommand);
        if (matcher.find()) {
            System.out.println("The inDegree of the vertex : " + GraphMetrics.inDegreeCentrality(graph, vertex));
        }
        matcher = Rules.get(3).matcher(OptionalCommand);
        if (matcher.find()) {
            System.out.println("The outDegree of the vertex : " + GraphMetrics.outDegreeCentrality(graph, vertex));
        }
        matcher = Rules.get(4).matcher(OptionalCommand);
        if (matcher.find()) {
            System.out.println("The closenessCentrality of the vertex : " + GraphMetrics.closenessCentrality(graph, vertex));
        }
        matcher = Rules.get(5).matcher(OptionalCommand);
        if (matcher.find()) {
            System.out.println("The betweennessCentrality of the vertex : " + GraphMetrics.betweennessCentrality(graph, vertex));
        }
    }
}
