package programmers.lv4.TerrainTraversal;

import java.util.*;

class Solution {

    public class Coo {
        public int x, y;

        public int delta = 0;

        public Coo(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object other) {
            Coo c= (Coo) other;
            return c.x == this.x && c.y == this.y;
        }

        @Override
        public int hashCode() {
            return x * N + y;
        }

        public boolean isValid() {
            return 0 <= x && x < N && 0 <= y && y < N;
        }
    }

    private int N;
    private int height;
    private int[][] land;

    public int solution(int[][] land, int height) {
        this.land = land;
        N = land.length;
        this.height = height;
        int answer = 0;
        return bfs(new Coo(0, 0));
    }

    private int[] dx = {0, 0, 1, -1};
    private int[] dy = {1, -1, 0, 0};

    private int bfs(Coo start) {
        int cost = 0;
        PriorityQueue<Coo> candidates = new PriorityQueue((c1, c2) -> ((Coo) c1).delta - ((Coo)c2).delta);
        start.delta = 0;
        candidates.add(start);

        Set<Coo> visited = new HashSet();

        while (candidates.size() > 0) {
            Coo current = candidates.poll();

            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);

            if (current.delta > height)
            {
                cost += current.delta;
            }

            List<Coo> ns = getNs(current);

            for (Coo n : ns) {
                if (!visited.contains(n)) {
                    candidates.add(n);
                }
            }
        }
        return cost;
    }

    private List<Coo> getNs(Coo coo) {
        List<Coo> result = new ArrayList();
        for (int i = 0; i < 4; i++) {
            Coo n = new Coo(coo.x + dx[i], coo.y + dy[i]);
            if (n.isValid()) {
                n.delta = Math.abs(land[n.x][n.y] - land[coo.x][coo.y]);
                result.add(n);
            }
        }
        return result;
    }
}