package vertex;

import Exception.Vertex.VertexAttributeException;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Director Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>May 19, 2018</pre>
 */
public class DirectorTest {

    private Director d1, d2;

    @Before
    public void before() throws Exception {
        d1 = new Director("d1");
        d2 = new Director("d2");
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
        args[1] = "12";
        args[0] = "M";
        d1.fillVertexInfo(args);

        //传入非正常的参数
        args[1] = "1.2";
        args[0] = "K";
        try {
            d1.fillVertexInfo(args);
            assert (false);
        } catch (VertexAttributeException e) {
            assert (true);
        }

        //传入非正常的参数
        args[1] = "-12";
        try {
            d1.fillVertexInfo(args);
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
        assertFalse(d1.equals(d2));
        d2.setLabel("d1");
        assertTrue(d1.equals(d2));
        String[] args = new String[2];
        args[1] = "12";
        args[0] = "M";
        d1.fillVertexInfo(args);
        args[1] = "12";
        args[0] = "F";
        d2.fillVertexInfo(args);
        assertFalse(d1.equals(d2));
    }

    /**
     * Method: getGender()
     */
    @Test
    public void testGetGender() throws Exception {
        String[] args = new String[2];
        args[1] = "12";
        args[0] = "M";
        d1.fillVertexInfo(args);
        assertEquals(args[0], d1.getGender());
        args[1] = "12";
        args[0] = "F";
        d2.fillVertexInfo(args);
        assertEquals(args[0], d2.getGender());
    }

    /**
     * Method: getAge()
     */
    @Test
    public void testGetAge() throws Exception {
        String[] args = new String[2];
        args[1] = "11";
        args[0] = "M";
        d1.fillVertexInfo(args);
        assertEquals(11, d1.getAge());
        args[1] = "12";
        args[0] = "F";
        d2.fillVertexInfo(args);
        assertEquals(12, d2.getAge());
    }

    /**
     * Method: hashCode()
     */
    @Test
    public void testHashCode() throws Exception {
        Object[] objects = new Object[3];
        objects[0] = d1.getLabel();
        objects[1] = d1.getAge();
        objects[2] = d1.getGender();
        assertEquals(Arrays.hashCode(objects), d1.hashCode());
        objects[0] = d2.getLabel();
        objects[1] = d2.getAge();
        objects[2] = d2.getGender();
        assertEquals(Arrays.hashCode(objects), d2.hashCode());
    }


} 
