package programmers.lv4.MinimizeSalesLoss;

import java.util.*;

class Solution {

    private int[] sales;
    private Map<Integer, List<Integer>> childs;
    private Map<Integer, Map<Boolean, Integer>> cache = new HashMap();

    public int solution(int[] sales, int[][] links) {
        this.sales = sales;
        initChilds(links);

        return tracking(1, true);
    }

    private int checkCache(int current, boolean canCurrent) {

        if (cache.get(current) == null) return -1;

        if (cache.get(current).get(canCurrent) == null) return -1;

        return cache.get(current).get(canCurrent);
    }

    private void caching(int current, boolean canCurrent, int value) {
        if (cache.get(current) == null) {
            cache.put(current, new HashMap());
        }
        cache.get(current).put(canCurrent, value);
    }

    private int tracking(int current, boolean canCurrent) {

        int cacheTemp = checkCache(current, canCurrent);
        if (cacheTemp != -1) {
            return cacheTemp;
        }

        // System.out.println("Start Tracking === " + current + " " + canCurrent);
        if (childs.get(current) == null) {
            return 0;
        }

        List<Integer> subResults = new ArrayList();

        for (int childId : childs.get(current)) {
            subResults.add(tracking(childId, true));
        }

        int result = Integer.MAX_VALUE;

        if (canCurrent){
            int sumOfSubs = 0;
            for (int a : subResults) {
                sumOfSubs += a;
            }
            int temp = sale(current) + sumOfSubs;
            result = Math.min(result, temp);
        }

        for (int i = 0; i < childs.get(current).size(); i++) {
            int currentChildId = childs.get(current).get(i);
            int temp = canCurrent ? sale(currentChildId) : 0;
            for (int childId : childs.get(current)) {
                temp += tracking(childId, !canCurrent || currentChildId != childId);
            }
            result = Math.min(result, temp);
        }

        caching(current, canCurrent, result);

        return result;
    }

    private void initChilds(int[][] links) {
        childs = new HashMap();

        for (int[] link : links) {
            if (childs.get(link[0]) == null) {
                childs.put(link[0], new ArrayList());
            }
            childs.get(link[0]).add(link[1]);
        }
    }

    private int sale(int id) {
        return sales[id - 1];
    }
}