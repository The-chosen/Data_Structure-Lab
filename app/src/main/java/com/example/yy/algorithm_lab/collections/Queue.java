package com.example.yy.algorithm_lab.collections;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>{
    private Node<Item> first;
    private Node<Item> last;
    private int N;

    public boolean isEmpty(){return first == null;}

    public int size() {return N;}

    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next ;
        if (isEmpty()) last = null;
        N--;
        return item;
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
