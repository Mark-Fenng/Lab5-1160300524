package edge;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import vertex.Actor;
import vertex.Vertex;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * SameMovieHyperEdge Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>May 19, 2018</pre>
 */
public class SameMovieHyperEdgeTest {
    private Edge e1, e2;
    private Vertex v1, v2;

    @Before
    public void before() throws Exception {
        e1 = new SameMovieHyperEdge("e1", 1);
        e2 = new SameMovieHyperEdge("e2", 2);
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


} 
