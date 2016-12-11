import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by michal on 11.12.16.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
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
        if (item == null) throw new NullPointerException();
        Node node = new Node();
        node.item = item;
        node.next = first;
        node.previous = null;
        first = node;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node node = new Node();
        node.item = item;
        node.previous = last;
        node.next = null;
        last = node;
        size++;
    }

    public Item removeFirst() {
        if (size() == 0) throw new NoSuchElementException();
        Node node = first;
        first = node.next;
        return node.item;
    }

    public Item removeLast() {
        if (size() == 0) throw new NoSuchElementException();
        Node node = last;
        last = node.previous;
        return node.item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class Node {
        Item item;
        Node next;
        Node previous;
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
            Item item = current.item;
            current = current.next;
            return item;
        }

    }
}