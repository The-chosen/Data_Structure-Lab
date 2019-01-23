package com.example.yy.algorithm_lab.Algorithm.collections;

/**
 * @author YangYue
 * @description 异常类，如果没有该元素就会抛出异常
 * @date 2019-2-22 10:00
 */

public class NoSuchElementException extends RuntimeException {
    private static final long serialVersionUID = 6769829250639411880L;

    public NoSuchElementException() {
        super();
    }

    public NoSuchElementException(String s) {
        super(s);
    }
}
