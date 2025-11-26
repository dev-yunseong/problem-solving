package programmers.NAryGame;

import java.util.*;
import java.lang.*;

class Solution {

    public String solution(int n, int t, int m, int p) {
        var answer = new StringBuilder();

        int number = 0;
        int allOfCount = 0;
        while (answer.length() < t) {
            var numerInString = getNum(number, n);

            for (var a : numerInString) {
                allOfCount++;
                if (allOfCount % m == p % m) {
                    answer.append(a);
                }
                if (answer.length() >= t) {
                    break;
                }
            }

            number++;
        }

        return answer.toString();
    }

    // 19, 4 -> 19 / 4
    // 100 /10 -> 100 / 10 = 10 (0)
    private Deque<String> getNum(int i, int n) {
        Deque<String> result = new LinkedList();
        do {
            int b = i % n;
            i = i / n;
            result.addFirst(int2String(b));
        } while(i != 0);

        return result;
    }

    private String int2String(int i) {
        if (i < 10) {
            return String.valueOf(i);
        }

        return switch(i) {
            case 10 -> "A";
            case 11 -> "B";
            case 12 -> "C";
            case 13 -> "D";
            case 14 -> "E";
            case 15 -> "F";
            default -> "a";
        };
    }
}