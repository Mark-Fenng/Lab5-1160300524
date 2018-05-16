package helper;

import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeVertexException;
import edge.Edge;
import factory.edge.EdgeFactory;
import graph.Graph;
import vertex.Vertex;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class EdgeCommand extends Command {
    EdgeCommand(Graph g) {
        super(g);
    }

    @Override
    void add(List<String> args) throws EdgeVertexException, EdgeTypeException {
        Pattern Rule = Pattern.compile("\"(.*)\"");
        Matcher matcher = Rule.matcher(args.get(0));
        String label;
        if (matcher.find())
            label = matcher.group(1);
        else
            return;
        String type;
        matcher = Rule.matcher(args.get(1));
        if (matcher.find()) {
            type = matcher.group(1);
            StringBuilder OptionalCommand = new StringBuilder();
            for (int i = 2; i < args.size(); i++) {
                OptionalCommand.append(args.get(i));
            }
            Rule = Pattern.compile("\"(.*)\"\"(.*)\"");
            matcher = Rule.matcher(OptionalCommand);
            String label1, label2;
            if (matcher.find()) {
                label1 = matcher.group(1);
                label2 = matcher.group(2);
            } else
                return;
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
                return;   // 此处有待 lab4处理
            // 匹配可选项 weight
//            Rule = Pattern.compile("directed=([Y|N])");
//            matcher = Rule.matcher(OptionalCommand);
//            if (matcher.find()) {
//                directed = matcher.group(1).equals("Y");
//            }
//            if(directed){
//                to do something in lab4
//            }
            // 匹配可选项 directed
            List<Vertex> vertices = graph.vertices().
                    stream().
                    filter(item -> item.getLabel().equals(label1) || item.getLabel().equals(label2))
                    .collect(Collectors.toList());
            Edge newEdge = EdgeFactory.createEdge(label, type, vertices, weight);
            if (graph.addEdge(newEdge))
                System.out.println("Add edge successfully");
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
            List<Edge> edges = graph.edges().stream().filter(item -> InputRule.matcher(item.getLabel()).find()).collect(Collectors.toList());
            if (edges.size() != 0) {
                System.out.println(edges.size() + " edges are found:");
                edges.forEach(item -> System.out.println(item.getLabel()));
                // 请用户确认是否删除这些内容
                if (Command.confirm()) {
                    edges.forEach(item -> graph.removeEdge(item));
                    System.out.println("Delete them successfully");
                }
            } else {
                System.out.println("Not found the specific edge");
            }
        }
    }

    @Override
    void update(List<String> args) {
        Pattern Rule = Pattern.compile("\"(.*)\"");
        Matcher matcher = Rule.matcher(args.get(0));
        String label;
        Edge edge;
        if (matcher.find()) {
            label = matcher.group(1);
            edge = graph.edges().stream().filter(item -> item.getLabel().equals(label)).findFirst().orElse(null);
            if (edge == null)
                return;
        } else
            return;
        Rule = Pattern.compile("weight=([0-9]+/.?[0-9]*)");
        StringBuilder OptionalCommand = new StringBuilder();
        for (int i = 1; i < args.size(); i++) {
            OptionalCommand.append(args.get(i));
        }
        matcher = Rule.matcher(OptionalCommand);
        double weight;
        if (matcher.find()) {
            weight = Double.parseDouble(matcher.group(1));
            edge.setWeight(weight);
            System.out.println("Update weight successfully");
        }
        Rule = Pattern.compile("label=(.*)");
        matcher = Rule.matcher(OptionalCommand);
        String newLabel;
        if (matcher.find()) {
            newLabel = matcher.group(1);
            String oldLabel = edge.setLabel(newLabel);
            System.out.println("Update label successfully , the old label is " + oldLabel);
        }
    }

    @Override
    void show(List<String> args) {
        throw new UnsupportedOperationException();
    }
}
