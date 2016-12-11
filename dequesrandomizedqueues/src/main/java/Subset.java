import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by michal on 11.12.16.
 */
public class Subset {

    public static void main(String[] args) {

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            randomizedQueue.enqueue(s);
        }

        StdOut.println();
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}
