package programmers.lv3.SharedTaxiFare;

class Solution {
    /*
    플로이드 워샬 사용해서
    어디서 분기할지 찾는 방법
    */
    int[][] fares;

    public int solution(int n, int s, int a, int b, int[][] fares) {

        this.fares = initFares(fares, n);
        floyed(n);
        return findAnswer(n, s, a, b);
    }

    private int findAnswer(int n, int s, int a, int b) {
        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int temp = fares[s][i] + fares[i][a] + fares[i][b];
            result = Math.min(result, temp);
        }
        return result;
    }

    private void floyed(int n) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (fares[i][k] + fares[k][j] < fares[i][j]) {
                        fares[i][j] = fares[i][k] + fares[k][j];
                    }
                }
            }
        }
    }

    private int[][] initFares(int[][] fares, int n) {
        int[][] result = new int[n+1][n+1];

        // init
        for (var line : result) {
            for (int i = 1; i <= n; i++) {
                line[i] = 100_000_000;
            }
        }

        for (var fare : fares) {
            result[fare[0]][fare[1]] = fare[2];
            result[fare[1]][fare[0]] = fare[2];
        }

        for (int i = 1; i <= n; i++) {
            result[i][i] = 0;
        }
        return result;
    }
}