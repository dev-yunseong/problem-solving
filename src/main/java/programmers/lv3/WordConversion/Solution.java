package programmers.lv3.WordConversion;

import java.util.*;
import java.util.stream.*;

class Solution {

    private Map<String, List<String>> edges = new HashMap();
    private String[] words;
    public int solution(String begin, String target, String[] words) {
        this.words = words;
        initEdges(words);
        return bfs(begin, target);
    }

    private int bfs(String start, String target) {
        Queue<State> queue = new LinkedList();
        queue.addAll(
                getNeighbors(start).stream()
                        .map(str -> new State(1, str)).collect(Collectors.toList())
        );

        Set<String> visited = new HashSet();
        visited.add(start);

        while (queue.size() > 0) {
            State currentState = queue.remove();
            if (visited.contains(currentState.str)) {
                continue;
            }

            visited.add(currentState.str);

            if (currentState.str.equals(target)) {
                return currentState.step;
            }

            queue.addAll(
                    edges.get(currentState.str)
                            .stream().map(str -> new State(currentState.step + 1, str)).collect(Collectors.toList()));

        }
        return 0;
    }

    private class State {
        int step;
        String str;
        public State(int step, String str) {
            this.step = step; this.str = str;
        }
    }

    private List<String> getNeighbors(String str) {
        return Arrays.stream(words)
                .filter(word -> areSim(str, word))
                .collect(Collectors.toList());
    }

    private void initEdges(String[] words) {
        int num = words.length;
        for (int i = 0; i < num - 1; i++) {
            for (int j = i + 1; j < num; j++) {
                if (areSim(words[i], words[j])) {
                    addEdge(words[i], words[j]);
                    addEdge(words[j], words[i]);
                }
            }
        }
    }

    private void addEdge(String a, String b) {
        if (edges.get(a) == null) {
            edges.put(a, new ArrayList());
        }
        edges.get(a).add(b);
    }

    private boolean areSim(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int num = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                num++;

                if (num >= 2) {
                    return false;
                }
            }
        }

        return num == 1;
    }
}