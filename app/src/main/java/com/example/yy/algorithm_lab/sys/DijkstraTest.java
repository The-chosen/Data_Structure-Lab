package com.example.yy.algorithm_lab.sys;

import com.example.yy.algorithm_lab.db.Site;
import com.example.yy.algorithm_lab.util.DbControl;

public class DijkstraTest {
    public static void main(String[] args) {
        SiteGraph siteGraph = (new TestGraph()).getGraph();
//        SiteGraph siteGraph = DbControl.SiteDistr();
        Site from = siteGraph.getDiAdj().get(0).get(0).from();
        Site to = siteGraph.getDiAdj().get(3).get(0).from();


//        Site from = siteGraph.getDiAdj().get(0).get(0).from();
//        Site to = siteGraph.getDiAdj().get(4).get(0).from();
        Dijkstra dijkstra = new Dijkstra(siteGraph, from, to);
        if (dijkstra.hasPath()) {
            System.out.println(dijkstra.getLine());
            System.out.println(dijkstra.distTo());
        }
    }
}
