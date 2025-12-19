package programmers.SplittingPowerGrid;

import java.util.*;
import java.util.stream.*;

class Solution {

    private class State {
        int left, right, rights, lefts;

        State(int left, int right, int lefts, int rights) {
            this.left = left; this.right = right; this.rights = rights; this.lefts = lefts;
        }

        public int getDiff() {
            if (lefts == -1 && rights == -1) {
                lefts = sumNodes(left, right);
                rights = TOTAL - lefts;
            }
            return Math.abs(rights - lefts);
        }
    }

    private Map<Integer, List<Integer>> wires = new HashMap();
    private int TOTAL;
    public int solution(int n, int[][] wires) {
        TOTAL = n;
        initWiresMap(wires);

        int left = wires[0][0];
        int right = wires[0][1];

        State state = new State(left, right, -1, -1);
        int answer = Integer.MAX_VALUE;

        while (answer > state.getDiff()) {
            answer = state.getDiff();
            state = getNextState(state);
        }

        return answer;
    }

    private State getNextState(State state) {
        List<State> candidates;
        if (state.lefts > state.rights) {
            List<Integer> nexts = wires.get(state.left);
            if (nexts.size() == 1) {
                return state;
            }
            candidates = nexts.stream().filter(i -> i != state.right)
                    .map(i ->
                            new State(i, state.left, -1, -1))
                    .collect(Collectors.toList());
        } else {
            List<Integer> nexts = wires.get(state.right);
            if (nexts.size() == 1) {
                return state;
            }
            candidates = nexts.stream().filter(i -> i != state.left)
                    .map(i ->
                            new State(state.right, i, -1, -1))
                    .collect(Collectors.toList());
        }

        State result = candidates.get(0);
        for (State candidate : candidates.subList(1, candidates.size())) {
            if (result.getDiff() > candidate.getDiff()) {
                result = candidate;
            }
        }
        return result;
    }

    private int sumNodes(int start, int cutted) {
        int result = 0;
        Queue<Integer> queue = new LinkedList();
        queue.add(start);
        Set<Integer> visited = new HashSet();

        while (queue.size() > 0) {
            int current = queue.remove();
            if (current == cutted || visited.contains(current)) { // cut
                continue;
            }

            visited.add(current);

            result++;
            queue.addAll(wires.get(current));
        }
        return result;
    }

    private void initWiresMap(int[][] wires) {
        for (int[] wire : wires) {
            this.wires.computeIfAbsent(wire[0], k -> new ArrayList<>()).add(wire[1]);
            this.wires.computeIfAbsent(wire[1], k -> new ArrayList<>()).add(wire[0]);
        }
    }
}