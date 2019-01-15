package com.example.yy.algorithm_lab.sys;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("sites.txt");
        int V;
        try(Scanner input = new Scanner(file)) {
            V = Integer.parseInt(input.next());
            SiteGraph siteGraph = new SiteGraph(V);
            int cnt = 0;
            while (input.hasNext()) {
                String[] stringLs = input.next().split(",");
                String site1 = stringLs[0];
                String site2 = stringLs[1];
                int length = Integer.parseInt(stringLs[2]);
                boolean flag1 = false;
                boolean flag2 = false;
                for (Edge e: siteGraph.edges()
                     ) {
                    if (e.either().getName() == site1 || e.other(e.either().getId()).getName() == site1) {
                        flag1 = true;
                    }
                    if (e.either().getName() == site2 || e.other(e.either().getId()).getName() == site2) {
                        flag2 = true;
                    }
                }
                Site Site1;
                Site Site2;
                if (flag1 != true) {
                    Site1 = new Site(cnt, site1);
                    cnt++;
                }
                if (flag2 != true) {
                    Site2 = new Site(cnt, site2);
                    cnt++;
                }

            }
        } catch (Exception e) {
            System.out.println(">>>>>wrong!");
        }
    }
}
