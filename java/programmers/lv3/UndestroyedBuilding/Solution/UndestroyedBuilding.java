package programmers.lv3.UndestroyedBuilding.Solution;

import java.util.*;

class Solution {

    public int solution(int[][] board, int[][] skill) {

        int[][] deltaBoard = new int[board.length+1][board[0].length+1];

        for (int[] skil : skill) {
            int deltaValue = (skil[0] == 1 ? -1 : 1) * skil[5];
            int r1 = skil[1];
            int r2 = skil[3];
            int c1 = skil[2];
            int c2 = skil[4];

            deltaBoard[r1][c1] += deltaValue;
            deltaBoard[r1][c2 + 1] += -deltaValue;
            deltaBoard[r2+1][c1] += -deltaValue;
            deltaBoard[r2+1][c2+1] += deltaValue;
        }

        for (int r = 0; r <= board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                deltaBoard[r][c + 1] += deltaBoard[r][c];
            }
            // System.out.println(Arrays.toString(deltaBoard[r]));
        }

        for (int c = 0; c <= board[0].length; c++) {
            for (int r = 0; r < board.length; r++) {
                deltaBoard[r + 1][c] += deltaBoard[r][c];
            }
        }


        int answer = 0;


        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (deltaBoard[r][c] + board[r][c] >= 1) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
