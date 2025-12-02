package programmers.TowerOfHanoi;

import java.util.*;

class Solution {

/*

- 규칙
 - 한번에 하나의 원판만 옮길 수 있음
 - 큰 원판이 작은 원판 위에 있어서는 안 됨

*/

    public int[][] solution(int n) {
        return convertToAnswer(move(1, 3, n));
    }

    private int[][] convertToAnswer(List<List<Integer>> a) {
        int[][] answer = new int[a.size()][2];
        for (int i = 0; i < a.size(); i++) {
            answer[i][0] = a.get(i).get(0);
            answer[i][1] = a.get(i).get(1);
        }
        return answer;
    }

    private List<List<Integer>> move(int from, int to, int num) {
        if (num == 1) {
            return List.of(List.of(from, to));
        }
        List subAnswer = new ArrayList();
        subAnswer.addAll(move(from, getOther(from, to), num - 1));
        subAnswer.addAll(move(from, to, 1));
        subAnswer.addAll(move(getOther(from, to), to, num - 1));

        return subAnswer;
    }

    private int getOther(int a, int b) {
        return 1 + 2 + 3 - a - b;
    }
}