package programmers.lv3.CardMatching;

import java.util.*;
import java.util.stream.*;

class Solution {

    private int[] diffR = new int[] {0, 0, 1, -1};
    private int[] diffC = new int[] {1, -1, 0, 0};

    public int solution(int[][] board, int r, int c) {

        return backtracking(board, new int[]{r, c}, Integer.MAX_VALUE);
    }

    public int backtracking(int[][] board, int[] rc, int maxValue) {
        if (isClear(board)) return 0;

        var results = getAllCards(board).stream()
                .map(card -> {
                    int[] other = getOtherCard(board, card);
                    int keyCount = getKeyCount(board, rc, card) + getKeyCount(board, card, other);
                    return new int[]{card[0], card[1], other[0], other[1], keyCount+2};
                }).sorted((a, c) -> a[4] - c[4])
                .collect(Collectors.toList());

        int minResult = maxValue;

        for (var result : results) {
            if (result[4] > minResult) continue;
            int type = board[result[0]][result[1]];
            board[result[0]][result[1]] = 0;
            board[result[2]][result[3]] = 0;

            int temp = backtracking(board, new int[]{result[2], result[3]}, minResult);
            minResult = Math.min(temp + result[4], minResult);

            board[result[0]][result[1]] = type;
            board[result[2]][result[3]] = type;
        }
        return minResult;
    }

    private int[] getOtherCard(int[][]board, int[]rc) {
        int cardType = board[rc[0]][rc[1]];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (r == rc[0] && c == rc[1]) continue;
                if (board[r][c] == cardType) return new int[] {r, c};
            }
        }
        return null;
    }

    private int getKeyCount(int[][] board, int[] start, int[] end) {
        Queue<BfsItem> queue = new LinkedList();
        queue.add(new BfsItem(start, 0));
        Set visited = new HashSet();

        while (queue.size() != 0) {
            BfsItem item = queue.remove();
            List<Integer> key = item.getKey();
            if (visited.contains(key)) continue;
            visited.add(key);

            if (key.get(0) == end[0] && key.get(1) == end[1]) return item.keyCount;

            for (var neighbor : getNeighbors(board, item.rc)) {
                queue.add(new BfsItem(neighbor, item.keyCount + 1));
            }
        }
        return -1;
    }

    private class BfsItem {
        int[] rc;
        int keyCount;
        BfsItem(int[] rc, int keyCount) {
            this.rc = rc; this.keyCount = keyCount;
        }

        List<Integer> getKey() {
            return List.of(rc[0], rc[1]);
        }
    }

    private List<int[]> getAllCards(int[][] board) {
        var results = new ArrayList();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] != 0) {
                    results.add(new int[]{r, c});
                }
            }
        }
        return results;
    }

    private boolean isClear(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<int[]> getNeighbors(int[][] board, int[] rc) {
        var results = new ArrayList();

        // default arrow
        for (int i = 0; i < 4; i++) {
            int newR = rc[0] + diffR[i];
            int newC = rc[1] + diffC[i];

            if (0 <= newR && newR < board.length && 0 <= newC && newC < board[0].length) {
                results.add(new int[]{newR, newC});
            }
        }

        // ctrl arrow
        for (int i = 0; i < 4; i++) {
            int newR = rc[0];
            int newC = rc[1];

            while (true) {
                newR += diffR[i];
                newC += diffC[i];
                if (0 <= newR && newR < board.length && 0 <= newC && newC < board[0].length) {
                    if (board[newR][newC] != 0) {
                        results.add(new int[]{newR, newC});
                        break;
                    }
                } else {

                    if (newR - diffR[i] == rc[0] && newC - diffC[i] == rc[1]) continue;
                    results.add(new int[]{newR - diffR[i], newC - diffC[i]});
                    break;
                }
            }
        }
        return results;
    }
}