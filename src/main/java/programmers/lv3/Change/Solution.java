package programmers.Change;

import java.util.*;

class Solution {

    private int[][] cacheStore;

    public int solution(int n, int[] money) {
        Arrays.sort(money);

        cacheStore = new int[n+1][];
        for (int i = 0; i <= n; i++) {
            cacheStore[i] = new int[money.length];
            Arrays.fill(cacheStore[i], -1);
        }

        return backTracking(n, money, money.length - 1);
    }

    private int backTracking(int target, int[] money, int step) {
        if (target < 0) return 0;
        int cacheValue = checkCache(target, step);
        if (cacheValue >= 0) {
            return cacheValue;
        }

        int currentMoney = money[step];

        if (step == 0) {
            return (target % currentMoney == 0) ? 1 : 0;
        }

        int nextStep = step - 1;
        int temp = backTracking(target, money, nextStep) + backTracking(target - currentMoney, money, step);

        int result = temp % 1_000_000_007;

        saveCache(target, step, result);

        return result;
    }

    private void saveCache(int target, int step, int value) {
        cacheStore[target][step] = value;
    }

    private int checkCache(int target, int step) {
        return cacheStore[target][step];
    }
}