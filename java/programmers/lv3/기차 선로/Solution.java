import java.util.*;

class Solution {
    
    int n, m;

    private Map<Integer, Map<String, String>> sideToSideInTile = Map.of(
        1, Map.of(
            "left", "right",
            "right", "left"
        ),
        2, Map.of(
            "down", "up",
            "up", "down"
        ),
        3, Map.of(
            "left", "right",
            "right", "left",
            "down", "up",
            "up", "down"
        ),
        4, Map.of(
            "left", "up",
            "up", "left"
        ),
        5, Map.of(
            "up", "right",
            "right", "up"
        ),
        6, Map.of(
            "down", "right", 
            "right", "down"
        ),
        7, Map.of(
            "left", "down",
            "down", "left"
        ) 
    );
        
    
    private Map<Integer, List<String>> tileSides = Map.of(
        1, List.of("left", "right"),
        2, List.of("up", "down"),
        3, List.of("up", "down", "left", "right"),
        4, List.of("left", "up"),
        5, List.of("up", "right"),
        6, List.of("down", "right"),
        7, List.of("left", "down")
    );
    private Map<String, List<Integer>> sideToTile = Map.of(
        "left", List.of(1, 3, 4, 7),
        "right", List.of(1, 3, 5, 6),
        "up", List.of(2, 3, 4, 5),
        "down", List.of(2, 3, 6, 7)
    );
    private Map<String, String> opp = Map.of(
        "down", "up",
        "up", "down",
        "left", "right",
        "right", "left"
    );
    private Map<String, List<Integer>> sideToDelta = Map.of(
        "down", List.of(1, 0),
        "up", List.of(-1, 0),
        "left", List.of(0, -1),
        "right", List.of(0, 1)
    );
    
    
    public int solution(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        System.out.println(n + " " + m);
        return dfs(grid, 0, 0, "left");
    }
    
    private int dfs(int[][] grid, int curX, int curY, String lastSide) {
        if (curX < 0 || n <= curX || curY < 0 || m <= curY) return 0;
        int curTile = grid[curX][curY];
        if (curX == n - 1 && curY == m - 1) { // 도착했을 때
            if (isDone(grid)) return 1;
            return 0;
        }
        if (curTile == -1) return 0; // 해당 칸에 장애물이 있을 때
        if (curTile != 0 && !sideToTile.get(lastSide).contains(curTile)) return 0; // 해당칸이 이전 칸이랑 안 이어질 때
        if (grid[curX][curY] == 0) { // 해당 칸에 가능한 tile들을 배치해봄
            int answer = 0;
            for (int tile : sideToTile.get(lastSide)) {
                
                grid[curX][curY] = tile;
                
                String nextSide = sideToSideInTile.get(tile).get(lastSide);
                var delta = sideToDelta.get(
                    nextSide
                );
                
                
                answer += dfs(grid, curX + delta.get(0), curY + delta.get(1), opp.get(nextSide));
                grid[curX][curY] = 0;
            }
            
            return answer;
            
            
        } else { // 다음 side 방향으로 넘김
            String nextSide = sideToSideInTile.get(curTile).get(lastSide);
            var delta = sideToDelta.get(
                nextSide
            );
            
            return dfs(grid, curX + delta.get(0), curY + delta.get(1), opp.get(nextSide));
        }
    }
    
    private boolean isDone(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                
                if (grid[i][j] == 0 || grid[i][j] == -1) continue;
                
                var sides = tileSides.get(grid[i][j]);
                
                for (String side : sides) {
                    var delta = sideToDelta.get(side);
                    
                    int ti = delta.get(0) + i;
                    int tj = delta.get(1) + j;
                    
                    if (0 <= ti && ti < n && 0 <= tj && tj < m) {
                        if (sideToTile.get(opp.get(side)).contains(grid[ti][tj])) {
                            continue;
                        } else {
                            return false;
                        }
                    } else if (
                        (i == 0 && j == 0 && side == "left") ||
                        (i == n-1 && j == m-1 && (side == "right" || side == "down"))
                    ){
                        continue;
                        
                    } else {
                        return false;
                    }
                    
                }
                
            }
        }
        return true;
    }
}