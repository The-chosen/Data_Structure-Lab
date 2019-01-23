package com.example.yy.algorithm_lab.Algorithm.collections;

import java.util.Iterator;

/**
 * @author YangYue
 * @description 栈的类
 * @date 2019-2-22 10:00
 */

public class Stack<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int N;

    public int size() {return N;}

    public boolean isEmpty() {return first == null;}

    public void push(Item item) {
        Node<Item> oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public Item peek() {
        return first.item;
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
