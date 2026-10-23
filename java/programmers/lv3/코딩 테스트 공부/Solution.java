import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {

        return bfs(alp, cop, problems);
    }
    
    class Item {
        int alp, cop, cost;
        Item(int alp, int cop, int cost) { this.alp = alp; this.cop = cop; this.cost = cost; }
    }
    
    private int[] getMaxReq(int[][] problems) {
        int[] reqs = new int[2];
        
        for (int[] problem : problems) {
            reqs[0] = Math.max(reqs[0], problem[0]);
            reqs[1] = Math.max(reqs[1], problem[1]);
        }
        return reqs;
    }
    
    private int bfs(int alp, int cop, int[][] problems) {
        int[] reqs = getMaxReq(problems);
        Queue<Item> queue = new LinkedList<Item>();
        queue.add(new Item(alp, cop, 0));
        int[][] visited = new int[reqs[0] + 1][reqs[1] + 1];
        
        for (int[] line : visited) {
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        
        while (queue.size() > 0) {
            Item current = queue.remove();
            
            if (visited[Math.min(reqs[0], current.alp)][Math.min(reqs[1], current.cop)] <= current.cost) {
                continue;
            }
            visited[Math.min(reqs[0], current.alp)][Math.min(reqs[1], current.cop)] = current.cost;
            
            if (current.alp < reqs[0]) 
                queue.add(new Item(current.alp + 1, current.cop, current.cost + 1));
            if (current.cop < reqs[1]) 
                queue.add(new Item(current.alp, current.cop + 1, current.cost + 1));
            
            for (int[] problem : problems) {
                if (problem[0] > current.alp || problem[1] > current.cop) continue; // 못 푸는 문제
                
                int nextAlp = current.alp + problem[2];
                int nextCop = current.cop + problem[3];
                int nextCost = current.cost + problem[4];
                
                queue.add(new Item(nextAlp, nextCop, nextCost));
                
            }
        }
        
        return visited[reqs[0]][reqs[1]];
    }
}