package com.example.yy.algorithm_lab.sys;

import com.example.yy.algorithm_lab.collections.IndexMinPQ;
import com.example.yy.algorithm_lab.collections.Stack;

public class Dijkstra {
    private DiEdge[] DiEdgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;
    private String line;
    private Site s;

    public Dijkstra(SiteGraph G, Site s) {
        this.s = s;
        DiEdgeTo = new DiEdge[G.V()];
        distTo = new double[G.V()];
        for (int i = 0; i < G.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s.getId()] = 0.0;
        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s.getId(), 0.0);
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }

    public void relax(SiteGraph G, int v) {
        for (DiEdge e: G.DiAdj(v)
                ) {
            int w = e.to().getId();
            if (e.weight() + distTo[v] < distTo[w]) {
                distTo[w] = e.weight() + distTo[v];
                DiEdgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                }
                else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public boolean hasPath(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Stack<DiEdge> pathTo(Site v) {
        if (!hasPath(v.getId())) return null;
        Stack<DiEdge> s = new Stack<>();
        for (DiEdge i = DiEdgeTo[v.getId()]; i != null ; i = DiEdgeTo[i.from().getId()]) {
            s.push(i);
        }
        return s;
    }

    public void setLine(Site v) {
        line += s.getName();
        for (int i = 0; i < pathTo(v).size(); i++) {
            line += "—>" + pathTo(v).pop().to().getName();
        }
        line += v.getName();
    }

    public String getLine() {
        return line;
    }

    public double distTo(int v) {
        return distTo[v];
    }
}
