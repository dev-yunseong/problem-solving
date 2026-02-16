package programmers.lv3.SteppingStones;

import java.util.*;

class Solution {
    Deque<Integer> deque = new ArrayDeque();

    public int solution(int[] stones, int k) {
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.getFirst() <= i-k) deque.removeFirst();
            while (!deque.isEmpty() && stones[deque.getLast()] <= stones[i]) deque.removeLast();

            deque.addLast(i);
        }
        int answer = stones[deque.getFirst()];

        for (int i = k; i < stones.length; i++) {
            while (!deque.isEmpty() && deque.getFirst() <= i-k) deque.removeFirst();
            while (!deque.isEmpty() && stones[deque.getLast()] <= stones[i]) deque.removeLast();

            deque.addLast(i);
            answer = Math.min(stones[deque.getFirst()], answer);
        }

        return answer;
    }
}