package programmers.AbusiveUser;

import java.util.*;

class Solution {

    private List<List<Integer>> candidateBannedUserIndexs = new ArrayList();
    private Set<Set<Integer>> candidates = new HashSet();

    public int solution(String[] user_id, String[] banned_id) {
        initCandidates(user_id, banned_id);
        System.out.println(candidateBannedUserIndexs.toString());
        return backtracking(new HashSet(), 0);
    }

    private int backtracking(Set<Integer> candidates, int index) {
        if (index == candidateBannedUserIndexs.size()) {
            if (this.candidates.contains(candidates)) return 0;
            this.candidates.add(new HashSet(candidates));
            return 1;
        }
        int result = 0;

        for (int currentIndex : candidateBannedUserIndexs.get(index)) {
            if (candidates.contains(currentIndex)) continue;

            candidates.add(currentIndex);
            result += backtracking(candidates, index+1);
            candidates.remove(currentIndex);
        }
        return result;
    }

    private void initCandidates(String[] userIds, String[] bannedIds) {
        for (String bannedId : bannedIds) {
            int index1 = candidateBannedUserIndexs.size();
            candidateBannedUserIndexs.add(new ArrayList());
            int index2 = 0;
            for (String userId : userIds) {
                if (canBe(userId, bannedId)) candidateBannedUserIndexs.get(index1).add(index2);
                index2++;
            }
        }
    }

    private boolean canBe(String plainUserId, String bannedUserId) {
        int userIdLength = plainUserId.length();
         System.out.printf("compare %s and %s \n", plainUserId, bannedUserId);
        if (userIdLength != bannedUserId.length()) return false;

        for (int i = 0; i < userIdLength; i++) {
            char banC = bannedUserId.charAt(i);
            if (banC == '*') continue;

            System.out.printf("%s == %s \n", banC, plainUserId.charAt(i));
            if (plainUserId.charAt(i) != banC) return false;
        }

         System.out.println("same");
        return true;
    }
}