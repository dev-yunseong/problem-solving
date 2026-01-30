import java.util.*;

class Solution {
    
    private int[][] dp;
    
    public int[] solution(int target) {
        initDp(target);
        var answer = getAnswer(target);
        return answer;
    }
    

    private int[] getAnswer(int target) {
        if (target <= -1) return null;
        if (dp[target][0] != -1) return new int[]{dp[target][0], dp[target][1]};
        
        int[] result = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
        
        int[] temp = getAnswer(target - 50);
        if (temp != null) {
            temp[0] += 1;
            temp[1] += 1;
            update(result, temp);
        }

        for (int i = 1; i <= 20; i++) {
            int[] temp1 = getAnswer(target - i);
            if (temp1 == null) continue;
            temp1[0] += 1;
            temp1[1] += 1;
            update(result, temp1);
            
            int[] temp2 = getAnswer(target - 2 * i);
            if (temp2 == null) continue;
            temp2[0] += 1;
            update(result, temp2);
            
            int[] temp3 = getAnswer(target - 3 * i);
            if (temp3 == null) continue;
            temp3[0] += 1;
            update(result, temp3);
        }
        dp[target][0] = result[0];
        dp[target][1] = result[1];
        return result;
    }
    
    private void update(int[] result, int[] temp) {
        if (result[0] > temp[0]) {
            result[0] = temp[0];
            result[1] = temp[1];
        } else if (result[0] == temp[0]) {
            result[1] = Math.max(temp[1], result[1]);
        }
    }
    
    
    private void initDp(int target) {
        dp = new int[target+1][2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        
        Arrays.fill(dp[0], 0);
    }
}