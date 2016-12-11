import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by michal on 11.12.16.
 */
public class RandomizedQueueTest {
    RandomizedQueue<String> randomizedQueue;

    @Before
    public void setUp() throws Exception {

        randomizedQueue = new RandomizedQueue<>();
    }

    @Test
    public void isEmpty() throws Exception {

    }

    @Test
    public void size() throws Exception {

    }

    @Test
    public void enqueue() throws Exception {

    }

    @Test
    public void dequeue() throws Exception {
        randomizedQueue.enqueue("Test1");
        randomizedQueue.enqueue("Test2");
        randomizedQueue.enqueue("Test3");
        randomizedQueue.enqueue("Test4");
        randomizedQueue.enqueue("Test5");

        Iterator<String> iterator = randomizedQueue.iterator();
        String s1 = iterator.next();
        String s2 = iterator.next();
        String s3 = iterator.next();
        String s4 = iterator.next();
        String s5 = iterator.next();
        printQueue();

        randomizedQueue.dequeue();

        printQueue();

        randomizedQueue.dequeue();

        printQueue();

    }

    @Test
    public void sample() throws Exception {

    }

    private void printQueue() {
        System.out.println("###################");
        for (String s : randomizedQueue) {
            System.out.println(s);
        }
        System.out.println("###################");
    }

}