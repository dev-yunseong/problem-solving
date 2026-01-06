package programmers.OvertimeIndex;

import java.util.*;

class Solution {

    public long solution(int n, int[] works) {

        PriorityQueue queue = new PriorityQueue(Collections.reverseOrder());
        Arrays.stream(works)
                .forEach(i -> queue.add(i));
        while (n --> 0) {
            int value = (int) queue.poll();
            if (value == 0) break;

            int deltaValue = Math.max(Math.min(n, value - (int) queue.peek()), 1);
            queue.add(value - deltaValue);
            n -= deltaValue - 1;
        }

        long answer = 0;

        while (queue.size() > 0) {
            int temp = (int) queue.poll();
            answer += temp*temp;
        }

        return answer;
    }
}