package vertex;

import Exception.Vertex.VertexAttributeException;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Actor Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>May 19, 2018</pre>
 */
public class ActorTest {
    private Actor a1, a2;

    @Before
    public void before() throws Exception {
        a1 = new Actor("a1");
        a2 = new Actor("a2");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: fillVertexInfo(String[] args)
     */
    @Test
    public void testFillVertexInfo() throws Exception {
        // 传入正常的参数
        String[] args = new String[2];
        args[0] = "12";
        args[1] = "M";
        a1.fillVertexInfo(args);

        //传入非正常的参数
        args[0] = "1.2";
        args[1] = "K";
        try {
            a1.fillVertexInfo(args);
            assert (false);
        } catch (VertexAttributeException e) {
            assert (true);
        }

        //传入非正常的参数
        args[1] = "-12";
        try {
            a1.fillVertexInfo(args);
            assert (false);
        } catch (VertexAttributeException e) {
            assert (true);
        }
    }

    /**
     * Method: getGender()
     */
    @Test
    public void testGetGender() throws Exception {
        String[] args = new String[2];
        args[0] = "12";
        args[1] = "M";
        a1.fillVertexInfo(args);
        assertEquals(args[1], a1.getGender());
        args[0] = "12";
        args[1] = "F";
        a2.fillVertexInfo(args);
        assertEquals(args[1], a2.getGender());
    }

    /**
     * Method: getAge()
     */
    @Test
    public void testGetAge() throws Exception {
        String[] args = new String[2];
        args[0] = "11";
        args[1] = "M";
        a1.fillVertexInfo(args);
        assertEquals(11, a1.getAge());
        args[0] = "12";
        args[1] = "F";
        a2.fillVertexInfo(args);
        assertEquals(12, a2.getAge());
    }

    /**
     * Method: equals(Object obj)
     */
    @Test
    public void testEquals() throws Exception {
        assertFalse(a1.equals(a2));
        a2.setLabel("a1");
        assertTrue(a1.equals(a2));
        String[] args = new String[2];
        args[0] = "12";
        args[1] = "M";
        a1.fillVertexInfo(args);
        args[0] = "12";
        args[1] = "F";
        a2.fillVertexInfo(args);
        assertFalse(a1.equals(a2));
    }

    /**
     * Method: hashCode()
     */
    @Test
    public void testHashCode() throws Exception {
        Object[] objects = new Object[3];
        objects[0] = a1.getLabel();
        objects[1] = a1.getAge();
        objects[2] = a1.getGender();
        assertEquals(Arrays.hashCode(objects), a1.hashCode());
        objects[0] = a2.getLabel();
        objects[1] = a2.getAge();
        objects[2] = a2.getGender();
        assertEquals(Arrays.hashCode(objects), a2.hashCode());
    }
} 
