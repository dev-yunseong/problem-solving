package programmers.lv3.SheepAndWolves;

import java.util.*;

class Solution {

    private Map<Integer, List<Integer>> edges = new HashMap();
    private int[] info;
    public int solution(int[] info, int[][] edges) {
        initEdges(edges);
        this.info = info;

        List<Integer> visited = new ArrayList<Integer>();
        visited.add(0);

        return traverse(visited, 1);
    }

    private int traverse(List<Integer> visited, int num) {
        int result = -1;

        List<Integer> iter = new ArrayList(visited);
        for (int i : iter) {
            if (edges.get(i) == null) { // Null Reference 방지
                continue;
            }

            for (int next : edges.get(i)) {
                if (visited.contains(next)) { // Cut
                    continue;
                }

                int temp;
                if (info[next] == 0) {
                    visited.add(next);
                    temp = traverse(visited, num + 1);
                } else {
                    if (num <= 1) {
                        continue;
                    }
                    visited.add(next);
                    temp = traverse(visited, num - 1);
                }
                result = Math.max(result, temp);

                visited.remove((int) visited.size() - 1);
            }
        }


        if (result == -1) { // leaf
            result = getNum(visited);
            return result;
        }

        return result;
    }

    private int getNum(List<Integer> visited) {
        int result = 0;
        for (int v : visited) {
            if (info[v] == 0) {
                result++;
            }
        }
        return result;
    }

    private void initEdges(int[][] data) {
        for (int[] edge : data) {
            int a = edge[0];
            int b = edge[1];
            if (edges.get(a) == null) {
                edges.put(a, new ArrayList());
            }
            edges.get(a).add(b);
        }
    }
}