package com.example.yy.algorithm_lab.sys;

import com.example.yy.algorithm_lab.collections.LinkedList;
import com.example.yy.algorithm_lab.collections.Merge;
import com.example.yy.algorithm_lab.db.DiEdge;
import com.example.yy.algorithm_lab.db.Site;

public class Hamilton {
    private String line = "";
    private DiEdge[][] Matrix;
    private boolean isCycle;
    private LinkedList<LinkedList<Object>> ways;
    private boolean hasRow;
    private SiteGraph siteGraph;

    public Hamilton(SiteGraph siteGraph, int begin, int end, boolean isCycle) {
        this.siteGraph = siteGraph;
        DiEdge[][] matrix = siteGraph.getMatrix();
        Matrix = matrix;
        ways = new LinkedList<>();
        this.isCycle = isCycle;
        int[][] accessMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                accessMatrix[i][j] = (Math.abs(matrix[i][j].weight() - 32767) < 0.1) ? -1 : 1;
            }
        }
        getHamiltonCircuit(accessMatrix, begin, end);
    }

    public void getHamiltonCircuit(int[][] matrix, int begin, int end) {
        boolean[] used = new boolean[matrix.length];
        int[] path = new int[matrix.length];
        for(int i = 0;i < matrix.length;i++) {
            used[i] = false;
            path[i] = -1;
        }
        used[0] = true;
        path[0] = begin;
        if (isCycle) {
            hasRow = dfsCycle(matrix, path, used, 1, begin);
            addLine(end);
        }
        else {
            hasRow = dfs(matrix, path, used, 1, begin, end);
            addLine(end);
        }
    }

    public boolean dfs(int[][] matrix, int[] path, boolean[] used, int step, int begin, int end) {
        if(step == matrix.length) {
            if(path[step - 1] == end) {
                for(int i = 0;i < path.length - 1;i++)
                    line += Matrix[path[i]][path[i]].from().getName() + "—>";
                line += Matrix[path[path.length - 1]][path[path.length - 1]].from().getName();
                return true;
            }
            for(int i = 0;i < path.length - 1;i++)
                line += Matrix[path[i]][path[i]].from().getName() + "—>";
            LinkedList<Object> s = new LinkedList<>();
            s.add(line);
            s.add(path[path.length - 1]);
            ways.add(s);
            line = "";

            return false;
        } else {
            for(int i = 0;i < matrix.length;i++) {
                if(!used[i] && matrix[path[step - 1]][i] == 1) {
                    used[i] = true;
                    path[step] = i;
                    if(dfs(matrix, path, used, step + 1, begin, end))
                        return true;
                    else {
//                        回溯处理
                        used[i] = false;
                        path[step] = -1;
                    }
                }
            }
        }
        return false;
    }

    public boolean dfsCycle(int[][] matrix, int[] path, boolean[] used, int step, int begin) {
        if(step == matrix.length) {
            if(matrix[path[step - 1]][begin] == 1) {
                for(int i = 0;i < path.length;i++)
                    line += Matrix[path[i]][path[i]].from().getName() + "—>";
                line += Matrix[path[begin]][path[begin]].from().getName();
                return true;
            }
            for(int i = 0;i < path.length - 1;i++)
                line += Matrix[path[i]][path[i]].from().getName() + "—>";
            LinkedList<Object> s = new LinkedList<>();
            s.add(line);
            s.add(path[path.length - 1]);
            ways.add(s);
            line = "";

            return false;
        } else {
            for(int i = 0;i < matrix.length;i++) {
                if(!used[i] && matrix[path[step - 1]][i] == 1) {
                    used[i] = true;
                    path[step] = i;
                    if(dfsCycle(matrix, path, used, step + 1, begin))
                        return true;
                    else {
//                        回溯处理
                        used[i] = false;
                        path[step] = -1;
                    }
                }
            }
        }
        return false;
    }

    public String getLine() {
        return line;
    }

    public boolean isHasRow() {
        return hasRow;
    }

    public void addLine(int end) {
        if (isHasRow()) {
            return;
        }
        else {
            Integer[] ls = new Integer[ways.size()];
            for (int i = 0; i < ways.size(); i++) {
                ls[i] = (Integer)(ways.get(i).get(1));
            }

            Double[] distLs = new Double[ways.size()];
            for (int i = 0; i < ways.size(); i++) {
                Site from = Matrix[ls[i]][ls[i]].from();
                Site to = Matrix[end][end].from();
                Dijkstra dijkstra = new Dijkstra(siteGraph, from, to);
                distLs[i] = dijkstra.distTo();
                LinkedList<Object> temp = ways.get(i);
                temp.add(dijkstra.getLine());
                temp.add(dijkstra.distTo());
                ways.set(i, temp);
            }
            Merge.sort(distLs);
            double minDist = distLs[0];
            String minPath = "";
            for (int i = 0; i < ways.size(); i++) {
                if (Math.abs(minDist - (Double)ways.get(i).get(3)) < 0.1) {
                    minPath = (String)ways.get(i).get(0) + (String)ways.get(i).get(2);
                    break;
                }
            }
            line += minPath;
        }
    }
}
