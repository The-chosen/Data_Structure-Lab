package com.example.yy.algorithm_lab.Algorithm.collections;

/**
 * @author YangYue
 * @description 链表测试
 * @date 2019-2-22 10:00
 */

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
