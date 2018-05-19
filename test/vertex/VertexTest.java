package vertex;

import edge.Edge;
import factory.edge.EdgeFactory;
import factory.vertex.VertexFactory;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Vertex Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>May 6, 2018</pre>
 */
public class VertexTest {

    private Vertex v1;
    private Vertex v2;

    @Before
    public void before() throws Exception {
        v1 = VertexFactory.createVertex("v1", "Word", null);
        v2 = VertexFactory.createVertex("v2", "Word", null);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addInEdge(Edge inEdge)
     */
    @Test
    public void testAddInEdge() throws Exception {
        List<Vertex> vertices = new ArrayList<>();
        vertices.addAll(Arrays.asList(v1, v2));
        Edge edge = EdgeFactory.createEdge("edge1", "WordNeighborhood", vertices, 1);
        assert v2 != null;
        v2.addInEdge(edge);
        assertEquals(v2.getInEdges().stream().findFirst().orElse(null), edge);
    }

    /**
     * Method: addOutEdge(Edge outEdge)
     */
    @Test
    public void testAddOutEdge() throws Exception {
        List<Vertex> vertices = new ArrayList<>();
        vertices.addAll(Arrays.asList(v1, v2));
        Edge edge = EdgeFactory.createEdge("edge1", "WordNeighborhood", vertices, 1);
        assert v1 != null;
        v1.addOutEdge(edge);
        assertEquals(v1.getOutEdges().stream().findFirst().orElse(null), edge);
    }

    /**
     * Method: removeEdge(Edge edge)
     */
    @Test
    public void testRemoveEdge() throws Exception {
        List<Vertex> vertices = new ArrayList<>();
        vertices.addAll(Arrays.asList(v1, v2));
        Edge edge = EdgeFactory.createEdge("edge1", "WordNeighborhood", vertices, 1);
        assert v1 != null;
        v1.addOutEdge(edge);
        assertEquals(v1.getOutEdges().stream().findFirst().orElse(null), edge);
        v1.removeEdge(edge);
        assertEquals(v1.getOutEdges().stream().findFirst().orElse(null), null);
    }

    /**
     * Method: getInEdges()
     */
    @Test
    public void testGetInEdges() throws Exception {
        List<Vertex> vertices = new ArrayList<>();
        vertices.addAll(Arrays.asList(v1, v2));
        Edge edge = EdgeFactory.createEdge("edge1", "WordNeighborhood", vertices, 1);
        assert v2 != null;
        v2.addInEdge(edge);
        assertEquals(v2.getInEdges().stream().findFirst().orElse(null), edge);
    }

    /**
     * Method: getOutEdges()
     */
    @Test
    public void testGetOutEdges() throws Exception {
        List<Vertex> vertices = new ArrayList<>();
        vertices.addAll(Arrays.asList(v1, v2));
        Edge edge = EdgeFactory.createEdge("edge1", "WordNeighborhood", vertices, 1);
        assert v1 != null;
        v1.addOutEdge(edge);
        assertEquals(v1.getOutEdges().stream().findFirst().orElse(null), edge);
    }

    /**
     * Method: getLabel()
     */
    @Test
    public void testGetLabel() throws Exception {
        assertEquals("v1", v1.getLabel());
        assertEquals("v2", v2.getLabel());
    }

    /**
     * Method: setLabel(String newLabel)
     */
    @Test
    public void testSetLabel() throws Exception {
        assertEquals("v1", v1.getLabel());
        v1.setLabel("newV1");
        assertEquals("newV1", v1.getLabel());
        assertEquals("v2", v2.getLabel());
        v2.setLabel("newV2");
        assertEquals("newV2", v2.getLabel());
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
        assertEquals("v1", v1.toString());
        assertEquals("v2", v2.toString());
    }

    /**
     * Method: equals(Object obj)
     */
    @Test
    public void testEquals() throws Exception {
        assertFalse(v1.equals(v2));
        v2.setLabel("v1");
        assertTrue(v1.equals(v2));
    }

    /**
     * Method: hashCode()
     */
    @Test
    public void testHashCode() throws Exception {
        assertEquals(v1.getLabel().hashCode(), v1.hashCode());
        assertEquals(v2.getLabel().hashCode(), v2.hashCode());
    }


} 
