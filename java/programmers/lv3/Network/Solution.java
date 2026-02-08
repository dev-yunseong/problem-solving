package programmers.lv3.Network;

import java.util.*;

class Solution {

    int[][] coms;
    int n;

    public int solution(int n, int[][] computers) {
        coms = computers;
        this.n = n;
        return getAnswer();
    }

    private int getAnswer() {
        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < n; i++) queue.add(i);
        int answer = 0;
        while (queue.size() > 0) {
            int comId = queue.remove();
            Set network = bfs(comId);
            queue.removeAll(network);
            answer++;
        }
        return answer;
    }

    private Set<Integer> bfs(int start) {
        Queue<Integer> queue = new LinkedList();
        queue.add(start);
        Set<Integer> visited = new HashSet();

        while (queue.size() > 0) {
            int comId = queue.remove();
            if (visited.contains(comId)) continue;
            visited.add(comId);
            for (int nextId = 0; nextId < n; nextId++) {
                if (coms[comId][nextId] == 0) continue;
                if (!visited.contains(nextId)) queue.add(nextId);
            }
        }

        return visited;
    }
}