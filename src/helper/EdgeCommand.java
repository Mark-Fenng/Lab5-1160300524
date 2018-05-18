package helper;

import Exception.*;
import Exception.Command.CommandException;
import Exception.Edge.*;
import Exception.Graph.GraphNullException;
import Exception.Vertex.VertexAttributeException;
import edge.Edge;
import factory.edge.EdgeFactory;
import graph.Graph;
import vertex.Vertex;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class EdgeCommand extends Command {
    EdgeCommand(Graph g) {
        super(g);
    }

    @Override
    void add(List<String> args) throws EdgeNullVertexException, EdgeTypeException, FormatException, HyperEdgeException, EdgeWeightException, GraphNullException, CommandException {
        if (graph == null) {
            throw new GraphNullException("");
        }
        Pattern Rule = Pattern.compile("\"(.*)\"");
        Matcher matcher = Rule.matcher(args.get(0));
        String label;
        if (matcher.find()) {
            label = matcher.group(1);
            args.remove(0);
        } else
            throw new CommandException("Lack the Edge Label");
        String type;
        matcher = Rule.matcher(args.get(0));
        if (matcher.find()) {
            type = matcher.group(1);
            StringBuilder OptionalCommand = new StringBuilder();
            for (String arg : args)
                OptionalCommand.append(arg);
            Rule = Pattern.compile("\"(.*)\"\"(.*)\"");
            matcher = Rule.matcher(OptionalCommand);
            String label1, label2;
            if (matcher.find()) {
                label1 = matcher.group(1);
                label2 = matcher.group(2);
            } else
                throw new CommandException("Lack Vertices of the Edge");
            Rule = Pattern.compile("weighted=([Y|N])");
            matcher = Rule.matcher(OptionalCommand);
            boolean weighted = false;
            double weight = -1;
//            boolean directed = true;
            if (matcher.find()) {
                weighted = matcher.group(1).equals("Y");
            }
            // 匹配可选项 weighted
            Rule = Pattern.compile("[0-9]+/.?[0-9]*");
            matcher = Rule.matcher(OptionalCommand);
            if (matcher.find()) {
                weight = Double.parseDouble(matcher.group(1));
            }
            if (!weighted && weight != -1)
                throw new EdgeWeightException(label, "" + weight);
            // 匹配可选项 weight
//            Rule = Pattern.compile("directed=([Y|N])");
//            matcher = Rule.matcher(OptionalCommand);
//            if (matcher.find()) {
//                directed = matcher.group(1).equals("Y");
//            }
//            if(directed){
//
//            }
            // 匹配可选项 directed
            List<Vertex> vertices = graph.vertices().
                    stream().
                    filter(item -> item.getLabel().equals(label1) || item.getLabel().equals(label2))
                    .collect(Collectors.toList());
            try {
                Edge newEdge = EdgeFactory.createEdge(label, type, vertices, weight);
                if (graph.addEdge(newEdge))
                    System.out.println("Add edge successfully");
                else
                    System.out.println("Add fail!");
            } catch (EdgeVertexTypeException | EdgeLoopException e) {
                System.out.println("Add fail!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    void delete(List<String> args) throws EdgeWeightException, GraphNullException, CommandException {
        if (graph == null)
            throw new GraphNullException("");
        Pattern Rule = Pattern.compile("\"(.*)\"");
        Matcher matcher = Rule.matcher(args.get(0));
        String regex;
        if (matcher.find()) {
            regex = matcher.group(1);
            Pattern InputRule = Pattern.compile(regex);
            List<Edge> edges = graph.edges().stream().filter(item -> InputRule.matcher(item.getLabel()).find()).collect(Collectors.toList());
            if (edges.size() != 0) {
                System.out.println(edges.size() + " edges are found:");
                edges.forEach(item -> System.out.println(item.getLabel()));
                // 请用户确认是否删除这些内容
                if (Command.confirm()) {
                    for (Edge edge : edges) {
                        graph.removeEdge(edge);
                    }
                    System.out.println("Delete them successfully");
                }
            } else {
                System.out.println("Not found the specific edge");
            }
        } else
            throw new CommandException("Lack the Regex");
    }

    @Override
    void update(List<String> args) throws EdgeWeightException, VertexAttributeException, CommandException, GraphNullException {
        if (graph == null) {
            throw new GraphNullException("");
        }
        Pattern Rule = Pattern.compile("\"(.*)\"");
        Matcher matcher = Rule.matcher(args.get(0));
        String label;
        Edge edge;
        if (matcher.find()) {
            label = matcher.group(1);
            edge = graph.edges().stream().filter(item -> item.getLabel().equals(label)).findFirst().orElse(null);
            if (edge == null)
                throw new CommandException("The Edge of this Label is not Found");
            args.remove(0);
        } else
            throw new CommandException("Lack the Edge Label");
        Rule = Pattern.compile("weight=([0-9]+/.?[0-9]*)");
        StringBuilder OptionalCommand = new StringBuilder();
        for (String arg : args) {
            OptionalCommand.append(arg);
        }
        boolean CommandFlag = false; // 用于标记以上的命令是否有执行过，如果在函数结束时，值还是false，则抛出异常
        matcher = Rule.matcher(OptionalCommand);
        double weight;
        if (matcher.find()) {
            weight = Double.parseDouble(matcher.group(1));
            edge.setWeight(weight);
            System.out.println("Update weight successfully");
            CommandFlag = true;
        }
        Rule = Pattern.compile("label=(.*)");
        matcher = Rule.matcher(OptionalCommand);
        String newLabel;
        if (matcher.find()) {
            newLabel = matcher.group(1);
            String oldLabel = edge.setLabel(newLabel);
            System.out.println("Update label successfully , the old label is " + oldLabel);
            CommandFlag = true;
        }
        if (!CommandFlag)
            throw new CommandException(""); // 命令输入的参数有误
    }

    @Override
    void show(List<String> args) throws GraphNullException, CommandException {
        if (graph == null) {
            throw new GraphNullException("");
        }
        throw new CommandException("");
    }
}
