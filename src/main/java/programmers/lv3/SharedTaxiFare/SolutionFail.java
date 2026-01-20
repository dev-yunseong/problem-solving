package programmers.lv3.SharedTaxiFare;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

class SolutionFail {
    /*
    a-b
    s-a
    s-b
    의 비용을 모두 구하는 문제
    so start, end를 지정할 수 있는 bfs를 구하면 된다.
    */
    Map<Integer, Map<Integer, Integer>> edges = new HashMap();

    public int solution(int n, int s, int a, int b, int[][] fares) {
        initEdges(fares);

        int ab = bfs(a, b);
        int sa = bfs(s, a);
        int sb = bfs(s, b);

        System.out.printf("ab: %d, sa: %d, sb: %d", ab, sa, sb);

        int r1 = sa + sb;
        int r2 = sa + ab;
        int r3 = sb + ab;

        return Math.min(r1, Math.min(r2, r3));
    }

    private int bfs(int start, int end) {
        Queue<Node> queue = new PriorityQueue();
        queue.add(new Node(start, 0));
        Set visited = new HashSet();

        while(queue.size() > 0) {
            Node current = queue.remove();

            if (current.id == end) {
                return current.cost;
            }

            if (visited.contains(current.id)) {
                continue;
            }

            for (var entry : edges.get(current.id).entrySet()) {
                queue.add(new Node(entry.getKey(), entry.getValue() + current.cost));
            }
        }

        return -1;
    }

    private class Node implements Comparable {
        public int id;
        public int cost;

        public Node(int id, int cost) {
            this.id = id; this.cost = cost;
        }

        @Override
        public boolean equals(Object other) {
            Node n = (Node) other;
            return id == n.id;
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public int compareTo(Object other) {
            Node n = (Node) other;
            return cost - n.cost;
        }
    }

    private void initEdges(int[][] fares) {
        for (int[] fare : fares) {
            putEdges(fare[0], fare[1], fare[2]);
            putEdges(fare[1], fare[0], fare[2]);
        }
    }

    private void putEdges(int a, int b, int cost) {
        if (edges.get(a) == null) {
            edges.put(a, new HashMap());
        }
        edges.get(a).put(b, cost);
    }
}
