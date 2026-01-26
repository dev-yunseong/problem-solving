package programmers.lv4.PurchaseCookies;

class Solution {

    int[] cookieSums;

    public int solution(int[] cookie) {
        initCookieSums(cookie);

        int answer = 0;

        for (int i = 0; i < cookie.length - 1; i++) {
            for (int j = i+1; j < cookie.length; j++) {

                if (getSum(i, j) <= answer / 2) continue;

                int l = i;
                int r = j;

                int maxM = r - 1;
                int minM = l;

                while (minM <= maxM) {
                    int m = (minM+maxM) / 2;
                    int sum1 = getSum(l, m);
                    int sum2 = getSum(m+1, r);
                    if (sum1 == sum2) {
                        answer = Math.max(answer, sum1);
                        break;
                    }

                    if (sum1 > sum2) {
                        maxM = m-1;
                    } else {
                        minM = m+1;
                    }
                }
            }
        }

        return answer;
    }

    private int getSum(int start, int end) {
        if (start == 0) {
            return cookieSums[end];
        }
        return cookieSums[end] - cookieSums[start - 1];
    }

    private void initCookieSums(int[] cookie) {
        cookieSums = new int[cookie.length];
        cookieSums[0] = cookie[0];
        for (int i = 1; i < cookie.length; i++) {
            cookieSums[i] = cookieSums[i-1] + cookie[i];
        }
    }
}