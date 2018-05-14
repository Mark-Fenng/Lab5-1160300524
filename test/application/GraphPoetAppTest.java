package application;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * GraphPoetApp Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>April 18, 2018</pre>
 */
public class GraphPoetAppTest {

    private GraphPoetApp poetApp;

    @Before
    public void before() throws Exception {
        poetApp = new GraphPoetApp("test/graph/data/GraphPoet.txt");
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testGetBridge() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getBridge = poetApp.getClass().getDeclaredMethod("getBridge", String.class, String.class);
        getBridge.setAccessible(true);
        assertEquals("strange", getBridge.invoke(poetApp, "explore", "new"));
        assertEquals("life", getBridge.invoke(poetApp, "new", "and"));
    }

    /**
     * Method: poem(String input)
     */
    @Test
    public void testPoem() throws Exception {
        assertEquals("Seek to explore strange new life and exciting synergies!", poetApp.poem("Seek to explore new and exciting synergies!"));
        assertEquals("Seek explore strange new life and exciting synergies!", poetApp.poem("Seek explore new and exciting synergies!"));
/*
try {
   Method method = GraphPoetApp.getClass().getMethod("poem", String.class);
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
    }
} 
