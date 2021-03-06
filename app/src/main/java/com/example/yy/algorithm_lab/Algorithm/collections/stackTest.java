package com.example.yy.algorithm_lab.Algorithm.collections;

/**
 * @author YangYue
 * @description 栈的单元测试
 * @date 2019-2-22 10:00
 */

public class stackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        System.out.println("isEmpty:" + stack.isEmpty());
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }

        for (int i: stack
                ) {
            System.out.println(i);
        }

        System.out.println("size:" + stack.size());

        int popedOne = stack.pop();
        System.out.println("poped one:" + popedOne);

        System.out.println("peek one:" + stack.peek());

        System.out.println("size:" + stack.size());

        System.out.println("isEmpty:" + stack.isEmpty());
    }
}
