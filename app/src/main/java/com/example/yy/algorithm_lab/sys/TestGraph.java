package com.example.yy.algorithm_lab.sys;

public class TestGraph {
    private  SiteGraph graph;

    public SiteGraph getGraph() {
        SiteGraph siteGraph = new SiteGraph();
        Site beimen = new Site(siteGraph.V(), "北门");
        siteGraph.addNewSite();
        Site shizishan = new Site(siteGraph.V(), "狮子山");
        siteGraph.addNewSite();
        Site xianyunshi = new Site(siteGraph.V(), "仙云石");
        siteGraph.addNewSite();
        Site yixiantian = new Site(siteGraph.V(), "一线天");
        siteGraph.addNewSite();
        Site feiliupu = new Site(siteGraph.V(), "飞流瀑");
        siteGraph.addNewSite();
        Site xianwuhu = new Site(siteGraph.V(), "仙武湖");
        siteGraph.addNewSite();
        Site jiuquqiao = new Site(siteGraph.V(), "九曲桥");
        siteGraph.addNewSite();
        Site guanyuntai = new Site(siteGraph.V(), "观云台");
        siteGraph.addNewSite();

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
