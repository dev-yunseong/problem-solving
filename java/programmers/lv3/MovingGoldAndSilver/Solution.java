package programmers.lv3.MovingGoldAndSilver;

class Solution {
    // bineary
    // 1, 2, 3, 4, 5

    public long solution(
            int a, // 필요한 금
            int b, // 필요한 은
            int[] g, // 보유 금
            int[] s, // 보유 은
            int[] w, // 트럭 용량
            int[] t // 편도 이동 시간
    ) {
        long min = 0;
        long max = 1_000_000_000_000_000L;

        long answer = -1;

        while (min <= max) {
            long mid = (min + max) / 2L;
            System.out.print(" " + min +" ~ " + max +" : ");
            System.out.print(mid);
            if (check(a, b, g, s, w, t, mid)) {
                answer = mid;
                max = mid - 1;
                System.out.println(" : true");
                continue;
            } else {
                min = mid + 1;
                System.out.println(" : false");
                continue;
            }
        }

        return answer;
    }

    private int min(int[] arr) {
        int result = Integer.MAX_VALUE;
        for (int i : arr) {
            result = Math.min(result, i);
        }
        return result;
    }


    private int max(int[] arr) {
        int result = -1;
        for (int i : arr) {
            result = Math.max(result, i);
        }
        return result;
    }

    // 다중 자원 흐름
    // 금만 옮겼을 때 >= a
    // 은만 옮겼을 때 >= b
    // 금+은 옮겼을 때 >= a+b
    // <=> true
    private boolean check(
            int a, // 필요한 금
            int b, // 필요한 은
            int[] g, // 보유 금
            int[] s, // 보유 은
            int[] w, // 트럭 용량
            int[] t, // 편도 이동 시간
            long maxT
    ) {
        long onlyGold = 0;
        long onlySilver = 0;
        long dontCare = 0;

        for (int i = 0; i < g.length; i++) {
            long totalMove = w[i] * ((maxT - t[i]) / (2 * t[i]) + 1);

            onlyGold += Math.min(g[i], totalMove);
            onlySilver += Math.min(s[i], totalMove);
            dontCare += Math.min(g[i] + s[i], totalMove);
        }

        return onlyGold >= a && onlySilver >= b && dontCare >= a+b;
    }
}
