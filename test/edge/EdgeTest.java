package edge;

import Exception.Edge.EdgeVertexTypeException;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import vertex.Person;
import vertex.Vertex;
import vertex.Word;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Edge Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>May 19, 2018</pre>
 */
public class EdgeTest {
    private Edge e1, e2;
    private Vertex v1, v2;

    @Before
    public void before() throws Exception {
        e1 = new WordEdge("e1", 1);
        e2 = new WordEdge("e2", 2);
        v1 = new Word("w1");
        v2 = new Word("w2");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getLabel()
     */
    @Test
    public void testGetLabel() throws Exception {
        assertEquals("e1", e1.getLabel());
        assertEquals("e2", e2.getLabel());
    }

    /**
     * Method: getWeight()
     */
    @Test
    public void testGetWeight() throws Exception {
        assertEquals(1, e1.getWeight(), 0.0001);
        assertEquals(2, e2.getWeight(), 0.0001);
    }

    /**
     * Method: setLabel(String newLabel)
     */
    @Test
    public void testSetLabel() throws Exception {
        e1.setLabel("newE1");
        assertEquals("newE1", e1.getLabel());
        e2.setLabel("newE2");
        assertEquals("newE2", e2.getLabel());
    }

    /**
     * Method: addVertices(List<Vertex> vertices)
     */
    @Test
    public void testAddVertices() throws Exception {
        e1.addVertices(Arrays.asList(v1, v2));
        try {
            e2.addVertices(Collections.singletonList(v1));
            assert (false);
        } catch (RuntimeException e) {
            assert (true);
        }
        // 测试向边中添加别的类型的点
        try {
            v1 = new Person("p1");
            e2.addVertices(Collections.singletonList(v1));
            assert (false);
        } catch (EdgeVertexTypeException e) {
            assert (true);
        }
    }

    /**
     * Method: containVertex(Vertex v)
     */
    @Test
    public void testContainVertex() throws Exception {
        e1.addVertices(Arrays.asList(v1, v2));
        assertTrue(e1.containVertex(v1));
        assertTrue(e1.containVertex(v2));
    }

    /**
     * Method: vertices()
     */
    @Test
    public void testVertices() throws Exception {
        e1.addVertices(Arrays.asList(v1, v2));
        assertEquals(new HashSet<>(Arrays.asList(v1, v2)), e1.vertices());
    }

    /**
     * Method: sourceVertices()
     */
    @Test
    public void testSourceVertices() throws Exception {
        e1.addVertices(Arrays.asList(v1, v2));
        assertEquals(new HashSet<>(Collections.singleton(v1)), e1.sourceVertices());
    }

    /**
     * Method: targetVertices()
     */
    @Test
    public void testTargetVertices() throws Exception {
        e1.addVertices(Arrays.asList(v1, v2));
        assertEquals(new HashSet<>(Collections.singleton(v2)), e1.targetVertices());
    }

    /**
     * Method: setWeight(double weight)
     */
    @Test
    public void testSetWeight() throws Exception {
        e1.setWeight(3);
        assertEquals(3.0, e1.getWeight(), 0.0001);
        e2.setWeight(4);
        assertEquals(4.0, e2.getWeight(), 0.0001);
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
        assertEquals("e1", e1.toString());
        assertEquals("e2", e2.toString());
    }

    /**
     * Method: equals(Object obj)
     */
    @Test
    public void testEqualsObj() throws Exception {
        assertFalse(e1.equals((Object) e2));
        e2.setLabel("e1");
        assertTrue(e1.equals((Object) e2));
    }

    /**
     * Method: equals(Edge edge)
     */
    @Test
    public void testEqualsEdge() throws Exception {
        e1.addVertices(Arrays.asList(v1, v2));
        e2.addVertices(Arrays.asList(v2, v1));
        assertFalse(e1.equals(e2));
        e2 = new WordEdge("e3", 1);
        e2.addVertices(Arrays.asList(v1, v2));
        assertTrue(e1.equals(e2));
    }

    /**
     * Method: hashCode()
     */
    @Test
    public void testHashCode() throws Exception {
        assertEquals(e1.hashCode(), e1.getLabel().hashCode());
        assertEquals(e2.hashCode(), e2.getLabel().hashCode());
    }


} 
