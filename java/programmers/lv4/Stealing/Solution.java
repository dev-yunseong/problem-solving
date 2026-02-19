package programmers.lv4.Stealing;

class Solution {

    int[] dp;

    public int solution(int[] money) {
        dp = new int[money.length];
        dp[0] = money[0];
        dp[1] = Math.max(dp[0], money[1]);

        for (int i = 2; i < money.length - 1; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);
        }
        int temp1 = dp[money.length - 2];

        dp[1] = money[1];
        dp[2] = Math.max(dp[1], money[2]);

        for (int i = 3; i < money.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);
        }
        int temp2 = dp[money.length - 1];


        return Math.max(temp1, temp2);
    }

}