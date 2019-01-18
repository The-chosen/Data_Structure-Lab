package com.example.yy.algorithm_lab.collections;

public class linkedListTest {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        System.out.println("isEmpty:" + linkedList.isEmpty());
        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }

        for (int i: linkedList
                ) {
            System.out.println(i);
        }

        System.out.println("size:" + linkedList.size());

        linkedList.remove(4);

        System.out.println("size(removed one):" + linkedList.size());

        System.out.println("isEmpty:" + linkedList.isEmpty());
    }
}
