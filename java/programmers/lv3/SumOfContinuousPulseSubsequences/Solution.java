package programmers.lv3.SumOfContinuousPulseSubsequences;

/*
dp
2 3 -6 1

2 (2) - 0
-1 (2 -3) - 1
-7 (2, -3, -6) - 2

dp[i] = dp[i-1] + seq[i] * (i % 2 == 0 ? 1 : -1);

그래서 0 - 3 = dp[3]
1 - 3 = dp[3] - dp[0]
*/

class Solution {

    public long solution(int[] sequence) {
        long[] dp = new long[sequence.length + 1];
        dp[0] = 0;
        for (int i = 0; i < sequence.length; i++) {
            dp[i+1] = dp[i]
                    + sequence[i] * (i % 2 == 0 ? 1 : -1);
        }

        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;

        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
            min = Math.min(min, dp[i]);
        }

        return max - min;
    }
}