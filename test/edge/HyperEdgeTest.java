package edge;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import vertex.Actor;
import vertex.Vertex;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * HyperEdge Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>May 19, 2018</pre>
 */
public class HyperEdgeTest {
    private Edge e1, e2;
    private Vertex v1, v2;

    @Before
    public void before() throws Exception {
        e1 = new HyperEdge("e1", 1);
        e2 = new HyperEdge("e2", 2);
        v1 = new Actor("w1");
        v2 = new Actor("w2");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addVertices(List<Vertex> vertices)
     */
    @Test
    public void testAddVertices() throws Exception {
        e1.addVertices(Arrays.asList(v1, v2));
        e2.addVertices(Collections.singletonList(v1));
    }

    /**
     * Method: sourceVertices()
     */
    @Test
    public void testSourceVertices() throws Exception {
        e1.addVertices(Arrays.asList(v1, v2));
        e2.addVertices(Collections.singletonList(v1));
    }

    /**
     * Method: targetVertices()
     */
    @Test
    public void testTargetVertices() throws Exception {
        e1.addVertices(Arrays.asList(v1, v2));
        assertEquals(new HashSet<>(Arrays.asList(v1, v2)), e1.sourceVertices());
    }

    /**
     * Method: removeVertex(Vertex vertex)
     */
    @Test
    public void testRemoveVertex() throws Exception {
        e1.addVertices(Arrays.asList(v1, v2));
        assertEquals(new HashSet<>(Arrays.asList(v1, v2)), e1.sourceVertices());
    }


} 
