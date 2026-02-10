package programmers.lv3.RepresentableBinaryTree;

import java.util.*;

class Solution {

    private class Key {
        long longValue;
        int intValue;

        Key(long longValue, int intValue) {
            this.longValue = longValue; this.intValue = intValue;
        }

        @Override
        public int hashCode() {
            return ((int) longValue / 7) + intValue;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Key)) return false;
            Key key = (Key) other;
            return key.intValue == intValue && key.longValue == longValue;
        }
    }

    Map<Key, Boolean> dp = new HashMap();

    public int[] solution(long[] numbers) {
        initDp();
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            int length = getTreeLength(numbers[i]);
            answer[i] = isValid(numbers[i], length) ? 1 : 0;
        }

        return answer;
    }

    private boolean isValid(long number, int length) {
        if (number == 0) return true;
        if (length == 1) return true;
        Key key = new Key(number, length);
        if (dp.containsKey(key)) return dp.get(key);

        long[] numbers = split(number, length);

        boolean result;
        if (numbers[1] == 0) {
            result = false;
        } else {
            int subLength = (length-1)/2;
            result = isValid(numbers[0], subLength) && isValid(numbers[2], subLength);
        }

        dp.put(key, result);
        return result;
    }

    private void initDp() {
        dp.put(new Key(1L, 1), true);
        dp.put(new Key(0L, 1), true);
    }

    private long[] split(long number, int length) {
         System.out.printf("split: %d = ", number);
        int subLength = (length-1)/2;

        long mask = 0;
        for (int i = 0; i < subLength; i++) {
            mask = mask << 1;
            mask |= 1;
        }

        long right = mask & number;
        number = (number >> subLength);
        long mid = number & 1;
        long left = number >> 1;


         System.out.printf("%d(%d) %d(%d) %d(%d)\n", left, left << (subLength + 1), mid, mid << subLength, right, right);

        return new long[] {left, mid, right};
    }

    private int getTreeLength(long number) {
        int length = getLength(number);
        int treeLength = 1;
        int i = 1;
        while (length > treeLength) {
            i *= 2;
            treeLength += i;
        }
        return treeLength;
    }

    private int getLength(long number) {
        int length = 0;
        while (number != 0) {
            length++;
            number = number >> 1;
        }
        return length;
    }
}