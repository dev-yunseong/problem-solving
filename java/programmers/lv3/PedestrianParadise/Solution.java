package programmers.lv3.PedestrianParadise;

import java.util.*;

class Solution {
    int MOD = 20170805;

    private int[][] dp;

    public int solution(int m, int n, int[][] cityMap) {
        dp = new int[cityMap.length][cityMap[0].length];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cityMap[i][j] == 1) continue;

                if (0 <= i - 1) {
                    int cell = cityMap[i-1][j];
                    if (cell == 0) {
                        dp[i][j] += dp[i-1][j];
                    } else if (cell == 2) {
                        dp[i][j] += getFirstNot2(cityMap, dp, i-1, j, -1, 0);
                    }
                }

                dp[i][j] = dp[i][j] % MOD;

                if (0 <= j - 1) {
                    int cell = cityMap[i][j-1];
                    if (cell == 0) {
                        dp[i][j] += dp[i][j-1];
                    } else if (cell == 2) {
                        dp[i][j] += getFirstNot2(cityMap, dp, i, j - 1, 0, -1);
                    }
                }
                dp[i][j] = dp[i][j] % MOD;
            }
        }

        return dp[m-1][n-1];
    }

    private int getFirstNot2(int[][] cityMap, int[][] dp, int i, int j, int deltaI, int deltaJ) {
        while (cityMap[i][j] == 2) {
            i += deltaI;
            j += deltaJ;
            if (i < 0 || j < 0) return 0;
        }
        return dp[i][j];
    }
}