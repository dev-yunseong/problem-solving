package programmers.DoubleEndedPriorityQueue;

import java.util.*;

class Solution {

    List<Integer> queue = new ArrayList();

    public int[] solution(String[] operations) {

        for (String operation : operations) {
            handleOperation(operation);
             System.out.println(queue.toString());
        }

        if (queue.size() == 0) {
            return new int[]{0, 0};
        }
        return new int[]{queue.get(queue.size()-1), queue.get(0)};
    }

    private void handleOperation(String operation) {
        char o = operation.charAt(0);
        switch(o) {
            case 'I':
                System.out.println("insert");
                insert(Integer.parseInt(operation.substring(2)));
                break;
            case 'D':
                 System.out.println("delete");
                if (queue.isEmpty()) return;
                if (operation.equals("D 1")) {
                    queue.remove(queue.size() - 1);
                } else {
                    queue.remove(0);
                }
                break;
        }
    }

    private void insert(int num) {
        int index = Collections.binarySearch(queue, num);
        if (index < 0) {
            index = - (index + 1);
        }

        if (index == queue.size()) {
            queue.add(num);
        } else {
            queue.add(index, num);
        }
    }
}