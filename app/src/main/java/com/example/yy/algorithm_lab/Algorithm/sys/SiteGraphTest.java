package com.example.yy.algorithm_lab.Algorithm.sys;

import com.example.yy.algorithm_lab.Android.db.DiEdge;
import com.example.yy.algorithm_lab.Algorithm.sys.SiteGraph;
import com.example.yy.algorithm_lab.Algorithm.sys.TestGraph;

/**
 * @author YangYue
 * @description 景点图的单元测试
 * @date 2019-2-22 10:00
 */

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
