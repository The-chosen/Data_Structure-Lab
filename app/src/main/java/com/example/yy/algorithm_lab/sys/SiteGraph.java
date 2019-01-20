package com.example.yy.algorithm_lab.sys;

import com.example.yy.algorithm_lab.collections.LinkedList;
import com.example.yy.algorithm_lab.db.DiEdge;
import com.example.yy.algorithm_lab.db.Site;

import java.io.Serializable;

public class SiteGraph implements Serializable {
    private int V = 0;
    private int E;//有向图的边数目
    private LinkedList<LinkedList<DiEdge>> DiAdj;//有向图的边集合

    public SiteGraph() {
        DiAdj =  new LinkedList<>();
    }

    public SiteGraph(int V) {
        this.V = V;
        DiAdj =  new LinkedList<>();
        for (int i = 0; i < V; i++) {
            DiAdj.add(new LinkedList<DiEdge>());
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
        DiAdj.get(v.getMyId()).add(e);
//        因为是无向图，故一次增加两条边
        DiEdge inverseE = new DiEdge(w, v, e.weight());
        DiAdj.get(w.getMyId()).add(inverseE);
        E++;
    }

//    待删除
    public void addNewSite() {
        DiAdj.add(new LinkedList<DiEdge>());
        V++;
    }

//    删除一个景点，不仅删除该景点，还删除该景点周围的所有路
    public void removeDiSite(Site s) {
        int removedIndex = s.getMyId();
        DiAdj.set(s.getMyId(), null);

        for (int i = 0; i < DiAdj.size(); i++) {
            if (i == removedIndex) {continue;}
            else {
                for (DiEdge e: DiAdj.get(i)
                     ) {
                    if (e.to().getName().equals(s.getName())) {
                        DiAdj.get(i).remove(e);
                        break;
                    }
                }
            }
        }
    }

//    删除一个边，由于是无向图，故一个方向被删除，另一个方向也同时被删除
    public void removeDiEdge(DiEdge e) {
        for (int i = 0; i < DiAdj.size(); i++) {
            for (DiEdge d: DiAdj.get(i)
                        ) {
                    if (d.to().getName().equals(e.to().getName())
                            || d.from().getName().equals(e.to().getName())) {
                        DiAdj.get(i).remove(d);
                        break;
                    }
            }
        }
    }

//    返回某个景点周围所有路径
    public Iterable<DiEdge> DiAdj(int v) {
        return DiAdj.get(v);
    }


//    返回该图的关于权重的邻接矩阵
    public DiEdge[][] getMatrix() {
        DiEdge[][] matrix = new DiEdge[V][V];
        DiEdge unAccess = new DiEdge(null, null, 32767);
        for (int i = 0; i < V; i++) {
            Site self = DiAdj.get(i).iterator().next().from();
            for (int j = 0; j < V; j++) {
                matrix[i][j] = unAccess;
            }
            for (DiEdge e:DiAdj.get(i)
                 ) {
                matrix[i][e.to().getMyId()] = e;
            }
            matrix[i][i] = new DiEdge(self, self, 0);
        }
        return matrix;
    }

    public LinkedList<LinkedList<DiEdge>> getDiAdj() {
        return DiAdj;
    }
}
