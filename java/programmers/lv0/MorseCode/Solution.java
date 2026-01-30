package programmers.lv0.MorseCode;

import java.util.*;

class Solution {

    public String solution(String letter) {
        String answer = "";

        var map = initMap();

        for (String morse : letter.split(" ")) {
            answer += map.get(morse);
        }

        return answer;
    }

    private Map<String, Character> initMap() {
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        int index = 0;
        Map<String, Character> map = new HashMap<>();

        for (char i = 'a'; i <= 'z'; i++) {
            map.put(morse[index], i);
            index++;
        }

        return map;
    }
}