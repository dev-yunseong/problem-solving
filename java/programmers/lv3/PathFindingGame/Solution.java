package programmers.lv3.PathFindingGame;

import java.util.*;

/**
 # 문제 분석
 - 노드는 (x, y)로 주어짐
 - 모든 노드는 서로 다른 x 값을 가짐
 - 같은 레벨에 있는 노드는 같은 y 좌표를 가짐
 - 자식 노드는 y 값이 항상 부모 노드 보다 작음
 - 임의의 노트 V의 왼쪽 sub tree는 모두 x < V.x
 - 임의의 노트 V의 오른쪽 sub tree는 모두 x > V.x
 **/

class Solution {

    private int maxX;

    private class Node {
        public int id, x, y;

        public List<Node> childs = new ArrayList();

        public int getX() {
            return x;
        }

        public Node(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        public int compareTo(Node other) {
            return y * (maxX + 1) + x - (other.y * (maxX  + 1) + other.x);
        }

        public String toString() {
            return "id: " + id + " (" + x + ", " + y + ")";
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        List<Node> nodes = mapToList(nodeinfo);
        this.maxX = nodes.stream().map(Node::getX).max(Integer::compareTo)
                .get();

        sort(nodes);
        System.out.println("sorted: " + nodes);
        contructTree(nodes);

        int[][] answer = new int[2][nodeinfo.length];
        List<Integer> pre = new ArrayList();
        List<Integer> post = new ArrayList();
        pre.add(nodes.get(0).id);
        getAnswer(pre, post, nodes.get(0));
        post.add(nodes.get(0).id);
        writeAnswer(answer, 0, pre);
        writeAnswer(answer, 1, post);
        return answer;
    }

    private void writeAnswer(int[][] answer, int index, List<Integer> a) {
        int i = 0;
        for (var b : a) {
            answer[index][i++] = b;
        }
    }

    private void getAnswer(List<Integer> pre, List<Integer> post, Node root) {
        if (root == null || root.childs == null || root.childs.size() == 0) {
            return;
        }

        for (Node child : root.childs) {
            if (child == null) {continue;}
            pre.add(child.id);
            getAnswer(pre, post, child);
            post.add(child.id);
        }
    }

    private void contructTree(List<Node> sortedList) {
        if(sortedList.size() == 0) {
            return;
        }

        Node root = sortedList.get(0);
        System.out.println("contructTree root id: " + root.id + " list :" + sortedList);

        List<Node> right = new ArrayList();
        List<Node> left = new ArrayList();
        Node rightRoot = null;
        Node leftRoot = null;

        for (Node node : sortedList.subList(1, sortedList.size())) {
            if (node.x < root.x) {
                if (leftRoot == null)
                    leftRoot = node;
                left.add(node);
            } else {
                if (rightRoot == null)
                    rightRoot = node;
                right.add(node);
            }
        }
        root.childs.add(leftRoot);
        root.childs.add(rightRoot);
        contructTree(left);
        contructTree(right);
    }

    private void sort(List<Node> nodes) {
        Collections.sort(nodes, (n1, n2) -> -n1.compareTo(n2));
    }

    private List<Node> mapToList(int[][] nodeinfo) {
        List<Node> nodes = new ArrayList();
        int id = 0;
        for (int[] nodeIn : nodeinfo) {
            id++;
            Node node = new Node(id, nodeIn[0], nodeIn[1]);
            nodes.add(node);
        }

        return nodes;
    }
}