package programmers.lv3.DisappearingPlatform;

import java.util.*;

class Solution {

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        return backtracking(board, aloc, bloc).steps;
    }

    private Result backtracking(int[][] board, int[] aloc, int[] bloc) {
         print(board, aloc, bloc);
        List<int[]> possibleLocs = possibleLocs(board, aloc, bloc);
        if (possibleLocs.size() == 0) {
             System.out.println("==============");
            return new Result(0, false);
        }
        if (aloc[0] == bloc[0] && aloc[1] == bloc[1]) {
             System.out.println("==============");
            return new Result(1, true);
        }

        var maxValue = Integer.MIN_VALUE; // in loss state
        var minValue = Integer.MAX_VALUE; // in win state
        boolean canWin = false;

        for (int[] possibleLoc : possibleLocs) {
            board[aloc[0]][aloc[1]] = 0;

            Result temp = backtracking(board, bloc, possibleLoc);

            if (!canWin && !temp.isWin) {
                canWin = true;
            }

            if (!temp.isWin) { // 현재 a가 이김
                minValue = Math.min(minValue, temp.steps);
            } else {
                maxValue = Math.max(maxValue, temp.steps);
            }

            board[aloc[0]][aloc[1]] = 1;
        }
        if (canWin) {
            return new Result(minValue + 1, true);
        }
        return new Result(maxValue + 1, false);
    }

    private class Result {
        int steps;
        boolean isWin;

        Result(int steps, boolean isWin) {
            this.steps = steps; this.isWin = isWin;
        }
    }

    private void print(int[][] board, int[] aloc, int[] bloc) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (aloc[0] == r && aloc[1] == c || bloc[0] == r && bloc[1] == c) {
                    System.out.print("@ ");
                } else {
                    System.out.print(board[r][c] == 1 ? "_ " : ". ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }


    private int[] diffX = new int[]{0, 0, -1, 1};
    private int[] diffY = new int[]{-1, 1, 0, 0};

    private List<int[]> possibleLocs(int[][] board, int[] cloc, int[] oloc) {
        List<int[]> result = new ArrayList();
        for (int i = 0; i < 4; i++) {
            int r = cloc[0] + diffY[i];
            int c = cloc[1] + diffX[i];

            if (0 <= r && r < board.length && 0 <= c && c < board[0].length) {
                if (board[r][c] == 1) {
                    result.add(new int[] {r, c});
                }
            }
        }

        return result;
    }
}
