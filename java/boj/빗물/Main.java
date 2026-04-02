package boj.빗물;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line1 = scanner.nextLine();

        List<Integer> temp = Arrays.stream(line1.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int h = temp.get(0) + 1;
        int w = temp.get(1);

        String line2 = scanner.nextLine();
        List<Integer> map = Arrays.stream(line2.split(" ")).map(Integer::parseInt).map(a -> a + 1).collect(Collectors.toList());

        int result = 0;

        for (int i = h; i >= 0; i--) {

            List<Integer> temp1 = new ArrayList<>();
            for (int j = 0; j < map.size(); j++) {
                if (map.get(j) >= i) {
                    temp1.add(j);
                }
            }

            result += get_빗물_얼마나_고였는지_and_update_map(i, temp1, map);

        }

        System.out.println(result);
    }


    static int get_빗물_얼마나_고였는지_and_update_map(int h, List<Integer> temp1, List<Integer> map) {
        Set<Integer> ignoreSet = new HashSet<>();
        int result = 0;
        for (int i = 0; i < temp1.size() - 1; i++) {
            int left = temp1.get(i);
            int right = temp1.get(i+1);

            for (int j = left; j < right; j++) {
                result += Math.max(h - map.get(j), 0);

                ignoreSet.add(j);
            }

        }

        ignoreSet.stream().sorted((a, b) -> b - a).forEachOrdered(i -> map.remove((int) i));

        return result;
    }
}
