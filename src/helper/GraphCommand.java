package helper;

import Exception.*;
import Exception.Edge.*;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
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
        if (args.size() != 1)
            System.out.println("Error Command!\nGraph --add filepath");
        graph = GraphFactory.createGraph(args.get(0));
        System.out.println("Establish Graph Successfully");
    }

    @Override
    void delete(List<String> args) throws EdgeWeightException {
        if (graph == null) {
            System.out.println("You must Establish Graph First!\nUsage : Graph --add filepath");
            return;
        }
        System.out.println("Don't Support this Command");
    }

    @Override
    void update(List<String> args) throws VertexAttributeException, EdgeWeightException {
        if (graph == null) {
            System.out.println("You must Establish Graph First!\nUsage : Graph --add filepath");
            return;
        }
        System.out.println("Don't Support this Command");
    }

    @Override
    void show(List<String> args) {
        if (graph == null) {
            System.out.println("You must Establish Graph First!\nUsage : Graph --add filepath");
            return;
        }
        Matcher matcher;
        StringBuilder OptionalCommand = new StringBuilder();
        for (String arg : args) {
            OptionalCommand.append(arg);
        }
        List<Pattern> Rules = new ArrayList<>();
        Rules.add(Pattern.compile("degreeCentrality"));
        Rules.add(Pattern.compile("radius"));
        Rules.add(Pattern.compile("diameter"));
        Rules.add(Pattern.compile("visible"));
        Rules.add(Pattern.compile("distance=\"(.*)\"\"(.*)\""));
        matcher = Rules.get(0).matcher(OptionalCommand);
        if (matcher.find()) {
            System.out.println("The degreeCentrality of the graph : " + GraphMetrics.degreeCentrality(graph));
        }
        matcher = Rules.get(1).matcher(OptionalCommand);
        if (matcher.find()) {
            System.out.println("The radius of the graph : " + GraphMetrics.radius(graph));
        }
        matcher = Rules.get(2).matcher(OptionalCommand);
        if (matcher.find()) {
            System.out.println("The diameter of the graph : " + GraphMetrics.diameter(graph));
        }
        matcher = Rules.get(3).matcher(OptionalCommand);
        if (matcher.find()) {
            GraphVisualizationHelper.visualize(graph);
        }
        Matcher newMatcher = Rules.get(4).matcher(OptionalCommand);
        if (matcher.find()) {
            Vertex start, end;
            start = graph.vertices().stream().filter(item -> item.getLabel().equals(newMatcher.group(1))).findFirst().orElse(null);
            end = graph.vertices().stream().filter(item -> item.getLabel().equals(newMatcher.group(2))).findFirst().orElse(null);
            if (start == null || end == null) {
                System.out.println("The input vertex is not in the graph ");
                return;
            }
            System.out.println("The distance between the two vertices : " + GraphMetrics.distance(graph, start, end));
        }
    }
}
