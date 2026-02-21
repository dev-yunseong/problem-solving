package programmers.lv3.JewelryShopping;

import java.util.*;

class Solution {

    private Map<String, Integer> counter = new HashMap();
    private Set<String> set = new HashSet();
    private int totalGems = 0;

    public int[] solution(String[] gems) {
        init(gems);

        int[] answer = new int[] {1, gems.length};

        int lc = 0;
        int rc = -1;

        while (rc < gems.length - 1) {
            rc++;
            add(gems[rc]);

            while (counter.get(gems[lc]) >= 2) {
                remove(gems[lc]);
                lc++;
            }

            if (set.size() == totalGems) {
                if (answer[1] - answer[0] > rc - lc) {
                    answer[1] = rc + 1;
                    answer[0] = lc + 1;
                }
            }
        }

        return answer;
    }

    private void add(String gem) {
        counter.put(
                gem,
                counter.get(gem) + 1
        );
        set.add(gem);
    }

    private void remove(String gem) {
        int temp = counter.get(gem) - 1;
        counter.put(
                gem,
                temp
        );

        if (temp == 0) {
            set.remove(gem);
        }
    }

    private void init(String[] gems) {
        for (String gem : gems) {
            if (!counter.containsKey(gem)) {
                counter.put(gem, 0);
                totalGems += 1;
            }
        }
    }
}