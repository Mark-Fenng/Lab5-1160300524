package vertex;

import Exception.Vertex.VertexAttributeException;
import edge.Edge;
import factory.edge.EdgeFactory;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Person Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>May 19, 2018</pre>
 */
public class PersonTest {
    private Person p1, p2;

    @Before
    public void before() throws Exception {
        p1 = new Person("p1");
        p2 = new Person("p2");
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
        vertices.addAll(Arrays.asList(p1, p2));
        Edge edge = EdgeFactory.createEdge("edge1", "FriendTie", vertices, 1);
        assert p2 != null;
        p2.addInEdge(edge);
        assertEquals(p2.getInEdges().stream().findFirst().orElse(null), edge);

        edge = EdgeFactory.createEdge("edge1", "CommentTie", vertices, 1);
        p2.addInEdge(edge);
        assertEquals(p2.getInEdges().stream().findFirst().orElse(null), edge);

        edge = EdgeFactory.createEdge("edge1", "ForwardTie", vertices, 1);
        p2.addInEdge(edge);
        assertEquals(p2.getInEdges().stream().findFirst().orElse(null), edge);
    }

    /**
     * Method: removeEdge(Edge edge)
     */
    @Test
    public void testRemoveEdge() throws Exception {
        List<Vertex> vertices = new ArrayList<>();
        vertices.addAll(Arrays.asList(p1, p2));
        Edge edge = EdgeFactory.createEdge("edge1", "FriendTie", vertices, 1);
        assert p1 != null;
        p1.addOutEdge(edge);
        assertEquals(p1.getOutEdges().stream().findFirst().orElse(null), edge);
        p1.removeEdge(edge);
        assertEquals(p1.getOutEdges().stream().findFirst().orElse(null), null);
    }

    /**
     * Method: getWeight()
     */
    @Test
    public void testGetWeight() throws Exception {
        List<Vertex> vertices = new ArrayList<>();
        vertices.addAll(Arrays.asList(p1, p2));
        Edge edge = EdgeFactory.createEdge("edge1", "FriendTie", vertices, 1);
        assert p2 != null;
        p2.addInEdge(edge);
        assertEquals(1, p2.getWeight(), 0.001);
    }

    /**
     * Method: fillVertexInfo(String[] args)
     */
    @Test
    public void testFillVertexInfo() throws Exception {
        // 传入正常的参数
        String[] args = new String[2];
        args[0] = "M";
        args[1] = "12";
        p1.fillVertexInfo(args);

        //传入非正常的参数
        args[0] = "K";
        args[1] = "1.2";
        try {
            p1.fillVertexInfo(args);
            assert (false);
        } catch (VertexAttributeException e) {
            assert (true);
        }

        //传入非正常的参数
        args[1] = "-12";
        try {
            p1.fillVertexInfo(args);
            assert (false);
        } catch (VertexAttributeException e) {
            assert (true);
        }
    }

    /**
     * Method: equals(Object obj)
     */
    @Test
    public void testEquals() throws Exception {
        assertFalse(p1.equals(p2));
        p2.setLabel("p1");
        assertTrue(p1.equals(p2));
        String[] args = new String[2];
        args[0] = "M";
        args[1] = "12";
        p1.fillVertexInfo(args);
        args[0] = "F";
        args[1] = "11";
        p2.fillVertexInfo(args);
        assertFalse(p1.equals(p2));
    }

    /**
     * Method: hashCode()
     */
    @Test
    public void testHashCode() throws Exception {
        Object[] objects = new Object[3];
        objects[0] = p1.getLabel();
        objects[1] = p1.getAge();
        objects[2] = p1.getGender();
        assertEquals(Arrays.hashCode(objects), p1.hashCode());
        objects[0] = p2.getLabel();
        objects[1] = p2.getAge();
        objects[2] = p2.getGender();
        assertEquals(Arrays.hashCode(objects), p2.hashCode());
    }


    /**
     * Method: getGender()
     */
    @Test
    public void testGetGender() throws Exception {
        String[] args = new String[2];
        args[0] = "M";
        args[1] = "11";
        p1.fillVertexInfo(args);
        assertEquals(args[0], p1.getGender());
        args[0] = "F";
        args[1] = "12";
        p2.fillVertexInfo(args);
        assertEquals(args[0], p2.getGender());
    }

    /**
     * Method: getAge()
     */
    @Test
    public void testGetAge() throws Exception {
        String[] args = new String[2];
        args[0] = "M";
        args[1] = "11";
        p1.fillVertexInfo(args);
        assertEquals(11, p1.getAge());
        args[0] = "F";
        args[1] = "12";
        p2.fillVertexInfo(args);
        assertEquals(12, p2.getAge());
    }
} 
