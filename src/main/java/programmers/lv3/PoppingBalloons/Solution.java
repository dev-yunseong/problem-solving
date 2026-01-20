package programmers.lv3.PoppingBalloons;

import java.util.*;

class Solution {

    private int[] leftMins;
    private int[] rightMins;
    private int[] a;

    public int solution(int[] a) {
        initMins(a);
         System.out.println(Arrays.toString(leftMins));
         System.out.println(Arrays.toString(rightMins));
        this.a = a;

        int answer = 0;

        for (int i = 0; i < a.length; i++) {
             System.out.printf("left min: %d, value: %d, right min: %d\n", getLeftMin(i-1), a[i], getRightMin(i+1));
            if (a[i] != Math.max(Math.max(getLeftMin(i-1), a[i]), getRightMin(i+1))) {
                answer++;

            }
        }

        return answer;
    }

    private int getRightMin(int i) {
        if (i < 0 || i >= a.length) {
            return Integer.MAX_VALUE;
        }

        return rightMins[i];
    }

    private int getLeftMin(int i) {
        if (i < 0 || i >= a.length) {
            return Integer.MAX_VALUE;
        }

        return leftMins[i];
    }

    private void initMins(int[] a) {
        int len = a.length;
        leftMins = new int[len];
        rightMins = new int[len];

        leftMins[0] = a[0];
        rightMins[len-1] = a[len-1];
        for (int i = 1; i < len; i++) {
            int j = len - i - 1;
            leftMins[i] = Math.min(leftMins[i-1], a[i]);
            rightMins[j] = Math.min(rightMins[j+1], a[j]);
        }

    }
}