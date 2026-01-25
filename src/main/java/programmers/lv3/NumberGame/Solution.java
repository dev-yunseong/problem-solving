package programmers.lv3.NumberGame;

import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int n = A.length;
        int aIndex = 0;
        int bIndex = 0;
        int answer = 0;

        while (aIndex < n && bIndex < n) {
            if (A[aIndex] < B[bIndex]) {
                answer++;
                aIndex++;
                bIndex++;
                continue;
            } else {
                bIndex++;
            }
        }

        return answer;
    }
}