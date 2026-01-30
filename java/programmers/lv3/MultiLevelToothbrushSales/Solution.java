package programmers.lv3.MultiLevelToothbrushSales;

import java.util.*;

class Solution {

    private Map<String, Node> nodes = new HashMap();

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        initNodes(enroll, referral);
        giveToSellers(seller, amount);
        return genAnswer(enroll);
    }

    private int[] genAnswer(String[] enroll) {
        int[] answer = new int[enroll.length];

        for (int i = 0; i < enroll.length; i++) {
            answer[i] = nodes.get(enroll[i]).돈;
        }
        return answer;
    }

    private void giveToSellers(String[] seller, int[] amount) {
        for (int i = 0; i < seller.length; i++) {
            nodes.get(seller[i]).give돈(amount[i] * 100);
        }
    }

    private void initNodes(String[] enroll, String[] referral) {
        List<String> enrollList = new LinkedList(List.of(enroll));
        List<String> referralList = new LinkedList(List.of(referral));

        String rootName = "-";
        Node root = new Node(rootName);
        nodes.put(rootName, root);

        for (int i = 0; i < enroll.length; i++) {
            Node temp = new Node(enroll[i], nodes.get(referral[i]));
            nodes.put(enroll[i], temp);
        }
    }

    private class Node {
        String name;

        Node parent;
        List<Node> children = new ArrayList();

        int 돈 = 0;

        void give돈(int 돈) {
            int 줄돈 = 돈 / 10;
            this.돈 += 돈 - 줄돈;
            if (parent == null) return;

            parent.give돈(줄돈);
        }

        Node(String name) {
            this.name = name;
        }

        Node(String name, Node parent) {
            this.name = name;
            this.parent = parent;
            parent.children.add(this);
        }
    }
}

