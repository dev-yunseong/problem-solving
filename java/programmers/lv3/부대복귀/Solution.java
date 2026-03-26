package programmers.lv3.부대복귀;

import java.util.*;

class Solution {

    Map<Integer, List<Integer>> adjNodes = new HashMap();

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        for (int[] road : roads) { add(road[0], road[1]); }
        return findDistances(destination, sources);
    }

    private class State {
        int num;
        int distance;
        State(int a, int b) {this.num = a; this.distance = b;}
    }

    private int[] findDistances(int source, int[] destinations) {
        Queue<State> queue = new LinkedList();
        queue.add(new State(source, 0));
        Set<Integer> visited = new HashSet();
        Set<Integer> targets = new HashSet();
        for (int d : destinations) targets.add(d);

        int[] answer = new int[destinations.length];
        Arrays.fill(answer, -1);

        int candidate = Integer.MAX_VALUE;
        int candidateNum = -1;

        while (queue.size() != 0) {
            State currentState = queue.remove();
            if (visited.contains(currentState.num)) continue;
            visited.add(currentState.num);


            if (targets.contains(currentState.num)) {

                for (int i = 0; i < destinations.length; i++) {
                    if (destinations[i] == currentState.num) {
                        answer[i] = currentState.distance;
                    }
                }
            }


            if (adjNodes.get(currentState.num) == null) continue;
            for (int num : adjNodes.get(currentState.num)) {
                queue.add(new State(num, currentState.distance + 1));
            }
        }

        return answer;
    }

    private void add(int n1, int n2) {
        addTo(n1, n2);
        addTo(n2, n1);
    }

    private void addTo(int n1, int n2) {
        if (!adjNodes.containsKey(n1)) {
            adjNodes.put(n1, new ArrayList());
        }
        adjNodes.get(n1).add(n2);

    }
}