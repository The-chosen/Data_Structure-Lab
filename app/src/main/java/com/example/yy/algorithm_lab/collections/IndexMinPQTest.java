package com.example.yy.algorithm_lab.collections;

public class IndexMinPQTest {
    public static void main(String[] args) {
        IndexMinPQ<Integer> indexMinPQ = new IndexMinPQ<>(10);
        System.out.println(indexMinPQ.isEmpty());
        indexMinPQ.insert(0, 12);
        indexMinPQ.insert(3, 1);
        indexMinPQ.insert(1, 32);
        indexMinPQ.insert(6, 19);
        indexMinPQ.insert(2, 2);
        indexMinPQ.insert(8, 4);
        System.out.println(indexMinPQ.size());
        System.out.println(indexMinPQ.delMin());
        System.out.println(indexMinPQ.size());
        System.out.println(indexMinPQ.delMin());
        indexMinPQ.changeKey(0, 1);
        System.out.println(indexMinPQ.delMin());
    }

}
