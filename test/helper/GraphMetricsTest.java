package helper;

import Exception.Command.UnsupportedException;
import factory.graph.*;
import graph.Graph;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import vertex.Vertex;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * GraphMetrics Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>April 21, 2018</pre>
 */
public class GraphMetricsTest {

    private Graph poet, socialNetwork, topologyNetwork, movie;

    @Before
    public void before() throws Exception {
        poet = GraphFactory.createGraph("test/graph/data/GraphPoet.txt");
        socialNetwork = GraphFactory.createGraph("test/graph/data/GraphSocial.txt");
        topologyNetwork = GraphFactory.createGraph("test/graph/data/GraphTopology.txt");
        movie = GraphFactory.createGraph("test/graph/data/GraphMovie.txt");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: degreeCentrality(Graph g)
     */
    @Test
    public void testGraphDegreeCentrality() throws Exception {
        assertEquals(1, GraphMetrics.degreeCentrality(topologyNetwork), 0.001);
    }

    /**
     * Method: degreeCentrality(Graph g, Vertex v)
     */
    @Test
    public void testDegreeCentrality() throws Exception {
        assertEquals(1, GraphMetrics.degreeCentrality(topologyNetwork, topologyNetwork.vertices().stream().filter(item -> item.getLabel().equals("Computer1")).findFirst().orElse(null)), 0.0001);
        assertEquals(2, GraphMetrics.degreeCentrality(topologyNetwork, topologyNetwork.vertices().stream().filter(item -> item.getLabel().equals("Server1")).findFirst().orElse(null)), 0.0001);
    }

    /**
     * Method: inDegreeCentrality(Graph g, Vertex v)
     */
    @Test
    public void testInDegreeCentrality() throws Exception {
        try {
            System.out.println(GraphMetrics.degreeCentrality(poet, poet.vertices().stream().filter(item -> item.getLabel().equals("to")).findFirst().orElse(null)));
            assert (false);
        } catch (UnsupportedException ignored) {
        }
        assertEquals(1, GraphMetrics.inDegreeCentrality(poet, poet.vertices().stream().filter(item -> item.getLabel().equals("to")).findFirst().orElse(null)), 0.00001);
    }

    /**
     * Method: outDegreeCentrality(Graph g, Vertex v)
     */
    @Test
    public void testOutDegreeCentrality() throws Exception {
        assertEquals(3, GraphMetrics.outDegreeCentrality(poet, poet.vertices().stream().filter(item -> item.getLabel().equals("new")).findFirst().orElse(null)), 0.00001);
    }

    /**
     * Method: closenessCentrality(Graph g, Vertex v)
     */
    @Test
    public void testClosenessCentrality() throws Exception {
        assertEquals(5.166666, GraphMetrics.closenessCentrality(poet, poet.vertices().stream().filter(item -> item.getLabel().equals("new")).findFirst().orElse(null)), 0.00001);
    }

    /**
     * Method: betweennessCentrality(Graph g, Vertex v)
     */
    @Test
    public void testBetweennessCentrality() throws Exception {
        assertEquals(11.5, GraphMetrics.betweennessCentrality(poet, poet.vertices().stream().filter(item -> item.getLabel().equals("seek")).findFirst().orElse(null)), 0.00001);
        assertEquals(8, GraphMetrics.betweennessCentrality(poet, poet.vertices().stream().filter(item -> item.getLabel().equals("and")).findFirst().orElse(null)), 0.00001);
    }

    /**
     * Method: distance(Graph g, Vertex start, Vertex end)
     */
    @Test
    public void testDistance() throws Exception {
        assertEquals(4, GraphMetrics.distance(poet, poet.vertices().stream().filter(item -> item.getLabel().equals("seek")).findFirst().orElse(null), poet.vertices().stream().filter(item -> item.getLabel().equals("and")).findFirst().orElse(null)), 0.00001);
        assertEquals(5, GraphMetrics.distance(poet, poet.vertices().stream().filter(item -> item.getLabel().equals("to")).findFirst().orElse(null), poet.vertices().stream().filter(item -> item.getLabel().equals("and")).findFirst().orElse(null)), 0.00001);
    }

    /**
     * Method: eccentricity(Graph g, Vertex v)
     */
    @Test
    public void testEccentricity() throws Exception {
        assertEquals(6, GraphMetrics.eccentricity(poet, poet.vertices().stream().filter(item -> item.getLabel().equals("seek")).findFirst().orElse(null)), 0.00001);
        assertEquals(5, GraphMetrics.eccentricity(poet, poet.vertices().stream().filter(item -> item.getLabel().equals("and")).findFirst().orElse(null)), 0.00001);
        assertEquals(Double.POSITIVE_INFINITY, GraphMetrics.eccentricity(poet, poet.vertices().stream().filter(item -> item.getLabel().equals("civilizations")).findFirst().orElse(null)), 0.00001);
    }

    /**
     * Method: radius(Graph g)
     */
    @Test
    public void testRadius() throws Exception {
        assertEquals(4, GraphMetrics.radius(poet), 0.00001);
    }

    /**
     * Method: diameter(Graph g)
     */
    @Test
    public void testDiameter() throws Exception {
        assertEquals(Double.POSITIVE_INFINITY, GraphMetrics.diameter(poet), 0.00001);
    }


    /**
     * Method: getpath(int start, int end, int[][] path, List<Integer> router)
     */
    @Test
    public void testGetpath() throws Exception {
        List<Vertex> vertices = new ArrayList<>();
        Vertex vertex = poet.vertices().stream().filter(item -> item.getLabel().equals("seek")).findFirst().orElse(null);
        vertices.add(vertex);
        vertices.addAll(poet.vertices());
        vertices.forEach(item -> System.out.println(item.getLabel()));
        double[][] e = new double[vertices.size() + 1][vertices.size() + 1];
        List<List<List<Integer>>> path = new ArrayList<>();
        List<List<Integer>> router = new ArrayList<>();
        GraphMetrics temp = new GraphMetrics();
        Method method = temp.getClass().getDeclaredMethod("floyd", List.class, double[][].class, List.class);
        method.setAccessible(true);
        method.invoke(temp, vertices, e, path);
        method = temp.getClass().getDeclaredMethod("getpath", int.class, int.class, List.class, List.class, int.class);
        method.setAccessible(true);
        for (Vertex a : vertices) {
            System.out.println(a.getLabel());
        }
        method.invoke(temp, 1, 5, path, router, 0);
        assertEquals(new ArrayList<List<Integer>>(Collections.singletonList(new ArrayList<>(Arrays.asList(2, 5)))), router);
        router = new ArrayList<>();
        method.invoke(temp, 5, 4, path, router, 0);
        assertEquals(new ArrayList<>(Arrays.asList(new ArrayList<>(Arrays.asList(3, 8, 1, 9, 4)), new ArrayList<>(Arrays.asList(7, 10, 1, 9, 7, 10, 1, 9, 4)))), router);
/*
try {
   Method method = GraphMetrics.getClass().getMethod("getpath", int.class, int.class, int[][].class, List<Integer>.class);
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
    }
} 
