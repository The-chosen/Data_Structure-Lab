package com.example.yy.algorithm_lab.Algorithm.sys;

/**
 * @author YangYue
 * @description:
 * @date :2019/1/22 18:39
 */
public class HamiltonTest {
    public static void main(String[] args) {
        SiteGraph siteGraph = (new TestGraph()).getGraph();
        Hamilton hamilton = new Hamilton(siteGraph, 0, 0, true);
        System.out.println(hamilton.getLine());
    }
}
