package com.example.yy.algorithm_lab.sys;

public class DijkstraTest {
    public static void main(String[] args) {
        SiteGraph siteGraph = (new TestGraph()).getGraph();
        Site from = siteGraph.getDiAdj().get(0).get(0).from();
        Site to = siteGraph.getDiAdj().get(7).get(0).from();
        Dijkstra dijkstra = new Dijkstra(siteGraph, from);
        if (dijkstra.hasPath(to)) {
            dijkstra.setLine(to);
            System.out.println(dijkstra.getLine());
        }
    }
}
