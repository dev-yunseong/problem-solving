package programmers.FourStepHighNotes;

import java.util.*;

class Solution {

    /*

     *++*++*++ - 17 * 3 + 2

     *++*+*+++
     *++**++++
     *+

     ***(*++)++++++ - 최소 - 3^4+8 = 89

     *++*++*++*++ - 최대

     */

    public static void main(String[] args) {
        System.out.println(new Solution().solution(2147483647));
    }

    int n;
    public int solution(int n) {
        this.n = n;
        int num = getNum(n);
        System.out.println(num);
        return search(List.of('*', '+', '+'), num - 2).size();
    }

    private Set<List<Character>> search(List<Character> cs, int depth) {
         System.out.println(cs);
        if (depth == 0) {
            int answer = 1;
            for (char c : cs) {
                if (c == '*') {
                    answer *= 3;
                } else {
                    answer += 1;
                }
            }
            System.out.println("Check " + cs + " anwser: " + answer);
            if (answer == n) {
                return Set.of(cs);
            }
            return new HashSet();
        }

        Set<List<Character>> result = new HashSet();

        for (int i = 1; i <= cs.size(); i++) {
            var list = insertTo(cs, i);
            result.addAll(search(list, depth - 1));
        }

        return result;
    }

    private List<Character> insertTo(List<Character> cs, int index) {
        var newList = new LinkedList(cs);
        newList.addAll(index, List.of('*', '+', '+'));
        return newList;
    }

    private int getNum(int n) {
        int i = 0;
        do {
            i += 1;
        } while(n >= Math.pow(3, i) + i * 2);
        return i;
    }
}