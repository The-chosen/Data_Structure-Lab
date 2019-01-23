package com.example.yy.algorithm_lab.Algorithm.sys;

import com.example.yy.algorithm_lab.Android.db.DiEdge;
import com.example.yy.algorithm_lab.Android.db.Site;

/**
 * @author YangYue
 * @description 测试图的类
 * @date 2019-2-22 10:00
 */

public class TestGraph {
    private  SiteGraph graph;

    public SiteGraph getGraph() {

        Site beimen = new Site(0, "北门");

        Site shizishan = new Site(1, "狮子山");

        Site xianyunshi = new Site(2, "仙云石");

        Site yixiantian = new Site(3, "一线天");

        Site feiliupu = new Site(4, "飞流瀑");

        Site xianwuhu = new Site(5, "仙武湖");

        Site jiuquqiao = new Site(6, "九曲桥");

        Site guanyuntai = new Site(7, "观云台");

        SiteGraph siteGraph = new SiteGraph(8);

        DiEdge diEdge1 = new DiEdge(beimen, shizishan, 9);
        DiEdge diEdge2 = new DiEdge(beimen, xianyunshi, 8);
        DiEdge diEdge3 = new DiEdge(shizishan, yixiantian, 7);
        DiEdge diEdge4 = new DiEdge(shizishan, feiliupu, 6);
        DiEdge diEdge5 = new DiEdge(xianyunshi, xianwuhu, 4);
        DiEdge diEdge6 = new DiEdge(xianyunshi, jiuquqiao, 5);
        DiEdge diEdge7 = new DiEdge(yixiantian, guanyuntai, 11);
        DiEdge diEdge8 = new DiEdge(feiliupu, guanyuntai, 3);
        DiEdge diEdge9 = new DiEdge(xianwuhu, jiuquqiao, 7);
        siteGraph.addDiEdge(diEdge1);
        siteGraph.addDiEdge(diEdge2);
        siteGraph.addDiEdge(diEdge3);
        siteGraph.addDiEdge(diEdge4);
        siteGraph.addDiEdge(diEdge5);
        siteGraph.addDiEdge(diEdge6);
        siteGraph.addDiEdge(diEdge7);
        siteGraph.addDiEdge(diEdge8);
        siteGraph.addDiEdge(diEdge9);
        return siteGraph;
    }
}
