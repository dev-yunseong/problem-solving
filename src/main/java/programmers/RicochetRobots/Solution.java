package programmers.RicochetRobots;

import java.util.*;

class Solution {

    public int solution(String[] board) {
        Board b = new Board(board);
        Pair start = b.getStart();

        return bfs(start, b);
    }

    private Pair[] directions = {new Pair(1, 0), new Pair(-1, 0), new Pair(0, 1), new Pair(0, -1)};

    public int bfs(Pair start, Board board) {
        PriorityQueue<Case> queue = new PriorityQueue();
        queue.add(new Case(start, 0));
        HashSet<Pair> visited = new HashSet();

        while (queue.size() > 0) {
            Case current = queue.remove();
            // System.out.println(" " + current.pair.x + ", " + current.pair.y + ": " + board.get(current.pair));
            if (visited.contains(current.pair)) {
                continue;
            }
            visited.add(current.pair);

            if (board.get(current.pair) == 'G') {
                return current.cost;
            }

            List<Pair> neighbors = getNeighbors(current.pair, board);
            // System.out.println(neighbors);
            for (var neighbor : neighbors) {
                queue.add(new Case(neighbor, current.cost + 1));
            }
        }

        return -1;
    }

    private List<Pair> getNeighbors(Pair start, Board board) {
        List<Pair> result = new ArrayList();
        for (var direction : directions) {
            Pair n = board.getNeighbor(start, direction);
            if (!n.equals(start)){
                result.add(n);
            }
        }
        return result;
    }

    private class Pair {
        public int x, y;
        public Pair(int x, int y) {
            this.x = x; this.y = y;
        }

        @Override
        public int hashCode() {
            return x*x + y;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Pair) {
                Pair op = (Pair) other;
                return op.x == x && op.y == y;
            }
            return false;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }

    private class Board {
        public char[][] values;

        public Pair getStart() {
            for (int y = 0; y < values.length; y++) {
                for (int x = 0; x < values[0].length; x++) {
                    if (get(x, y) == 'R') {
                        System.out.println("start: " + x + ", " + y);
                        return new Pair(x, y);
                    }

                }
            }
            return null;
        }

        public Pair getNeighbor(Pair start, Pair direction) {
            Pair currentPair = new Pair(start.x, start.y);
            char current = '.';
            do {
                currentPair.x += direction.x;
                currentPair.y += direction.y;
                try {
                    current = get(currentPair);
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }

            } while (current != 'D');
            currentPair.x -= direction.x;
            currentPair.y -= direction.y;
            return currentPair;
        }

        public char get(Pair pair) {
            return get(pair.x, pair.y);
        }

        public char get(int x, int y) {
            return values[y][x];
        }

        public Board(String[] board) {
            values = new char[board.length][board[0].length()];
            int i = 0;
            for (var string : board) {
                int j = 0;
                for (var c : string.toCharArray()) {
                    values[i][j] = c;
                    j++;
                }
                i++;
            }
        }
    }

    public class Case implements Comparable  {
        public Pair pair;
        public int cost;
        public Case(Pair pair, int cost) {
            this.pair = pair; this.cost = cost;
        }
        @Override
        public int compareTo(Object other) {
            Case oc = (Case) other;
            return cost - oc.cost;
        }
    }
}