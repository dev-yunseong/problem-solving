package programmers.DesertIslandTrip;

import java.util.*;

class Solution {

    /*
    - X는 바다
    - 1~9 육지
    - 숫자는 식량 -> 합
    - 각 섬마다 숫자의 합을 오름차순으로 리턴하라.
    */
    private int MAX_X;
    private int MAX_Y;
    private int[] deltaX = {1, -1, 0, 0};
    private int[] deltaY = {0, 0, -1, 1};

    private class Cell {
        public int x, y;
        public int value;

        public Cell(int x, int y, int value) {
            this.x = x; this.y = y; this.value = value;
        }

        public boolean isSea() {
            return value == -1;
        }

        @Override
        public int hashCode() {
            return x * MAX_Y + y;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Cell) {
                Cell cell = (Cell) other;
                return x == cell.x && y == cell.y;
            }
            return false;
        }
    }

    private Set<Cell> cells = new HashSet();

    public int[] solution(String[] maps) {
        MAX_Y = maps.length;
        MAX_X = maps[0].length();
        convertToCells(maps, cells);
        List<Integer> answer = new ArrayList();

        while (cells.size() > 0) {
            Cell cell = cells.stream().findAny().get();
            cells.remove(cell);
            answer.add(bfs(cell, maps));
        }
        Collections.sort(answer);
        System.out.println(answer);
        int[] a = new int[answer.size()];
        if (answer.size() == 0) {
            return new int[]{-1};
        }
        for (int i = 0; i < answer.size(); i++) {
            a[i] = answer.get(i);
        }

        return a;
    }

    private int bfs(Cell start, String[] maps) {
        Set<Cell> visited = new HashSet();
        Queue<Cell> willVisit = new LinkedList();
        willVisit.add(start);
        int result = 0;

        while (willVisit.size() > 0) {
            Cell current = willVisit.remove();
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);
            this.cells.remove(current);
            result += current.value;
            for (Cell n : getNs(current, maps)) {
                if (visited.contains(n)) {
                    continue;
                }
                willVisit.add(n);
            }
        }
        return result;
    }

    private List<Cell> getNs(Cell start, String[] maps) {
        List<Cell> cells = new ArrayList();
        for (int i = 0; i < 4; i++) {
            int x = start.x + deltaX[i];
            int y = start.y + deltaY[i];
            if (0 <= x && x < MAX_X && 0 <= y && y < MAX_Y) {
                char c = maps[y].charAt(x);
                if (c != 'X') {
                    Cell cell = new Cell(x, y, c - '0');
                    cells.add(cell);
                }
            }
            continue;
        }
        return cells;
    }

    private void convertToCells(String[] maps, Set<Cell> cells) {
        for (int y = 0; y < MAX_Y; y++) {
            for (int x = 0; x < MAX_X; x++) {
                char c = maps[y].charAt(x);
                if (c != 'X') {
                    Cell cell = new Cell(x, y, c - '0');
                    cells.add(cell);
                }
            }
        }
    }
}