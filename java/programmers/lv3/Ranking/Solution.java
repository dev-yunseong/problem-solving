package programmers.lv3.Ranking;

import java.util.*;
import java.util.stream.*;

class Solution {

    private Map<Integer, Item> items = new HashMap();
    private int n;

    public int solution(int n, int[][] results) {
        this.n = n;
        for (int[] result : results) {
            Item winner = getItemAndPut(result[0]);
            Item loser = getItemAndPut(result[1]);

            loser.applyWinner(winner);
            winner.applyLoser(loser);
        }

        // System.out.println(items.values().toString());

        int answer = 0;
        for (Item item : items.values()) {
            if (item.isClear()) {
                answer += 1;
            }
        }
        return answer;
    }

    private Item getItemAndPut(int key) {
        Item item = items.get(key);
        if (item == null) {
            items.put(key, new Item(key));
        }
        return items.get(key);
    }

    private class Item {
        int id;
        List<Item> superItems = new ArrayList();
        List<Item> subItems = new ArrayList();
        Set<Integer> superIds;
        Set<Integer> subIds;

        boolean isClear() {
            return getSuperIds().size() + getSubIds().size() + 1 == n;
        }

        Set<Integer> getSuperIds() {
            if (superIds != null) {
                return superIds;
            }

            superIds = superItems.stream()
                    .map(item -> item.id)
                    .collect(Collectors.toSet());

            for (Item item : superItems) {
                superIds.addAll(item.getSuperIds());
            }
            return superIds;
        }

        Set<Integer> getSubIds() {
            if (subIds != null) {
                return subIds;
            }

            subIds = subItems.stream()
                    .map(item -> item.id)
                    .collect(Collectors.toSet());

            for (Item item : subItems) {
                subIds.addAll(item.getSubIds());
            }
            return subIds;
        }

        void applyWinner(Item winner) {
            superItems.add(winner);
        }

        void applyLoser(Item loser) {
            subItems.add(loser);
        }

        Item(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return String.format("{id: %d, super: %s, sub: %s}", id, getSuperIds().toString(), getSubIds().toString());
        }
    }
}