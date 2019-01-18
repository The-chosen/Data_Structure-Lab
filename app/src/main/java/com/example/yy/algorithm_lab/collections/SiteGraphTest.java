package com.example.yy.algorithm_lab.collections;

import com.example.yy.algorithm_lab.sys.DiEdge;
import com.example.yy.algorithm_lab.sys.SiteGraph;
import com.example.yy.algorithm_lab.sys.TestGraph;

public class SiteGraphTest {
    public static void main(String[] args) {
        SiteGraph siteGraph = (new TestGraph()).getGraph();
        DiEdge[][] m = siteGraph.getMatrix();
        for (int i = 0; i < siteGraph.V(); i++) {
            for (int j = 0; j < siteGraph.V(); j++) {
                System.out.print(m[i][j].weight());
                System.out.print("  ");
            }
            System.out.println();
        }
    }
}
