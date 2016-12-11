import org.junit.Before;
import org.junit.Test;

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
    public void size() throws Exception {
        assertEquals(0, deque.size());
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

}