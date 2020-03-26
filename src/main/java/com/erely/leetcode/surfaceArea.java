package com.erely.leetcode;

public class surfaceArea {

    public static int f1(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; i++) {

                if (i - 1 < 0) { //说明首行查找右和上
                    if (j + 1 < grid.length) {
                        if (grid[i][j + 1] != 0) {
                            sum += 5;
                        } else {
                            sum += 6;
                        }
                    } else {
                        sum += 6;
                    }
                }
            }
        }

        return 0;
    }
}
