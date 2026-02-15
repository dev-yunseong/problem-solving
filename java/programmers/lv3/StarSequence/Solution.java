package programmers.lv3.StarSequence;

import java.util.*;

class Solution {

    public int solution(int[] a) {

        if (a.length < 2) return 0;

        Map<Integer, Integer> counter = new HashMap();
        for (int i : a) {
            int temp = counter.getOrDefault(i, 0);
            temp +=1;
            counter.put(i, temp);
        }

        int answer = -1;

        for (var entry : counter.entrySet()) {
            if (entry.getValue() < answer) continue;

            var solution = new SubSolution();
            System.out.printf("num: %d -- ", entry.getKey());
            int temp = solution.solution(a, entry.getKey());
            answer = Math.max(answer, temp);

        }

        return answer * 2;
    }

    private class SubSolution {
        int[] dp;
        int num;
        int[] a;

        int solution(int[] a, int num) {
            dp = new int[a.length];
            this.a = a;
            Arrays.fill(dp, -1);
            this.num = num;
            dp[0] = 0;
            dp[1] = ((a[0] == num || a[1] == num) && a[0] != a[1]) ? 1 : 0;


            for (int i = 2; i < a.length; i++) {
                getAnswer(i);
            }

            System.out.println(Arrays.toString(dp));

            return dp[a.length - 1];
        }

        int getAnswer(int step) {
            if (a[step] == num) {
                if (a[step - 1] != num) {
                    if (dp[step - 1] == dp[step - 2]) {
                        dp[step] = dp[step - 1] + 1;
                    } else {
                        dp[step] = dp[step - 1];
                    }
                } else {
                    dp[step] = dp[step - 1];
                }
            } else {
                if (a[step - 1] == num) {
                    if (dp[step - 1] == dp[step - 2]) {
                        dp[step] = dp[step - 1] + 1;
                    } else {
                        dp[step] = dp[step - 1];
                    }
                } else {
                    dp[step] = dp[step - 1];
                }
            }

            return dp[step];
        }
    }
}