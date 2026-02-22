package programmers.lv4.DropOneTwoThree;

import java.util.*;

class Solution {
    private Map<Integer, Node> nodes = new HashMap();

    public int[] solution(int[][] edges, int[] target) {

        for (int[] edge : edges) {
            Node parent = get(edge[0]);
            Node child = get(edge[1]);

            parent.children
                    .add(child);
        }

        Node root = get(1);
        root.init();

        int total = sum(target);

        List<Integer> sequence = new ArrayList();
        int[] counts = new int[target.length];

        while (sequence.size() < total) {
            // initial
            int temp = root.get() - 1;
            sequence.add(temp);
            counts[temp] += 1;

            // check
            boolean cont = false;
            for (int i = 0; i < target.length; i++) {
                int temp1 = counts[i];
                if (target[i] < temp1) {
                    return new int[] {-1};
                }

                if (target[i] > temp1 * 3) {
                    cont = true;
                    break;
                }
            }
            if (cont) continue;

            // result
            List<List<Integer>> nums = new ArrayList();

            for (int i = 0; i < target.length; i++) {
                nums.add(new LinkedList());
                int temp1 = counts[i];

                int z = (target[i] - temp1) / 2;
                int y = target[i] - temp1 - 2*z;
                int x = temp1 - y - z;

                for (int j = 0; j < x; j++) {
                    nums.get(i)
                            .add(1);
                }
                for (int j = 0; j < y; j++) {
                    nums.get(i)
                            .add(2);
                }
                for (int j = 0; j < z; j++) {
                    nums.get(i)
                            .add(3);
                }

            }

            int[] answer = new int[sequence.size()];
            for (int i = 0; i < sequence.size(); i++) {
                int index = sequence.get(i);
                int value = nums.get(index)
                        .remove(0);
                answer[i] = value;
            }

            return answer;
        }

        return new int[] {-1};
    }


    private Node get(int id) {
        if (!nodes.containsKey(id)) {
            nodes.put(id, new Node(id));
        }
        return nodes.get(id);
    }

    private int sum(int[] array) {
        int result = 0;

        for (int num : array) {
            result += num;
        }
        return result;
    }

    private class Node {
        int id;
        List<Node> children = new ArrayList();
        int cursor = 0;
        Node(int id) { this.id = id; }

        int get() {
            int size = children.size();
            if (size == 0) return id;

            int result = children.get(cursor).get();
            cursor = (cursor + 1) % size;
            return result;
        }

        void init() {
            Collections.sort(children, (node1, node2) -> node1.id - node2.id);
            for (Node node : children) {
                node.init();
            }
        }
    }
}
