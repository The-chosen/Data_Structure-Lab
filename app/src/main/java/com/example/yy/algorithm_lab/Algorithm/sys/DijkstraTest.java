package com.example.yy.algorithm_lab.Algorithm.sys;

import com.example.yy.algorithm_lab.Android.db.Site;

/**
 * @author YangYue
 * @description 最短路的算法单元测试
 * @date 2019-2-22 10:00
 */

public class DijkstraTest {
    public static void main(String[] args) {
        SiteGraph siteGraph = (new TestGraph()).getGraph();
        Site from = siteGraph.getDiAdj().get(0).get(0).from();
        Site to = siteGraph.getDiAdj().get(3).get(0).from();
        Dijkstra dijkstra = new Dijkstra(siteGraph, from, to);
        if (dijkstra.hasPath()) {
            System.out.println(dijkstra.getLine());
            System.out.println(dijkstra.distTo());
        }
    }
}
