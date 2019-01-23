package com.example.yy.algorithm_lab.Algorithm.collections;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @author YangYue
 * @description 链表实现
 * @date 2019-2-22 10:00
 */

public class LinkedList<Item> implements Iterable<Item>, Serializable {
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

    public void removeFirst() {
        if (first.next == null) {
            first = null;
        }
        else {
            last.next.pre = null;
        }
        first = first.next;
        N--;
    }

    public void removeLast() {
        if(first.next == null){
            first = null;
        }else {
            last.pre.next = null;
        }
        last = last.pre;
        N--;
    }

    public void remove(Item item) {
        for (Node<Item> i = first; i.equals(null); i = i.next) {
            if (i.item.equals(item)) {
                if (i.equals(first)) {
                    removeFirst();
                }
                if (i.next.equals(null)) {
                    removeLast();
                }
                i.pre.next = i.next;
                i.next.pre = i.pre;
                i = null;
                N--;
                break;
            }
        }
    }

    public Item get(int i) {
        if (i < 0 || i > N - 1) {
            throw new ArrayIndexOutOfBoundsException("Invalide position!");
        } else {
            int cnt = 0;
            Node t = first;
            while (!t.equals(null)) {
                if (cnt == i) {
                    return (Item)t.item;
                }
                t = t.next;
                cnt ++;
            }
            return null;
        }
    }

    public void set(int i, Item item) {
        if (i < 0 || i > N - 1) {
            throw new ArrayIndexOutOfBoundsException("Invalide position!");
        } else {
            int cnt = 0;
            Node t = first;
            while (!t.equals(null)) {
                if (cnt == i) {
                    t.item = item;
                    return;
                }
                t = t.next;
                cnt ++;
            }
        }
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
