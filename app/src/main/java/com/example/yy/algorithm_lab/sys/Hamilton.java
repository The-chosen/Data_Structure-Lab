package com.example.yy.algorithm_lab.sys;

import com.example.yy.algorithm_lab.db.DiEdge;

public class Hamilton {
    private String line = "";
    private DiEdge[][] Matrix;

    public Hamilton(DiEdge[][] matrix) {
        Matrix = matrix;
        int[][] accessMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                accessMatrix[i][j] = (Math.abs(matrix[i][j].weight() - 32767) < 0.1) ? -1 : 1;
            }
        }
        getHamiltonCircuit(accessMatrix);
    }

    public void getHamiltonCircuit(int[][] matrix) {
        boolean[] used = new boolean[matrix.length];
        int[] path = new int[matrix.length];
        for(int i = 0;i < matrix.length;i++) {
            used[i] = false;
            path[i] = -1;
        }
        used[0] = true;
        path[0] = 0;
        dfs(matrix, path, used, 1);
    }

    public boolean dfs(int[][] matrix, int[] path, boolean[] used, int step) {
        if(step == matrix.length) {
            if(matrix[path[step - 1]][0] == 1) {
                for(int i = 0;i < path.length;i++)
                    line += Matrix[path[i]][path[i]].from().getName() + "—>";
                line += Matrix[path[0]][path[0]].from().getName();
                return true;
            }
            return false;
        } else {
            for(int i = 0;i < matrix.length;i++) {
                if(!used[i] && matrix[path[step - 1]][i] == 1) {
                    used[i] = true;
                    path[step] = i;
                    if(dfs(matrix, path, used, step + 1))
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
}
