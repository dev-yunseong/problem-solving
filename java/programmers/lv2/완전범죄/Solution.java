import java.util.Arrays;

class Solution {
    
    
    public int solution(int[][] info, int n, int m) {
        int[][] dp = new int[info.length][m];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE/4);
        }
        dp[0][0] = info[0][0];
        if (info[0][1] < m) {
            dp[0][info[0][1]] = 0;
        }
        
        
        for (int i = 1; i < info.length; i++) {
            for (int b = 0; b < m; b++) {
                if (b + info[i][1] < m) {
                    dp[i][b + info[i][1]] = Math.min(dp[i-1][b], dp[i][b + info[i][1]]);
                }
                dp[i][b] = Math.min(dp[i-1][b] + info[i][0], dp[i][b]);
            }
            
        }
        
        int answer = Integer.MAX_VALUE;
        
        for (int[] d : dp) {
            for (int p : d) {
                System.out.print(p + " ");
            }
            System.out.println("");
        }
        
        for (int i = 0; i < m; i++) {
            int temp = dp[info.length - 1][i];
            answer = Math.min(answer, temp);
        }
        return answer >= n ? -1 : answer;
    }
}