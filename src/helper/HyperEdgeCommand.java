package helper;

import Exception.*;
import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeVertexException;
import edge.Edge;
import edge.HyperEdge;
import factory.edge.EdgeFactory;
import graph.Graph;
import vertex.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class HyperEdgeCommand extends Command {
    HyperEdgeCommand(Graph g) {
        super(g);
    }

    @Override
    void add(List<String> args) throws EdgeVertexException, EdgeTypeException, FormatException {
        Pattern Rule = Pattern.compile("\"(.*)\"");
        Matcher matcher = Rule.matcher(args.get(0));
        String label;
        if (matcher.find())
            label = matcher.group(1);
        else
            return;
        String type;
        matcher = Rule.matcher(args.get(1));
        StringBuilder OptionalCommand = new StringBuilder("");
        for (int i = 4; i < args.size(); i++) {
            OptionalCommand.append(args.get(i));
        }
        String[] edgesLabels = OptionalCommand.toString().split("\"");
        List<String> edgesList = new ArrayList<>(Arrays.asList(edgesLabels));
        edgesList.removeIf(item -> item.equals(""));
        List<Vertex> vertices = graph.vertices()
                .stream()
                .filter(item -> edgesList.contains(item.getLabel()))
                .collect(Collectors.toList());
        if (matcher.find()) {
            type = matcher.group(1);
            Edge HyperEdge = EdgeFactory.createEdge(label, type, vertices, -1);
            if (graph.addEdge(HyperEdge))
                System.out.println("Add hyper edge successfully");
            else
                System.err.println("Add fail!");
        } else {
            Edge hyperEdge = graph.edges()
                    .stream()
                    .filter(item -> item.getLabel().equals(label))
                    .findFirst()
                    .orElse(null);
            if (hyperEdge instanceof HyperEdge)
                hyperEdge.addVertices(vertices);
            else
                System.err.println("The edge you input is not hyper edge");
        }
    }

    @Override
    void delete(List<String> args) {
        Pattern Rule = Pattern.compile("\"(.*)\"");
        Matcher matcher = Rule.matcher(args.get(0));
        String label;
        Edge hyperEdge;
        if (matcher.find()) {
            label = matcher.group(1);
            hyperEdge = graph.edges().stream().filter(item -> item.getLabel().equals(label)).findFirst().orElse(null);
            if (hyperEdge == null) {
                System.err.println("Can't find the hyper edge!");
                return;
            }
            matcher = Rule.matcher(args.get(1));
            String regex;
            if (matcher.find()) {
                regex = matcher.group(1);
                List<Vertex> vertices = hyperEdge.vertices()
                        .stream()
                        .filter(item -> Pattern.compile(regex).matcher(item.getLabel()).find())
                        .collect(Collectors.toList());
                if (vertices.size() != 0) {
                    System.out.println(vertices.size() + " vertices are found:");
                    vertices.forEach(item -> System.out.println(item.getLabel()));
                    if (confirm()) {
                        for (Vertex item : vertices) {
                            if (hyperEdge instanceof HyperEdge) {
                                if (!((HyperEdge) hyperEdge).removeVertex(item)) {
                                    System.err.println("The vertex : " + item.getLabel() + " delete failed!");
                                }
                            } else
                                System.err.println("The edge you input is not hyper edge");
                        }
                        System.out.println("Delete them successfully!");
                    }
                }
            }
        } else {
            System.err.println("You don't input the edge label!");
        }
    }

    @Override
    void update(List<String> args) {
        throw new UnsupportedOperationException();
    }

    @Override
    void show(List<String> args) {
        throw new UnsupportedOperationException();
    }
}