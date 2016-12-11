import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by michal on 11.12.16.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private Node first;
    private Node last;

    public RandomizedQueue() {
    }

    public static void main(String[] args) {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node();
        node.item = item;
        if (size > 0) {
            last.next = node;
        }

        node.previous = last;
        node.next = null;

        last = node;
        if (size == 0) {
            first = node;
            last = node;
        }
        size++;
    }

    public Item dequeue()                    // remove and return a random item
    {
        if (size() == 0)
            throw new NoSuchElementException();
        Node node = first;
        int randomIndex = StdRandom.uniform(0, size());
        for (int i = 0; i < randomIndex; i++) {
            node = node.next;
        }

        if (node.previous != null) {
            node.previous.next = node.next;
        } else {
            first = node.next;
        }
        if (node.next != null) {
            node.next.previous = node.previous;
        } else {
            last = node.previous;
        }
        if (size == 1) {
            first = null;
            last = null;
        }
        size--;
        return node.item;
    }

    public Item sample()                     // return (but do not remove) a random item
    {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        Item result = first.item;
        int randomIndex = 1;
        if (size() > 1) {
            randomIndex = StdRandom.uniform(1, size());
        }
        Iterator<Item> iterator = this.iterator();
        for (int i = 1; i < randomIndex && iterator.hasNext(); i++) {
            result = iterator.next();
        }
        return result;
    }

    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

}
