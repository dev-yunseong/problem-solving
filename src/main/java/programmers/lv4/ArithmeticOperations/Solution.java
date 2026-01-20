package programmers.ArithmeticOperations;

import java.util.*;

class Solution {

    private int[][] minDp;
    private boolean[][] minDpSetted;
    private int[][] maxDp;
    private boolean[][] maxDpSetted;

    private int[] numbers;
    private int[] operators;

    public int solution(String arr[]) {
        initArrays(arr);

        return getMax(0, arr.length / 2);
    }

    private int getMin(int i, int j) {

        if (minDpSetted[i][j]) {
             System.out.printf("min[%d, %d]: %d\n", i, j, minDp[i][j]);
            return minDp[i][j];
        }
        int minValue = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int temp;
            if (operators[k] > 0) {
                temp = getMin(i, k) + getMin(k+1, j);
            } else {
                temp = getMin(i, k) - getMax(k+1, j);
            }

            minValue = Math.min(minValue, temp);
        }
        minDpSetted[i][j] = true;
        minDp[i][j] = minValue;
         System.out.printf("min[%d, %d]: %d\n", i, j, minDp[i][j]);
        return minValue;
    }

    private int getMax(int i, int j) {
        if (maxDpSetted[i][j]) {
             System.out.printf("max[%d, %d]: %d\n", i, j, maxDp[i][j]);
            return maxDp[i][j];
        }
        int maxValue = Integer.MIN_VALUE;
        for (int k = i; k < j; k++) {
            int temp;
            if (operators[k] > 0) {
                temp = getMax(i, k) + getMax(k+1, j);
            } else {
                temp = getMax(i, k) - getMin(k+1, j);
            }

            maxValue = Math.max(maxValue, temp);
        }
        maxDpSetted[i][j] = true;
        maxDp[i][j] = maxValue;
         System.out.printf("max[%d, %d]: %d\n", i, j, maxDp[i][j]);
        return maxValue;
    }

    private void initArrays(String[] arr) {
        int n = toDpIndex(arr.length - 1) + 1;
        minDp = new int[n][n];
        maxDp = new int[n][n];
        minDpSetted = new boolean[n][n];
        maxDpSetted = new boolean[n][n];
        fill(minDpSetted, false);
        fill(maxDpSetted, false);
        numbers = new int[n];
        operators = new int[n - 1];

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                numbers[i/2] = Integer.parseInt(arr[i]);
            } else {
                operators[i/2] = arr[i].equals("-") ? -1 : 1;
            }
        }

        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(operators));

        for (int i = 0; i < n; i++) {
            minDp[i][i] = numbers[i];
            maxDp[i][i] = numbers[i];
            minDpSetted[i][i] = true;
            maxDpSetted[i][i] = true;
        }

    }

    private void fill(boolean[][] array, boolean value) {
        for (boolean[] subArray : array) {
            Arrays.fill(subArray, value);
        }
    }

    private int toDpIndex(int i) {
        return i / 2;
    }
}