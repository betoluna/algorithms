package lists;

import java.util.Iterator;

/**
 * Created by beto on 7/12/17.
 */
public class MyLinkedList {
    private Node head, tail;
    private int size;

    //construct an empty list
    public MyLinkedList() {
        head = tail = null;
        size = 0;
    }

    //return the head of the list
    public Node getHead() {
        return this.head;
    }

    //append a node to the list
    public boolean add(int val) {
        Node newNode = new Node(val, null);

        //if list is empty
        if(size() == 0) {
            head = newNode;
            tail = newNode;
            size++;
        } else {//there is at least one node in the list
            tail.next = newNode;
            tail = newNode;
            size++;
        }

        return true;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public Iterator iterator() {
        return new MyIterator();
    }

    //getters & setters
    public int size() {
        return size;
    }

    public class Node {
        //only singly linked for now
        private Node next;
        int value;

        //constructor
        public Node(int myVal, Node nextPtr) {
            value = myVal;
            next = nextPtr;
        }

        //getters & setters
        public void setValue(int newVal) { value = newVal; }

        public void setNext(Node newNext) { next = newNext; }

        public int getValue() { return value; }

        public Node getNext() { return next; }
    }

    private class MyIterator implements Iterator {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public Integer next() {
            if(!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            int val = current.getValue();
            current = current.getNext();//current moves to next node;
            return val;
        }

        //this op is unsupported for the moment
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {

    }
}
