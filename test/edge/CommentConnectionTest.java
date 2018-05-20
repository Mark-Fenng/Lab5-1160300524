package edge;

import Exception.Edge.EdgeVertexTypeException;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import vertex.Person;
import vertex.Vertex;
import vertex.Word;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * CommentConnection Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>May 19, 2018</pre>
 */
public class CommentConnectionTest {
    private Edge e1, e2;
    private Vertex v1, v2;

    @Before
    public void before() throws Exception {
        e1 = new CommentConnection("e1", 1);
        e2 = new CommentConnection("e2", 2);
        v1 = new Person("w1");
        v2 = new Person("w2");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addVertices(List<Vertex> vertices)
     */
    @Test
    public void testAddVertices() throws Exception {
        e1.addVertices(Arrays.asList(v1, v2));
        try {
            e2.addVertices(Collections.singletonList(v1));
            assert (false);
        } catch (RuntimeException e) {
            assert (true);
        }
        // 测试向边中添加别的类型的点
        try {
            v1 = new Word("w1");
            e2.addVertices(Collections.singletonList(v1));
            assert (false);
        } catch (EdgeVertexTypeException e) {
            assert (true);
        }
    }


} 
