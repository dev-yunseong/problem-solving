package programmers.OptimalMatrixMultiplication;

import java.util.*;

class Solution {

    int[][] dp;
    int[][] matrixSizes;

    public int solution(int[][] matrix_sizes) {
        this.matrixSizes = matrix_sizes;
        int n = matrix_sizes.length;
        dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        for (int i = 0; i < n - 1; i++) {
            dp[i][i+1] = matrix_sizes[i][0] * matrix_sizes[i][1] * matrix_sizes[i+1][1];
        }

        return getValue(0, n-1);
    }

    public int getValue(int i, int j) {
        // System.out.printf("[%d, %d]\n", i, j);

        if (dp[i][j] != -1) return dp[i][j];

        int result = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int temp = getValue(i, k) + getValue(k+1, j);
            temp += matrixSizes[i][0] * matrixSizes[k][1] * matrixSizes[j][1];
            result = Math.min(temp, result);
        }
        dp[i][j] = result;

        return result;
    }
}