import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by michal on 11.12.16.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    public Deque() {
    }

    public static void main(String[] args) {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node();
        node.item = item;
        if (size > 0) {
            first.previous = node;
        }
        node.next = first;
        node.previous = null;
        first = node;
        if (size == 0) {
            last = node;
        }
        size++;
    }

    public void addLast(Item item) {
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
        }
        size++;
    }

    public Item removeFirst() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        Node node = first;
        if (size > 1) {
            first = node.next;
            first.previous = null;
        } else {
            first = null;
            last = null;
        }
        size--;
        return node.item;
    }

    public Item removeLast() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        Node node = last;
        if (size > 1) {
            last = node.previous;
            last.next = null;
        } else {
            last = null;
            first = null;
        }
        size--;
        return node.item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class Node {
        private Item item;
        private Node next;
        private Node previous;

        Node() {
            this.next = null;
            this.previous = null;
        }
    }

    private class DequeIterator implements Iterator<Item> {

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
}