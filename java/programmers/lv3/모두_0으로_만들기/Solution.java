package programmers.lv3.모두_0으로_만들기;

import java.util.*;

class Solution {

    class Node {
        int id;
        long value;

        Set<Node> neighbers = new HashSet();

        Node(int id, int value) {this.id = id; this.value = value;}

        boolean isLeaf() {
            return neighbers.size() <= 1;
        }
    }

    private Map<Integer, Node> nodes = new HashMap();

    public long solution(int[] a, int[][] edges) {

        if (isM(a)) return -1;

        // initialize
        for (int i = 0; i < a.length; i++) {
            nodes.put(i, new Node(i, a[i]));
        }

        for (int[] edge : edges) {
            Node n1 = nodes.get(edge[0]);
            Node n2 = nodes.get(edge[1]);
            n1.neighbers.add(n2);
            n2.neighbers.add(n1);
        }

        // 1. get leaf nodes
        Queue<Node> leafNodes = getLeafNodes();
        long answer = 0;

        while (leafNodes.size() >= 1) {

            Node node = leafNodes.remove();
            // System.out.printf("id: %d, value: %d\n", node.id, node.value);
            if (node.neighbers.size() == 0) {
                if (node.value == 0) {
                    return answer;
                } else {
                    return -1;
                }
            }

            Node pNode = node.neighbers.stream().findAny().get();

            pNode.value += node.value;
            answer += Math.abs(node.value);
            pNode.neighbers.remove(node);
            if (pNode.isLeaf()) {
                leafNodes.add(pNode);
            }
        }

        return answer;
    }

    private boolean isM(int[] a) {
        int sum = 0;
        for (int i : a) {
            sum += i;
        }
        return sum != 0;
    }

    private LinkedList<Node> getLeafNodes() {
        LinkedList<Node> result = new LinkedList();
        for (Node node : nodes.values()) {
            if (node.isLeaf()) {
                result.add(node);
            }
        }
        return result;
    }
}