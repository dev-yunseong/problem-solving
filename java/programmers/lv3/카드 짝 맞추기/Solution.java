import java.util.*;

class Solution {
    public int solution(int[][] board, int r, int c) {
        int cardNum = 0;
        for (int[] line : board) {
            for (int a : line) { 
                if (a != 0)
                    cardNum++;
            }
        }
        return dfs(board, r, c, cardNum, false);
    }
    
    class Item {
        int r, c, cost;
        Item(int r, int c, int cost) { this.r = r; this.c = c; this.cost = cost; } 
    }
    
    private int dfs(
        int[][] board, int r, int c, 
        int remainingCard,
        boolean should
    ) {
        if (remainingCard == 0) return 0;
        int anwser = Integer.MAX_VALUE;
        
        if (board[r][c] != 0) { // 현재 카드 위에 있을 때
            int temp = board[r][c];
            int[] pairRC = getPair(board, r, c);
            int cost = bfs(
                board, r, c,
                pairRC[0], pairRC[1]
            );

            board[r][c] = 0;
            board[pairRC[0]][pairRC[1]] = 0;

            anwser = Math.min(
                dfs(
                    board, pairRC[0], pairRC[1], 
                    remainingCard - 2, false) + 2 + cost, // 두 카드를 각각 클릭 하는 횟수 + 두 카드 사이를 이동한 횟수
                anwser
            );

            board[r][c] = temp;
            board[pairRC[0]][pairRC[1]] = temp;
        }
        
        if (should) return anwser;

        // 카드가 있는 다른 곳으로 이동
        for (int[] nei : allCards(board)) {
            if (nei[0] == r && nei[1] == c) continue; // 현재 있는 곳이면 건너뛰기

            int cost = bfs( // 현재 위치에서 카드가 있는 위치로 이동
                board, r, c,
                nei[0], nei[1]
            );
            
            anwser = Math.min(
                dfs(
                    board, nei[0], nei[1], 
                    remainingCard, true) + cost, 
                anwser
            );
        }

        return anwser;
    }
    
    private int[] getPair(int[][] board, int r, int c) {
        int target = board[r][c];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == r && j == c) continue;
                if (target == board[i][j]) return new int[]{i, j};
            }
        }
        return null;
    }
    
    // 출발지에서 목적지까지 갈 수 있는 최소한의 조작을 리턴
    private int bfs(int[][] board, int r, int c, int toR, int toC) {
        Queue<Item> queue = new LinkedList();
        queue.add(new Item(r, c, 0));
        int[][] visited = new int[4][4];
        for (int[] line : visited) { Arrays.fill(line, Integer.MAX_VALUE); }

        while (queue.size() > 0) {
            Item current = queue.remove();
            
            if (visited[current.r][current.c] <= current.cost) {
                continue;
            }
            visited[current.r][current.c] = current.cost;
            if (current.r == toR && current.c == toC) break;
            
            for (int[] nei : getNei(board, current.r, current.c)) {
                queue.add(new Item(nei[0], nei[1], current.cost + 1));
            }
        }
        
        return visited[toR][toC];
    }
    
    List<int[]> allCards(int[][] board) {
        List<int[]> answer = new ArrayList();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    answer.add(new int[] { i, j });
                }
            }
        }
        return answer;
    }
    
    
    int[] dX = new int[] {0, 0, -1, 1};
    int[] dY = new int[] {1, -1, 0, 0};
    
    // 한번의 조작으로 갈 수 있는 타일 좌표 list를 리턴
    List<int[]> getNei(int[][] board, int r, int c) {
        List<int[]> answer = new ArrayList();
        for (int i = 0; i < 4; i++) {
            int nC = c + dX[i];
            int nR = r + dY[i];
            if (0 <= nC && nC < 4 && 0 <= nR && nR < 4) {
                answer.add(new int[] {nR, nC});
            }
        }
        
        for (int i = 0; i < 4; i++) {
            
            int[] nextCR = getCtrlMove(board, r, c, i);
            
            if (0 <= nextCR[0] && nextCR[0] < 4 && 0 <= nextCR[1] && nextCR[1] < 4) { // 유효한 위치면 적용
                answer.add(nextCR);
            }
        }
        
        return answer;
    }
    
    
    private int[] getCtrlMove(int[][] board, int r, int c, int i) {
        int nC = c + dX[i];
        int nR = r + dY[i];
        while ( // 업데이트 시 위치가 올바른 위치이고 현재 위치가 카드가 없을 때
            0 <= nC + dX[i] && nC + dX[i] < 4 && 0 <= nR + dY[i] && nR + dY[i] < 4 &&
            board[nR][nC] == 0
        ) {
            nC += dX[i];
            nR += dY[i];
        }
        
        return new int[] {nR, nC};
    }
}