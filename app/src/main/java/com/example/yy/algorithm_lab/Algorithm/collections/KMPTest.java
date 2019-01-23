package com.example.yy.algorithm_lab.Algorithm.collections;

/**
 * @author YangYue
 * @description:
 * @date :2019/1/22 18:57
 */
public class KMPTest {
    public static void main(String[] args) {
        KMP kmp = new KMP("lo");
        System.out.println(kmp.search("hello world"));
        System.out.println(kmp.search("good bye"));
    }
}
