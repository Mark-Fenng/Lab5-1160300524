package vertex;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Router Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>May 19, 2018</pre>
 */
public class RouterTest {
    private Router r1, r2;

    @Before
    public void before() throws Exception {
        r1 = new Router("r1");
        r2 = new Router("r2");
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
        r1.fillVertexInfo(args);
        assertEquals(args[0], r1.getIp());
        args[0] = "100.100.101.100";
        r2.fillVertexInfo(args);
        assertEquals(args[0], r2.getIp());
    }

    /**
     * Method: equals(Object obj)
     */
    @Test
    public void testEquals() throws Exception {
        assertFalse(r1.equals(r2));
        r2.setLabel("r1");
        assertTrue(r1.equals(r2));
        String[] args = new String[1];
        args[0] = "192.168.0.0";
        r1.fillVertexInfo(args);
        args[0] = "192.168.0.1";
        r2.fillVertexInfo(args);
        assertFalse(r1.equals(r2));
    }

    /**
     * Method: hashCode()
     */
    @Test
    public void testHashCode() throws Exception {
        Object[] objects = new Object[2];
        objects[0] = r1.getLabel();
        objects[1] = r1.getIp();
        assertEquals(Arrays.hashCode(objects), r1.hashCode());

        objects[0] = r2.getLabel();
        objects[1] = r2.getIp();
        assertEquals(Arrays.hashCode(objects), r2.hashCode());
    }


    /**
     * Method: getIp()
     */
    @Test
    public void testGetIp() throws Exception {
        String[] args = new String[1];
        args[0] = "192.168.0.0";
        r1.fillVertexInfo(args);
        assertEquals(args[0], r1.getIp());
        args[0] = "192.168.1.1";
        r2.fillVertexInfo(args);
        assertEquals(args[0], r2.getIp());
    }

} 
