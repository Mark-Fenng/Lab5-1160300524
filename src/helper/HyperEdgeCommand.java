package helper;

import Exception.*;
import Exception.Command.CommandException;
import Exception.Edge.*;
import Exception.Graph.GraphNullException;
import Exception.Vertex.VertexAttributeException;
import LoggerFactory.MyLogger;
import edge.Edge;
import edge.HyperEdge;
import factory.edge.EdgeFactory;
import graph.Graph;
import vertex.Vertex;

import java.io.IOException;
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
    void add(List<String> args) throws EdgeNullVertexException, EdgeTypeException, FormatException, IOException, EdgeWeightException, GraphNullException, CommandException {
        if (graph == null)
            throw new GraphNullException("");
        Pattern Rule = Pattern.compile("\"(.*)\"");
        Matcher matcher = Rule.matcher(args.get(0));
        String label;
        if (matcher.find()) {
            label = matcher.group(1);
            args.remove(0);
        } else
            throw new CommandException("Lack the Label of Hyper Edge");
        String type;
        matcher = Rule.matcher(args.get(0));
        StringBuilder OptionalCommand = new StringBuilder("");
        for (String arg : args) {
            OptionalCommand.append(arg);
        }
        String[] edgesLabels = OptionalCommand.toString().split("\"");
        List<String> edgesList = new ArrayList<>(Arrays.asList(edgesLabels));
        edgesList.removeIf(item -> item.equals(""));
        List<Vertex> vertices = graph.vertices()
                .stream()
                .filter(item -> edgesList.contains(item.getLabel()))
                .collect(Collectors.toList());
        // 向图中添加一个全新的超边
        if (matcher.find()) {
            type = matcher.group(1);
            try {
                Edge HyperEdge = EdgeFactory.createEdge(label, type, vertices, -1);
                if (graph.addEdge(HyperEdge)) {
                    MyLogger.info("Add hyper edge successfully");
                    System.out.println("Add hyper edge successfully");
                } else {
                    MyLogger.info("Add fail!");
                    System.out.println("Add fail!");
                }
            } catch (EdgeVertexTypeException | EdgeLoopException e) {
                MyLogger.info("Add fail!");
                System.out.println("Add fail!");
            } catch (HyperEdgeException e) {
                e.printStackTrace();
            }
        } else { // 向图中已有的超边添加点
            Edge hyperEdge = graph.edges()
                    .stream()
                    .filter(item -> item.getLabel().equals(label))
                    .findFirst()
                    .orElse(null);
            if (hyperEdge == null)
                throw new CommandException("The Hyper Edge of This Label can't be found");
            try {
                if (hyperEdge instanceof HyperEdge)
                    hyperEdge.addVertices(vertices);
                else {
                    MyLogger.info("The edge you input is not hyper edge");
                    System.out.println("The edge you input is not hyper edge");
                }
            } catch (EdgeVertexTypeException | EdgeLoopException e) {
                MyLogger.info("Add fail!");
                System.out.println("Add fail!");
            }
        }
    }

    @Override
    void delete(List<String> args) throws EdgeWeightException, GraphNullException, CommandException {
        if (graph == null)
            throw new GraphNullException("");
        Pattern Rule = Pattern.compile("\"(.*)\"");
        Matcher matcher = Rule.matcher(args.get(0));
        String label;
        Edge hyperEdge;
        if (matcher.find()) {
            label = matcher.group(1);
            hyperEdge = graph.edges().stream().filter(item -> item.getLabel().equals(label)).findFirst().orElse(null);
            if (hyperEdge == null)
                throw new CommandException("The Hyper Edge of This Label can't be found");
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
                        StringBuilder InfoMessage = new StringBuilder("Delete vertices: ");
                        for (Vertex item : vertices) {
                            if (hyperEdge instanceof HyperEdge) {
                                if (!((HyperEdge) hyperEdge).removeVertex(item)) {
                                    MyLogger.info("The vertex : " + item.getLabel() + " delete failed!");
                                    System.out.println("The vertex : " + item.getLabel() + " delete failed!");
                                } else {
                                    InfoMessage.append(" ").append(hyperEdge).append(" ");
                                }
                            } else {
                                MyLogger.info("The edge you input is not hyper edge");
                                System.out.println("The edge you input is not hyper edge");
                            }
                        }
                        MyLogger.info(InfoMessage.toString());
                        System.out.println("Delete them successfully!");
                    }
                } else {
                    MyLogger.info("Not found the specific vertex");
                    System.out.println("Not found the specific vertex");
                }
            }
        } else {
            throw new CommandException("Lack the Label of the Hyper Edge");
        }
    }

    @Override
    void update(List<String> args) throws VertexAttributeException, EdgeWeightException, GraphNullException, CommandException {
        if (graph == null)
            throw new GraphNullException("");
        throw new CommandException("");
    }

    @Override
    void show(List<String> args) throws GraphNullException, CommandException {
        if (graph == null)
            throw new GraphNullException("");
        throw new CommandException("");
    }
}