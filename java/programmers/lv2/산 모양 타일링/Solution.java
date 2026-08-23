class Solution {
    
    public int solution(int n, int[] tops) {
        
        int[] dp = new int[2 * n + 1];
        dp[0] = 1;
        
        for (int i = 1; i < 2 * n + 1; i++) {
            if (i % 2 == 0) {
                dp[i] = (getValue(i-1, dp) + getValue(i-2, dp)) % 10007;
            } else if (tops[i / 2] == 0) {
                dp[i] = (getValue(i-1, dp) + getValue(i-2, dp)) % 10007;
            } else {
                dp[i] = (getValue(i-1, dp) * 2 + getValue(i-2, dp)) % 10007;
            }
            
        }
        return dp[2 * n];
    }
    
    private int getValue(int index, int[] dp) {
        if (index < 0) {
            return 1;
        }
        return dp[index];
    }
}