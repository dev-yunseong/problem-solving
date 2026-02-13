package programmers.lv3.Immigration;

class Solution {

    public long solution(int n, int[] times) {
        return parametric_search(n, times);
    }

    private long parametric_search(int n, int[] times) {
        long min = 0;
        long max = Long.MAX_VALUE;

        while (min < max) {
            long mid = min / 2 + max / 2;
            boolean isPossible = check(mid, n, times);

            if (isPossible) {
                // 가능할 때, 가능은 함
                max = mid;
            } else {
                // 불가능 할 때, answer는 mid보다 작음
                min = mid + 1;
            }
        }
        return max;
    }


    private boolean check(long answer, long n, int[] times) {
        for (int time : times) {
            n -= answer / time;
            if (n <= 0) return true;
        }
        return false;
    }
}