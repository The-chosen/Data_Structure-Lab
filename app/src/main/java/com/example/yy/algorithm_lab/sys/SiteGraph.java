package com.example.yy.algorithm_lab.sys;

import com.example.yy.algorithm_lab.collections.Bag;

public class SiteGraph {
    private final int V;
    private int E;//有向图的边数目
    private Bag<DiEdge>[] DiAdj;//有向图的边集合

    public SiteGraph(int V) {
        this.V = V;
        DiAdj =  (Bag<DiEdge>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            DiAdj[i] = new Bag<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addDiEdge(DiEdge e) {
        Site v = e.from();
        Site w = e.to();
        DiAdj[v.getId()].add(e);
//        因为是无向图，故一次增加两条边
        DiEdge inverseE = new DiEdge(w, v, e.weight());
        DiAdj[w.getId()].add(inverseE);
        E++;
    }

    public void removeDiSite(Site s) {
        
    }

//    返回某个景点周围所有路径
    public Iterable<DiEdge> DiAdj(int v) {
        return DiAdj[v];
    }


//    返回该图的关于权重的邻接矩阵
    public DiEdge[][] getMatrix() {
        DiEdge[][] matrix = new DiEdge[V][V];
        DiEdge unAccess = new DiEdge(null, null, 32767);
        for (int i = 0; i < V; i++) {
            Site self = DiAdj[i].iterator().next().from();
            for (int j = 0; j < V; j++) {
                matrix[i][j] = unAccess;
            }
            for (DiEdge e:DiAdj[i]
                 ) {
                matrix[i][e.to().getId()] = e;
            }
            matrix[i][i] = new DiEdge(self, self, 0);
        }
        return matrix;
    }
}
