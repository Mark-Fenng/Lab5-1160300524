package vertex;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Computer Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>May 19, 2018</pre>
 */
public class ComputerTest {

    private Computer c1, c2;

    @Before
    public void before() throws Exception {
        c1 = new Computer("c1");
        c2 = new Computer("c2");
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
        c1.fillVertexInfo(args);
        assertEquals(args[0], c1.getIp());
        args[0] = "100.100.101.100";
        c2.fillVertexInfo(args);
        assertEquals(args[0], c2.getIp());
    }

    /**
     * Method: equals(Object obj)
     */
    @Test
    public void testEquals() throws Exception {
        assertFalse(c1.equals(c2));
        c2.setLabel("c1");
        assertTrue(c1.equals(c2));
        String[] args = new String[1];
        args[0] = "192.168.0.0";
        c1.fillVertexInfo(args);
        args[0] = "192.168.0.1";
        c2.fillVertexInfo(args);
        assertFalse(c1.equals(c2));
    }

    /**
     * Method: hashCode()
     */
    @Test
    public void testHashCode() throws Exception {
        Object[] objects = new Object[2];
        objects[0] = c1.getLabel();
        objects[1] = c1.getIp();
        assertEquals(Arrays.hashCode(objects), c1.hashCode());

        objects[0] = c2.getLabel();
        objects[1] = c2.getIp();
        assertEquals(Arrays.hashCode(objects), c2.hashCode());
    }


    /**
     * Method: getIp()
     */
    @Test
    public void testGetIp() throws Exception {
        String[] args = new String[1];
        args[0] = "192.168.0.0";
        c1.fillVertexInfo(args);
        assertEquals(args[0], c1.getIp());
        args[0] = "192.168.1.1";
        c2.fillVertexInfo(args);
        assertEquals(args[0], c2.getIp());
    }

} 
