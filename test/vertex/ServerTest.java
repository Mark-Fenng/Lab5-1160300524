package vertex;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Server Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>May 19, 2018</pre>
 */
public class ServerTest {
    private Server s1, s2;

    @Before
    public void before() throws Exception {
        s1 = new Server("s1");
        s2 = new Server("s2");
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
        s1.fillVertexInfo(args);
        assertEquals(args[0], s1.getIp());
        args[0] = "100.100.101.100";
        s2.fillVertexInfo(args);
        assertEquals(args[0], s2.getIp());
    }

    /**
     * Method: equals(Object obj)
     */
    @Test
    public void testEquals() throws Exception {
        assertFalse(s1.equals(s2));
        s2.setLabel("s1");
        assertTrue(s1.equals(s2));
        String[] args = new String[1];
        args[0] = "192.168.0.0";
        s1.fillVertexInfo(args);
        args[0] = "192.168.0.1";
        s2.fillVertexInfo(args);
        assertFalse(s1.equals(s2));
    }

    /**
     * Method: hashCode()
     */
    @Test
    public void testHashCode() throws Exception {
        Object[] objects = new Object[2];
        objects[0] = s1.getLabel();
        objects[1] = s1.getIp();
        assertEquals(Arrays.hashCode(objects), s1.hashCode());

        objects[0] = s2.getLabel();
        objects[1] = s2.getIp();
        assertEquals(Arrays.hashCode(objects), s2.hashCode());
    }


    /**
     * Method: getIp()
     */
    @Test
    public void testGetIp() throws Exception {
        String[] args = new String[1];
        args[0] = "192.168.0.0";
        s1.fillVertexInfo(args);
        assertEquals(args[0], s1.getIp());
        args[0] = "192.168.1.1";
        s2.fillVertexInfo(args);
        assertEquals(args[0], s2.getIp());
    }

} 
