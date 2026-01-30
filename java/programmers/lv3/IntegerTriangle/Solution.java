package programmers.lv3.IntegerTriangle;

import java.util.*;

class Solution {

    public int solution(int[][] triangle) {
        int[][] dp = new int[triangle.length][];
        int i = 0;
        for (var t : triangle) {
            dp[i] = new int[t.length];
            i++;
        }

        dp[0][0] = triangle[0][0];
        for (i = 1; i < triangle.length; i++) {
            int[] line = triangle[i];
            for (int j = 0; j < line.length; j++) {
                int a = 0;
                int b = 0;
                if (j-1 >= 0) {
                    a = dp[i-1][j-1];
                }
                if (j < line.length-1) {
                    b = dp[i-1][j];
                }
                a = Math.max(a, b);
                dp[i][j] = a + triangle[i][j];
            }
        }

        int result = 0;
        for (int a : dp[triangle.length - 1]) {
            result = Math.max(result, a);
        }

        return result;
    }
}