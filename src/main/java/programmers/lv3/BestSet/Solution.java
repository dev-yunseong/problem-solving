package programmers.BestSet;

import java.lang.*;

class Solution {

    public int[] solution(int n, int s) {
        var tr = new int[1];
        tr[0] = -1;

        if (n > s) {
            return tr;
        }

        float a = (float) s / n;
        int a1 = (int) Math.ceil(a);
        int a2 = (int) Math.floor(a);

        int sum = a2 * n;

        for (int i = n; i >= 0; i--) {
            if (sum == s) {
                return createAnswer(a2, a1, i, n);
            }

            sum -= a2;
            sum += a1;
        }

        return tr;
    }

    private int[] createAnswer(int f, int c, int num, int n) {
        var answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = (i < num ? f : c);
        }
        return answer;
    }
}