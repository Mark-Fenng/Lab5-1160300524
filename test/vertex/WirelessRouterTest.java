package vertex;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * WirelessRouter Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>May 19, 2018</pre>
 */
public class WirelessRouterTest {

    private WirelessRouter w1, w2;

    @Before
    public void before() throws Exception {
        w1 = new WirelessRouter("w1");
        w2 = new WirelessRouter("w2");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: fillVertexInfo(String[] args)
     */
    @Test
    public void testFillVertexInfo() throws Exception {
        String[] args = new String[1];
        args[0] = "192.168.0.0";
        w1.fillVertexInfo(args);
        assertEquals(args[0], w1.getIp());
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
        assertEquals("w1", w1.toString());
        assertEquals("w2", w2.toString());
    }

    /**
     * Method: equals(Object obj)
     */
    @Test
    public void testEquals() throws Exception {
        assertFalse(w1.equals(w2));
        w2.setLabel("w1");
        assertTrue(w1.equals(w2));
        String[] args = new String[1];
        args[0] = "192.168.0.0";
        w1.fillVertexInfo(args);
        args[0] = "192.168.0.1";
        w2.fillVertexInfo(args);
        assertFalse(w1.equals(w2));
    }

    /**
     * Method: hashCode()
     */
    @Test
    public void testHashCode() throws Exception {
        Object[] objects = new Object[2];
        objects[0] = w1.getLabel();
        objects[1] = w1.getIp();
        assertEquals(Arrays.hashCode(objects), w1.hashCode());

        objects[0] = w2.getLabel();
        objects[1] = w2.getIp();
        assertEquals(Arrays.hashCode(objects), w2.hashCode());
    }


    /**
     * Method: getIp()
     */
    @Test
    public void testGetIp() throws Exception {
        String[] args = new String[1];
        args[0] = "192.168.0.0";
        w1.fillVertexInfo(args);
        assertEquals(args[0], w1.getIp());
        args[0] = "192.168.1.1";
        w2.fillVertexInfo(args);
        assertEquals(args[0], w2.getIp());
    }

} 
