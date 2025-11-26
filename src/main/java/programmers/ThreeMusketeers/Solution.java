package programmers.ThreeMusketeers;
import java.util.*;

class Solution {

    public int solution(int[] number) {
        int answer = 0;
        var cursor = initList(number.length);

        do {
            // System.out.println(cursor);
            if (check(number, cursor)) {
                answer++;
            }
        } while(nextCursor(cursor));

        return answer;
    }

    private boolean nextCursor(List<Integer> cursor) {
        int firstZero = cursor.size() - 2;
        while (firstZero >= 0 && cursor.get(firstZero) >= cursor.get(firstZero + 1)) {firstZero--;}
        if (firstZero < 0) {
            return false;
        }

        int firstOne = cursor.size() - 1;
        while (cursor.get(firstOne) != 1) {firstOne--;}

        Collections.swap(cursor, firstZero, firstOne);
        Collections.reverse(cursor.subList(firstZero + 1, cursor.size()));

        return true;
    }

    private boolean check(int[] number, List<Integer> cursor) {
        int sum = 0;
        for (int i = 0; i < number.length; i++) {
            sum += number[i] * cursor.get(i);
        }
        // System.out.println(sum);
        return sum == 0;
    }

    private List<Integer> initList(int n) {
        List<Integer> cursor = new ArrayList();
        for (int i = 0; i < n - 3; i++) {
            cursor.add(0);
        }
        cursor.add(1);
        cursor.add(1);
        cursor.add(1);

        return cursor;
    }
}