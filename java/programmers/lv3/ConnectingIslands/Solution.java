package programmers.lv3.ConnectingIslands;

import java.util.*;

class Solution {

    int[][] matrix;

    public int solution(int n, int[][] costs) {
        initMatrix(n, costs);
        return getAnswer();
    }

    private int getAnswer() {
        Queue<Cost> queue = new PriorityQueue();
        Set<Integer> visited = new HashSet();
        queue.add(new Cost(0, 0));
        int answer = 0;

        while (queue.size() != 0) {
            Cost cost = queue.remove();
            if (visited.contains(cost.destination)) continue;
            visited.add(cost.destination);
            answer += cost.cost;

            for (int i = 0; i < matrix.length; i++) {
                int costValue = matrix[cost.destination][i];
                if (costValue < 0) {
                    continue;
                }

                queue.add(new Cost(costValue, i));
            }
        }
        return answer;
    }

    private class Cost implements Comparable {
        int cost;
        int destination;

        public Cost(int cost, int destination) {
            this.cost = cost; this.destination = destination;
        }

        @Override
        public int compareTo(Object other) {
            Cost otherCost = (Cost) other;
            return cost - otherCost.cost;
        }

    }

    private void initMatrix(int n, int[][] costs) {
        matrix = new int[n][n];
        for (int[] row : matrix) {
            Arrays.fill(row, -1);
        }

        for (int[] cost : costs) {
            matrix[cost[0]][cost[1]] = cost[2];
            matrix[cost[1]][cost[0]] = cost[2];
        }
    }
}