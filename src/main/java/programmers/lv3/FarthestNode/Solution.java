package programmers.FarthestNode;

import java.util.*;

class Solution {

    private Map<Integer, List<Integer>> edges = new HashMap();

    public int solution(int n, int[][] edge) {
        initEdges(edge);

        List<Integer> current = new ArrayList();
        Set<Integer> visited = new HashSet();
        int answer = 0;
        current.add(1);
        visited.add(1);

        do {
            answer = current.size();
            current = nextStep(current, visited);
        } while (current.size() > 0);

        return answer;
    }

    private List<Integer> nextStep(List<Integer> current, Set<Integer> visited) {
        List<Integer> nexts = new ArrayList();

        for (var c : current) {
            var tempEdges = edges.get(c);
            if (tempEdges == null) continue;
            for (var nextNode : tempEdges) {
                if (visited.contains(nextNode)) continue;

                nexts.add(nextNode);
                visited.add(nextNode);
            }
        }

        return nexts;
    }

    private void initEdges(int[][] edge) {
        for (int[] e : edge) {
            addEdge(e[0], e[1]);
            addEdge(e[1], e[0]);
        }
    }

    private void addEdge(int a, int b) {
        if (edges.get(a) == null) {
            edges.put(a, new ArrayList());
        }
        edges.get(a).add(b);
    }
}
