package programmers.lv2.TriangleSnail;

import java.util.*;

class Solution {

    private int[][] triangle;

    public int[] solution(int n) {
        initTriangle(n);
        int length = n;
        int x = 0;
        int y = 0;
        int start = 1;

        while (n > 0) {
            start = fillTriangle(x, y, start, n);
            x++;
            y+=2;
            n-=3;
            // for (var t : triangle) {
            //     System.out.println(Arrays.toString(t));
            // }
        }

        int totalNum = length*(length+1)/2;
        int[] answer = new int[totalNum];
        int i = 0;
        for (var line : triangle) {
            for (int in : line) {
                answer[i] = in;
                i++;
            }
        }
        return answer;
    }

    private int fillTriangle(int x, int y, int start, int n) {
        int value = start;
        for (int i = 0; i < n-1; i++) {
            triangle[y+i][x] = value;
            value++;
        }
        for (int i = 0; i < n; i++) {
            triangle[y+n-1][x+i] = value;
            value++;
        }

        for (int i = 1; i < n-1; i++) {
            triangle[y+n-1-i][x+n-1-i] = value;
            value++;
        }
        return value;
    }

    private void initTriangle(int n) {
        triangle = new int[n][];
        for (int i = 1; i <= n; i++) {
            triangle[i-1] = new int[i];
        }
    }
}