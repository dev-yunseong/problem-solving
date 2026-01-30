package programmers.lv3.FillingPuzzlePieces;

import java.util.*;
import java.util.stream.*;

class Solution {

    private List<Block> blanks;
    private List<Block> blocks;

    private int[] deltaX = new int[]{0, 0, -1, 1};
    private int[] deltaY = new int[]{1, -1, 0, 0};

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        blanks = findBlocks(game_board, 0);
        blocks = findBlocks(table, 1);

        for (Block blank : blanks) {
            for (Block block : blocks) {
                if (block.equals(blank)) {
                    answer += block.size();
                    blocks.remove(block);
                    break;
                }
            }
        }
        return answer;
    }

    private List<Block> findBlocks(int[][] table, int target) {
        List<Cell> cellList = findCells(table, target);
        Queue<Cell> cells = new LinkedList(cellList);
        var map = mapListToMap(cellList);
        List<Block> blocks = new ArrayList();

        while (cells.size() != 0) {
            Cell cell = cells.remove();

            Block block = findBlock(map, cell);
            blocks.add(block);

            cells.removeAll(block.cells);
        }

        return blocks;
    }

    private Block findBlock(Map<List<Integer>, Cell> map, Cell start) {
        Queue<Cell> cells = new LinkedList();
        cells.add(start);
        Set<Cell> visitedCells = new HashSet();

        while (cells.size() != 0) {
            Cell cell = cells.remove();
            if (visitedCells.contains(cell)) continue;
            visitedCells.add(cell);

            for (int i = 0; i < 4; i++) {
                Cell neigbor = map.get(List.of(deltaX[i] + cell.x, deltaY[i] + cell.y));
                if (neigbor == null) continue;

                cells.add(neigbor);
            }
        }

        return new Block(visitedCells);
    }

    private Map<List<Integer>, Cell> mapListToMap(List<Cell> cells) {
        Map<List<Integer>, Cell> map = new HashMap();

        for (Cell cell : cells) {
            map.put(List.of(cell.x, cell.y), cell);
        }

        return map;
    }

    private List<Cell> findCells(int[][] table, int target) {
        List<Cell> result = new ArrayList();

        for (int y = 0; y < table.length; y++) {
            for (int x = 0; x < table[0].length; x++) {
                if (table[y][x] != target) continue;

                result.add(new Cell(x, y));
            }
        }
        return result;
    }

    private class Block {

        public List<Cell> cells;

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Block)) return false;

            Block block = (Block) other;
            if (block.size() != size()) return false;

            for (int i = 0; i < 4; i++) {
                if (equalCells(block.getCells(i))) {
                    return true;
                }
            }
            return false;
        }

        public boolean equalCells(List<Cell> others) {
            Cell ourBase = cells.stream().min((c1, c2) -> c1.x * 50 + c1.y - c2.x * 50 - c2.y).get();
            Cell otherBase = others.stream().min((c1, c2) -> c1.x * 50 + c1.y - c2.x * 50 - c2.y).get();

            int diffX = otherBase.x - ourBase.x;
            int diffY = otherBase.y - ourBase.y;

            for (Cell cell : cells) {
                int x = diffX + cell.x;
                int y = diffY + cell.y;
                boolean isExist = others.stream().anyMatch(c -> c.x == x && c.y == y);

                if (!isExist) {
                    return false;
                }
            }
            return true;
        }

        public List<Cell> getCells(int ver) {
            return cells.stream()
                    .map(cell ->
                            switch(ver) {
                                case 0 -> cell;
                                case 1 -> new Cell(cell.y, -cell.x);
                                case 2 -> new Cell(-cell.y, cell.x);
                                case 3 -> new Cell(-cell.x, -cell.y);
                                default -> cell;
                            }).collect(Collectors.toList());
        }

        @Override
        public String toString() {
            return "Block: " + cells.toString();
        }

        public int size() {
            return cells.size();
        }

        public Block(Set<Cell> visitedCells) {
            cells = new ArrayList(visitedCells);
        }
    }

    private class Cell {
        public int x, y;

        public Cell(int x, int y) {
            this.x = x; this.y = y;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }

        @Override
        public int hashCode() {
            return x * 31 + y;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Cell)) return false;
            Cell cell = (Cell) other;

            return x == cell.x && y == cell.y;
        }
    }
}