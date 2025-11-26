package boj.RemoteController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(in.nextInt());
        }

        Main solution = new Main();
        int result = solution.solve(n, m, list);
        System.out.println(result);
    }

    public int solve(int n, int m, List<Integer> list) {
        int fromInit = Math.abs(n - 100);
        for (int i = 0; i <= fromInit; i++) {
            if (n-i >= 0 && !isContain(n - i, list)) {
                return Math.min(i + String.valueOf(n-i).length(), fromInit);
            }

            if (!isContain(n + i, list)) {
                return Math.min(i + String.valueOf(n+i).length(), fromInit);
            }
        }
        return fromInit;
    }

    private boolean isContain(int n, List<Integer> list) {
        return String.valueOf(n)
                .chars()
                .map(c -> c - '0')
                .anyMatch(list::contains);
    }
}
