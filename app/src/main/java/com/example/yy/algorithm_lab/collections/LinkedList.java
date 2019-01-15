package com.example.yy.algorithm_lab.collections;

import java.util.Iterator;

public class LinkedList<Item> implements Iterable<Item>{
    static java.util.LinkedList a = new java.util.LinkedList();
    public int N;
    public Node<Item> first;
    public Node<Item> last;

    public int size() {return N;}

    public boolean isEmpty() {return first == null;}

    public void add(Item item) {
        Node<Item> newNode = new Node<>();
        newNode.item = item;
        if (isEmpty()) {
            first = newNode;
        }
        else {
            last.next = newNode;
            newNode.pre = last;
        }
        last = newNode;
        N++;
    }

    public void remove() {
        if (first.next == null) {
            first = null;
        }
        else {
            last.pre.next = null;
        }
        last = last.pre;
        N--;
    }

    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
