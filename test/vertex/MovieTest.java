package vertex;

import Exception.Vertex.VertexAttributeException;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Movie Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>May 19, 2018</pre>
 */
public class MovieTest {

    private Movie m1, m2;

    @Before
    public void before() throws Exception {
        m1 = new Movie("m1");
        m2 = new Movie("m2");
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
        String[] args = new String[3];
        args[0] = "2012";
        args[1] = "China";
        args[2] = "9.58";
        m1.fillVertexInfo(args);

        //传入非正常的参数
        args[0] = "2019";
        args[1] = "China";
        args[2] = "10.02";
        try {
            m1.fillVertexInfo(args);
            assert (false);
        } catch (VertexAttributeException e) {
            assert (true);
        }

        //传入非正常的参数
        args[0] = "1899";
        args[1] = "China";
        args[2] = "-0.01";
        try {
            m1.fillVertexInfo(args);
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
        assertFalse(m1.equals(m2));
        m2.setLabel("m1");
        assertTrue(m1.equals(m2));
        String[] args = new String[3];
        args[0] = "1900";
        args[1] = "China";
        args[2] = "9.4";
        m1.fillVertexInfo(args);
        args[0] = "1900";
        args[1] = "China";
        args[2] = "9.44";
        m2.fillVertexInfo(args);
        assertFalse(m1.equals(m2));
        args[0] = "1901";
        args[1] = "China";
        args[2] = "9.4";
        m2.fillVertexInfo(args);
        assertFalse(m1.equals(m2));
        args[0] = "1900";
        args[1] = "America";
        args[2] = "9.4";
        m2.fillVertexInfo(args);
        assertFalse(m1.equals(m2));
    }

    /**
     * Method: hashCode()
     */
    @Test
    public void testHashCode() throws Exception {
        Object[] objects = new Object[4];
        objects[0] = m1.getLabel();
        objects[1] = m1.getYear();
        objects[2] = m1.getIMDb();
        objects[3] = m1.getCountry();
        assertEquals(Arrays.hashCode(objects), m1.hashCode());
        objects[0] = m2.getLabel();
        objects[1] = m2.getYear();
        objects[2] = m2.getIMDb();
        objects[3] = m2.getCountry();
        assertEquals(Arrays.hashCode(objects), m2.hashCode());
    }

    /**
     * Method: getYear()
     */
    @Test
    public void testGetYear() throws Exception {
        String[] args = new String[3];
        args[0] = "1900";
        args[1] = "China";
        args[2] = "9.4";
        m1.fillVertexInfo(args);
        assertEquals(1900, m1.getYear());
        args[0] = "2018";
        m2.fillVertexInfo(args);
        assertEquals(2018, m2.getYear());
    }

    /**
     * Method: getCountry()
     */
    @Test
    public void testGetCountry() throws Exception {
        String[] args = new String[3];
        args[0] = "1900";
        args[1] = "China";
        args[2] = "9.4";
        m1.fillVertexInfo(args);
        assertEquals("China", m1.getCountry());
        args[1] = "America";
        m2.fillVertexInfo(args);
        assertEquals("America", m2.getCountry());
    }

    /**
     * Method: getIMDb()
     */
    @Test
    public void testGetIMDb() throws Exception {
        String[] args = new String[3];
        args[0] = "1900";
        args[1] = "China";
        args[2] = "7.62";
        m1.fillVertexInfo(args);
        assertEquals(7.62, m1.getIMDb(), 0.00001);
        args[2] = "5.56";
        m2.fillVertexInfo(args);
        assertEquals(5.56, m2.getIMDb(), 0.00001);
    }
} 
