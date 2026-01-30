package programmers.lv2.GroupPhoto;

import java.util.*;
import java.lang.*;

class Solution {

    List<Character> chars = new ArrayList(List.of('A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'));

    public int solution(int n, String[] data) {
        int answer = 0;
        do {
            if (isGood(n, data)) {
                answer++;
            }
        } while (nextPermutation(chars));
        return answer;
    }

    private boolean nextPermutation(List<Character> last) {
        int i = last.size() - 2;
        while (i >= 0 && last.get(i) > last.get(i + 1)) { i -= 1;}

        if (i < 0) return false;

        int j = last.size() - 1;
        while (last.get(i) > last.get(j)) { j -= 1;}

        Collections.swap(last, i, j);
        Collections.reverse(last.subList(i+1, last.size()));
        return true;
    }

    private boolean isGood(int n, String[] data) {
        for (int i = 0; i < n; i++) {
            String prompt = data[i];

            Character a = prompt.charAt(0);
            Character b = prompt.charAt(2);
            Character c = prompt.charAt(3);
            int d = prompt.charAt(4) - '0';

            int ia = chars.indexOf(a);
            int ib = chars.indexOf(b);
            int e= Math.abs(ia - ib) - 1;

            boolean good = switch(c) {
                case '=' -> e == d;
                case '<' -> e < d;
                case '>' -> e > d;
                default -> false;
            };

            if (!good) {
                return false;
            }
        }

        return true;
    }
}