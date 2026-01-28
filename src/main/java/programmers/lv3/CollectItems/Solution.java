package programmers.lv3.CollectItems;

import java.util.*;
import java.util.stream.*;

class Solution {

    private int[][] rects;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        this.rects = rectangle;
        int totalLength = 0;
        int semiLength = 0;

        int[] direction = startDirection(
                findOnRects(characterX, characterY),
                characterX, characterY);

        int x = characterX;
        int y = characterY;

        while (true) {
            List<int[]> rects = findOnRects(x, y);
            direction = findDirection(rects, direction, x, y);
            x += direction[0];
            y += direction[1];
            totalLength += 1;

            if (x == itemX && y == itemY) {
                semiLength = totalLength;
            } else if (x == characterX && y == characterY) {
                break;
            }
        }

        return Math.min(totalLength - semiLength, semiLength);
    }

    private int[] startDirection(List<int[]> rects, int x, int y) {

        List<int[]> directions = rects.stream()
                .map(rect -> startDirection(rect, x, y))
                .collect(Collectors.toList());

        if (directions.size() == 1) {
            return directions.get(0);
        }

        int[] temp = new int[]{directions.get(0)[0], directions.get(0)[1]};
        next(temp);
        if (temp[0] == directions.get(1)[0] && temp[1] == directions.get(1)[1]) {
            return directions.get(1);
        }

        return directions.get(0);
    }

    private int[] startDirection(int[] rect, int x, int y) {
        if (x == rect[0] && y < rect[3]) {
            return new int[]{0, 1};
        }

        if (x == rect[2] && y > rect[1]) {
            return new int[]{0, -1};
        }

        if (y == rect[1] && rect[0] < x) {
            return new int[]{-1, 0};
        }

        if (y == rect[3] && x < rect[2]) {
            return new int[]{1, 0};
        }

        return new int[]{1, 0};
    }

    private int[] findDirection(
            List<int[]> rects,
            int[] lastDirection, // [x, y]
            int x, int y) {

        lastDirection[0] *= -1;
        lastDirection[1] *= -1;

        boolean isFinded;

        do {
            isFinded = false;
            next(lastDirection);
            int nx = x + lastDirection[0];
            int ny = y + lastDirection[1];
            for (int[] rect : rects) {
                if (checkOnRect(rect, nx, ny)) {
                    isFinded = true;
                    break;
                }
            }
        } while (!isFinded);

        return lastDirection;
    }

    private void next(int[] lastDirection) {
        // (1, 0) -> (0, -1) -> (-1, 0) -> (0, 1)
        if (lastDirection[0] != 0) {
            lastDirection[1] = -lastDirection[0];
            lastDirection[0] = 0;
        } else {
            lastDirection[0] = lastDirection[1];
            lastDirection[1] = 0;
        }
    }

    private List<int[]> findOnRects(int x, int y) {
        List<int[]> result = new ArrayList();
        for (int[] rect : rects) {
            if (checkOnRect(rect, x, y)) {
                result.add(rect);
            }
        }
        return result;
    }

    private boolean checkOnRect(int[] rect, int x, int y) {
        return (rect[0] <= x && x <= rect[2] && (y == rect[1] || y == rect[3])) ||
                (rect[1] <= y && y <= rect[3] && (x == rect[0] || x == rect[2]));
    }
}