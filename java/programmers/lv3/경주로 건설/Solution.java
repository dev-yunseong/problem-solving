import java.util.*;

class Solution {
    public int solution(int[][] board) {
        return bfs(board);
    }
    
    class Item {
        int lastIndex;
        int x, y, cost;
        
        Item(int x, int y, int cost, int lastIndex) {this.x = x; this.y = y; this.cost = cost; this.lastIndex = lastIndex;}
    }
    
    int[] dX = new int[] {0, 0, 1, -1};
    int[] dY = new int[] {1, -1, 0, 0};
    
    private int bfs(int[][] board) {
        Queue<Item> queue = new LinkedList<Item>();
        int n = board.length;
        int[][][] visited = new int[board.length][board.length][4];
        for (int[][] visitedPlane : visited) { 
            for (int[] visitedLine : visitedPlane)
                Arrays.fill(visitedLine, Integer.MAX_VALUE); 
        }
        Arrays.fill(visited[0][0], 0);
        queue.add(new Item(0, 0, 0, -1));
        
        while (queue.size() > 0) {
            Item current = queue.remove();
            
            if (current.lastIndex != -1) {
                if (visited[current.x][current.y][current.lastIndex] <= current.cost) continue;
                visited[current.x][current.y][current.lastIndex] = current.cost;
            }
            
            
            for (int i = 0; i < 4; i++) {
                int nX = current.x + dX[i];
                int nY = current.y + dY[i];
                if (0 <= nX && nX < n && 0 <= nY && nY < n && board[nX][nY] == 0) {
                    int cost = 100 + current.cost;
                    if (current.lastIndex != -1) { // 시작 지점이 아님
                        if (current.lastIndex != i) { // 직선이 아닐 때
                            cost += 500;   
                        }
                    }
                    
                    queue.add(new Item(nX, nY, cost, i));
                }
            }
            
        }
        
        int answer = Integer.MAX_VALUE;
        for (int temp : visited[n-1][n-1]) {
            answer = Math.min(temp, answer);
        }
        
        return answer;
    }
}