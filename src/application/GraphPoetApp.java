package application;

import graph.*;
import helper.ParseCommandHelper;
import vertex.Vertex;

import java.util.*;

class GraphPoetApp {
    private Graph graphPoet;

    public static void main(String[] args) {
        new GraphPoetApp();
    }

    GraphPoetApp() {
        ParseCommandHelper.Command();
    }

    /**
     * Generate a poem.
     *
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    String poem(String input) {
        List<String> inputWords;
        String[] test = input.split(" ");
        inputWords = new LinkedList<>(Arrays.asList(test));
        ListIterator<String> iterator = inputWords.listIterator();
        if (!iterator.hasNext()) {
            return " ";
        }
        String previous = iterator.next();
        String next;
        StringBuilder result;
        while (iterator.hasNext()) {
            next = iterator.next();
            result = new StringBuilder(getBridge(previous, next));
            if (!result.toString().equals("")) {
                iterator.previous();
                iterator.add(result.toString());
                iterator.next();
            }
            previous = next;
        }
        result = new StringBuilder();
        for (String words : inputWords) {
            result.append(words).append(" ");
        }
        return result.substring(0, result.length() - 1);
    }

    /**
     * this function is to search the bridge word according to the given two words in the graph
     *
     * @param source : the source vertex of the edge, 'source' is followed by 'target'
     * @param target : the target vertex of the edge
     * @return if the bridge is existed,return the bridge word, "" is returned otherwise
     */
    private String getBridge(String source, String target) {
        source = source.toLowerCase();
        target = target.toLowerCase();
        String finalSource = source;
        Vertex sourceVertex = graphPoet.vertices().stream().filter(item -> item.getLabel().equals(finalSource)).findFirst().orElse(null);
        String finalTarget = target;
        Vertex targetVertex = graphPoet.vertices().stream().filter(item -> item.getLabel().equals(finalTarget)).findFirst().orElse(null);
        if (sourceVertex == null || targetVertex == null) {
            return "";
        }
        Map<Vertex, List<Double>> bridges = new HashMap<>();
        Map<Vertex, List<Double>> middleTargets;
        Map<Vertex, List<Double>> finalTargets;
        middleTargets = graphPoet.targets(sourceVertex);
        for (Map.Entry<Vertex, List<Double>> entry : middleTargets.entrySet()) {
            finalTargets = graphPoet.targets(entry.getKey());
            if (finalTargets.keySet().contains(targetVertex)) {
                bridges.put(entry.getKey(), new ArrayList<>(Collections.singletonList(entry.getValue().get(0) + finalTargets.get(targetVertex).get(0))));
            }
        }
        // use to sort the bridges by weight
        List<Map.Entry<Vertex, List<Double>>> sortedBridges = new ArrayList<>(bridges.entrySet());
        // use sort and lambda expression
        sortedBridges.sort((Map.Entry<Vertex, List<Double>> o1, Map.Entry<Vertex, List<Double>> o2) -> o2.getValue().get(0).compareTo(o1.getValue().get(0)));
        if (!sortedBridges.isEmpty())
            return sortedBridges.get(0).getKey().getLabel();
        return "";
    }
}
