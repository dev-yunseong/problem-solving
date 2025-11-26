package programmers.OpenChat;

import java.util.*;
import java.lang.*;

class Solution {

    private Map<String, String> users = new HashMap();
    private List<String> userIds = new ArrayList();
    private List<String> postfixs = new ArrayList();

    public String[] solution(String[] record) {

        for (var re : record) {
            parseRecord(re);
        }

        return createAnswer();
    }

    private String[] createAnswer() {
        var answer = new String[postfixs.size()];
        for (int i = 0; i < postfixs.size(); i++) {
            var temp = users.get(userIds.get(i)) + postfixs.get(i);
            answer[i] = temp;
        }
        return answer;
    }

    private void parseRecord(String record) {
        String[] keywords = record.split(" ");
        if (keywords[0].equals("Enter")) {
            users.put(keywords[1], keywords[2]);
            userIds.add(keywords[1]);
            postfixs.add("님이 들어왔습니다.");
        } else if (keywords[0].equals("Leave")) {
            userIds.add(keywords[1]);
            postfixs.add("님이 나갔습니다.");
        } else if (keywords[0].equals("Change")) {
            users.put(keywords[1], keywords[2]);
        }
    }
}