package com.example.yy.algorithm_lab.Algorithm.collections;

/**
 * @author YangYue
 * @description 归并排序测试
 * @date 2019-2-22 10:00
 */

public class MergeTest {
    public static void main(String[] args) {
        Integer[] a = {43, 1, 2, 89, 10, 11, 4, 111};
        Merge merge = new Merge();
        merge.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

}

