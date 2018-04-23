package com.company;

import java.util.*;

class Dot {
    int i;
    int j;
    int height;
    int time;
    public Dot(int i, int j, int height, int time) {
        this.i = i;
        this.j = j;
        this.height = height;
        this.time = time;
    }
}
public class Main {

    public static void main(String[] args) {

        int[][] matrix = {{10,14,19}, {14,15,17}, {18,6,15}};
        int[][] matrix2 = {{10,13,12}, {16,18,16}, {17,16,14}};
        String res = top(matrix);
        System.out.println(res);
        System.out.println(top(matrix2));

    }

    private static String top(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        Set<Dot> set = new HashSet<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (isTop(matrix, i, j)) {
                    int t = time(matrix, i, j);
                    set.add(new Dot(i, j, matrix[i][j], t));
                }
            }
        }

        if (set.isEmpty()) return null;
        
        PriorityQueue<Dot> pq = new PriorityQueue<>(new Comparator<Dot>() {
            @Override
            public int compare(Dot o1, Dot o2) {
                if (o1.time != o2.time) return o1.time - o2.time;
                if (o1.height != o2.height) return o2.height - o1.height;
                if (o1.j != o2.j) return o2.j - o1.j;
                return o2.i - o1.i;
            }
        });

        for (Dot d : set) {
            pq.offer(d);
        }
        Dot dot = pq.poll();

        return "(" + dot.j + "," + dot.i + "):" + dot.time;
    }

    private static boolean isTop(int[][] matrix, int i, int j) {
        if (i > 0 && matrix[i - 1][j] >= matrix[i][j]) return false;
        if (j > 0 && matrix[i][j - 1] >= matrix[i][j]) return false;
        if (i < matrix.length - 1 && matrix[i + 1][j] >= matrix[i][j]) return false;
        if (j < matrix[0].length - 1 && matrix[i][j + 1] >= matrix[i][j]) return false;
        if (i > 0 && j > 0 && matrix[i - 1][j - 1] >= matrix[i][j]) return false;
        if (i > 0 && j < matrix[0].length - 1 && matrix[i - 1][j + 1] >= matrix[i][j]) return false;
        if (i < matrix.length - 1 && j < matrix[0].length - 1 && matrix[i + 1][j + 1] >= matrix[i][j]) return false;
        if (i < matrix.length - 1 && j > 0 && matrix[i + 1][j - 1] >= matrix[i][j]) return false;
        return true;
    }

    private static int time(int[][] matrix, int i, int j) {
        int res = 0;
        for (int m = 0; m <= i; m++) res += matrix[m][0];
        for (int n = 1; n <= j; n++) res += matrix[i][n];
        return res;
    }

}
