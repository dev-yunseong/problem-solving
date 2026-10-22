import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        // Matrix 초기화
        int[][] fareMatrix = new int[n][n];
        
        for (int[] fareLine : fareMatrix) {
            Arrays.fill(fareLine, Integer.MAX_VALUE/4);
        }
        
        for (int i = 0; i < n; i++) {
            fareMatrix[i][i] = 0;
        }
        
        for (int[] fare : fares) {
            fareMatrix[fare[0] - 1][fare[1] - 1] = fare[2];
            fareMatrix[fare[1] - 1][fare[0] - 1] = fare[2];
        }
        
        // 플로이드 워샬로 
        for (int k = 0; k < n; k++) {
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n; i++) {
                    fareMatrix[i][j] = Math.min(fareMatrix[i][j], fareMatrix[i][k] + fareMatrix[k][j]);
                }
            }
        }
        
        // 정답 찾기
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            
            System.out.printf("%d\n", fareMatrix[s - 1][i] + fareMatrix[i][a - 1] + fareMatrix[i][b - 1]);
            
            answer = Math.min(
                answer, 
                fareMatrix[s - 1][i] + fareMatrix[i][a - 1] + fareMatrix[i][b - 1]
            );
        }
        
        return answer;
    }
}