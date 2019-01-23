package com.example.yy.algorithm_lab.Algorithm.sys;

import com.example.yy.algorithm_lab.Android.db.DiEdge;
import com.example.yy.algorithm_lab.Android.db.Site;

/**
 * @author YangYue
 * @description:
 * @date :2019/1/22 18:25
 */
public class FloydTest {
    public static void main(String[] args) {
        SiteGraph siteGraph = (new TestGraph()).getGraph();
        Site from = siteGraph.getDiAdj().get(0).get(0).from();
        Site to = siteGraph.getDiAdj().get(3).get(0).from();
        Floyd floyd = new Floyd(siteGraph, from, to);
        if (floyd.hasPath()) {
            for (DiEdge diedge: floyd.path()
                 ) {
                System.out.print(diedge.getFrom().getName() + "—>");
            }
            System.out.println(to.getName());
            System.out.println(floyd.dist());
        }
    }
}
