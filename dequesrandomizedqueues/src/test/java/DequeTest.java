import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by michal on 11.12.16.
 */
public class DequeTest {

    private Deque<String> deque;

    @Before
    public void setUp() throws Exception {
        deque = new Deque<>();
    }

    @Test
    public void isEmptyTrue() throws Exception {
        assertTrue(deque.isEmpty());
    }

    @Test
    public void isEmptyFalse() throws Exception {
        deque.addFirst("Test");
        assertFalse(deque.isEmpty());

    }

    @Test
    public void size0() throws Exception {
        assertEquals(0, deque.size());
    }

    @Test
    public void size() throws Exception {
        deque.addFirst("Test1");
        assertEquals(1, deque.size());

        deque.addFirst("Test2");
        assertEquals(2, deque.size());

        deque.addLast("Test3");
        assertEquals(3, deque.size());
    }

    @Test
    public void addFirst() throws Exception {
        deque.addFirst("Test 01");
        assertEquals(1, deque.size());
    }

    @Test
    public void addLast() throws Exception {
        deque.addLast("Test 01");
        assertEquals(1, deque.size());
    }

    @Test
    public void removeFirst() throws Exception {

    }

    @Test
    public void removeLast() throws Exception {

    }

    @Test
    public void iterator() throws Exception {

    }

    @Test
    public void testCoursera3() throws Exception {
        deque.addFirst("Test 1");
        deque.addFirst("Test 2");
        Iterator<String> iterator = deque.iterator();
        String next = iterator.next();
        String next2 = iterator.next();
        deque.addFirst("Test 3");
        deque.addFirst("Test 4");
        deque.addFirst("Test 5");
        String next3 = iterator.next();
    }

}