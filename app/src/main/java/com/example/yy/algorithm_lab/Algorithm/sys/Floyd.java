package com.example.yy.algorithm_lab.Algorithm.sys;

/**
 * @author YangYue
 * @description: Floyd算法实现最短路径
 * @date :2019/1/22 10:39
 */

import com.example.yy.algorithm_lab.Algorithm.collections.Stack;
import com.example.yy.algorithm_lab.Android.db.DiEdge;
import com.example.yy.algorithm_lab.Android.db.Site;

import java.io.Serializable;

public class Floyd implements Serializable {
    private boolean hasNegativeCycle;
    private double[][] distTo;
    private DiEdge[][] edgeTo;
    private int s;
    private int t;

    public Floyd(SiteGraph G, Site s, Site t) {
        this(G);
        this.s = s.getMyId();
        this.t = t.getMyId();
    }

    public Floyd(SiteGraph G) {
        int V = G.V();
        distTo = new double[V][V];
        edgeTo = new DiEdge[V][V];

        // 初始化距离
        for (int v = 0; v < V; v++) {
            for (int w = 0; w < V; w++) {
                distTo[v][w] = Double.POSITIVE_INFINITY;
            }
        }

        for (int v = 0; v < G.V(); v++) {
            for (DiEdge e : G.getDiAdj().get(v)) {
                distTo[e.from().getMyId()][e.to().getMyId()] = e.weight();
                edgeTo[e.from().getMyId()][e.to().getMyId()] = e;
            }
            // 防止自循环
            if (distTo[v][v] >= 0.0) {
                distTo[v][v] = 0.0;
                edgeTo[v][v] = null;
            }
        }

        // 更新
        for (int i = 0; i < V; i++) {
            for (int v = 0; v < V; v++) {
                if (edgeTo[v][i] == null) continue;
                for (int w = 0; w < V; w++) {
                    if (distTo[v][w] > distTo[v][i] + distTo[i][w]) {
                        distTo[v][w] = distTo[v][i] + distTo[i][w];
                        edgeTo[v][w] = edgeTo[i][w];
                    }
                }
                if (distTo[v][v] < 0.0) {
                    hasNegativeCycle = true;
                    return;
                }
            }
        }
    }

//    判断是否有环
    public boolean hasNegativeCycle() {
        return hasNegativeCycle;
    }

//判断是否有路
    public boolean hasPath() {
        validateVertex(s);
        validateVertex(t);
        return distTo[s][t] < Double.POSITIVE_INFINITY;
    }

//    返回距离
    public double dist() {
        validateVertex(s);
        validateVertex(t);
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        return distTo[s][t];
    }

//返回路径
    public Iterable<DiEdge> path() {
        validateVertex(s);
        validateVertex(t);
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        if (!hasPath()) return null;
        Stack<DiEdge> path = new Stack<DiEdge>();
        for (DiEdge e = edgeTo[s][t]; e != null; e = edgeTo[s][e.from().getMyId()]) {
            path.push(e);
        }
        return path;
    }

//  判断结点是否合法
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

}
