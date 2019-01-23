package com.example.yy.algorithm_lab.Algorithm.sys;

import com.example.yy.algorithm_lab.Algorithm.collections.LinkedList;
import com.example.yy.algorithm_lab.Algorithm.collections.Merge;
import com.example.yy.algorithm_lab.Android.db.DiEdge;
import com.example.yy.algorithm_lab.Android.db.Site;

/**
 * @author YangYue
 * @description 汉密尔顿算法的改进，可设定起点终点，而且可以走回头路
 * @date 2019-2-22 10:00
 */

public class Hamilton {
    private String line = "";
    private DiEdge[][] Matrix;
    private boolean isCycle;
    private LinkedList<LinkedList<Object>> ways;
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
//                将邻接矩阵转换为可达矩阵
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
        boolean hasRow;
        if (isCycle) {
            hasRow = dfsCycle(matrix, path, used, 1, begin);
            addLine(end, hasRow);
        }
        else {
            hasRow = dfs(matrix, path, used, 1, begin, end);
            addLine(end, hasRow);
        }
    }

//    深度搜索算法
    public boolean dfs(int[][] matrix, int[] path, boolean[] used, int step, int begin, int end) {
        if(step == matrix.length) {
//            如果相等就意味着找到了正确的路径
            if(path[step - 1] == end) {
                for(int i = 0;i < path.length - 1;i++)
                    line += Matrix[path[i]][path[i]].from().getName() + "—>";
                line += Matrix[path[path.length - 1]][path[path.length - 1]].from().getName();
                return true;
            }
//            如果最后结点不是终点，就先将该路径进行存储
            else {
                for(int i = 0;i < path.length - 1;i++)
                    line += Matrix[path[i]][path[i]].from().getName() + "—>";
                LinkedList<Object> s = new LinkedList<>();
                s.add(line);
                s.add(path[path.length - 1]);
                ways.add(s);
                line = "";
            }
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

//    处理起点和终点是同一个的情况，和dfs差不多
    public boolean dfsCycle(int[][] matrix, int[] path, boolean[] used, int step, int begin) {
        if(step == matrix.length) {
            if(matrix[path[step - 1]][begin] == 1) {
                for(int i = 0;i < path.length;i++)
                    line += Matrix[path[i]][path[i]].from().getName() + "—>";
                line += Matrix[path[begin]][path[begin]].from().getName();
                return true;
            }
            else {
                for(int i = 0;i < path.length - 1;i++)
                    line += Matrix[path[i]][path[i]].from().getName() + "—>";
                LinkedList<Object> s = new LinkedList<>();
                s.add(line);
                s.add(path[path.length - 1]);
                ways.add(s);
                line = "";
            }

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

//    获取路径
    public String getLine() {
        return line;
    }

//判断是否需要走回头路
    public void addLine(int end, boolean hasRow) {
//        如果有路，就不需要走回头路，就没有之后的操作
        if (hasRow) {
            return;
        }
//        需要走回头路
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
//            对所有被存储的路的尾节点到终点的距离进行排序
            Merge.sort(distLs);
            double minDist = distLs[0];
            String minPath = "";
            for (int i = 0; i < ways.size(); i++) {
                if (Math.abs(minDist - (Double)ways.get(i).get(3)) < 0.1) {
//                  将存储的路和Dijkstra生成的路合并
                    minPath = (String)ways.get(i).get(0) + (String)ways.get(i).get(2);
                    break;
                }
            }

            line += minPath;
        }
    }
}
