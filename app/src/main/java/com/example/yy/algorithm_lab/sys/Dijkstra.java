package com.example.yy.algorithm_lab.sys;

import com.example.yy.algorithm_lab.collections.IndexMinPQ;
import com.example.yy.algorithm_lab.collections.Stack;
import com.example.yy.algorithm_lab.db.DiEdge;
import com.example.yy.algorithm_lab.db.Site;

import java.io.Serializable;

public class Dijkstra implements Serializable {
    private DiEdge[] DiEdgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;
    private String line = "";
    private Site s;
    private Site v;

    public Dijkstra(SiteGraph G, Site s, Site v) {
        this.v = v;
        this.s = s;
        DiEdgeTo = new DiEdge[G.V()];
        distTo = new double[G.V()];
        for (int i = 0; i < G.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s.getMyId()] = 0.0;
        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s.getMyId(), 0.0);
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }

    public void relax(SiteGraph G, int v) {
        for (DiEdge e: G.DiAdj(v)
                ) {
            int w = e.to().getMyId();
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

    public boolean hasPath() {
        return distTo[v.getMyId()] < Double.POSITIVE_INFINITY;
    }

    public Stack<DiEdge> pathTo() {
        if (!hasPath()) return null;
        Stack<DiEdge> s = new Stack<>();
        for (DiEdge i = DiEdgeTo[v.getMyId()]; i != null ; i = DiEdgeTo[i.from().getMyId()]) {
            s.push(i);
        }
        return s;
    }

    public void setLine() {
        line += s.getName();
        Stack<DiEdge> path = pathTo();
        for (int i = 0; i < path.size(); i++) {
            line += "—>" + path.pop().to().getName();
        }
        line += "—>" + v.getName();
    }

    public String getLine() {
        setLine();
        return line;
    }

    public double distTo() {
        return distTo[v.getMyId()];
    }
}
