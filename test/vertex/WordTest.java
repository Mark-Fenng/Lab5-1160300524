package vertex;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

/**
 * Word Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>May 6, 2018</pre>
 */
public class WordTest {

    private Word word1, word2;

    @Before
    public void before() throws Exception {
        word1 = new Word("word1");
        word2 = new Word("word2");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: fillVertexInfo(String[] args)
     */
    @Test
    public void testFillVertexInfo() throws Exception {
        try {
            word1.fillVertexInfo(new String[0]);
            assert (false); // 如果不抛出异常，就断言为错误
        } catch (UnsupportedOperationException e) {
            assert (true);
        }
    }

    /**
     * Method: equals(Object obj)
     */
    @Test
    public void testEquals() throws Exception {
        // 当两个Word对象的label值相同时，则equals返回true
        assertFalse(word1.equals(word2));
        word1.setLabel("newWord1");
        word2.setLabel("newWord1");
        assertTrue(word1.equals(word2));

        // 当两个Vertex对象是不同的类型时,即使laebl相同，equals依然返回false
        Server server = new Server("newWord1");
        assertFalse(word1.equals(server));
        assertFalse(word2.equals(server));

        // 测试equals是否对Null进行过处理，测试健壮性
        assertFalse(word1.equals(null));
    }


} 
