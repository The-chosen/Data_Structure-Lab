package com.example.yy.algorithm_lab.collections;

public class queueTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        System.out.println("isEmpty:" + queue.isEmpty());
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }
        for (int i: queue
             ) {
            System.out.println(i);
        }

        System.out.println("size:" + queue.size());

        int dequeuedOne = queue.dequeue();
        System.out.println("dequeued one:" + dequeuedOne);

        System.out.println("size:" + queue.size());

        System.out.println("isEmpty:" + queue.isEmpty());
    }
}
