package programmers.Autocomplete;

import java.util.*;

class Solution {

    public int solution(String[] words) {
        List<String> wordList = List.of(words);

        return recursiveSolve(wordList, 1);
    }

    private int recursiveSolve(List<String> wordss, int step) {
        var wordsList = check(wordss);
        int result = 0;
        for (List<String> words : wordsList) {
            if (words.size() == 1) {
                result += step;
                continue;
            }

            for (String word : words) {
                if (word.length() == 0) {
                    result += step;
                    words.remove(word);
                    break;
                }
            }

            result += recursiveSolve(words, step + 1);
        }
        return result;
    }

    private Collection<List<String>> check(List<String> words) {
        Map<Character, List<String>> dict = new HashMap();
        for (String word : words) {
            char key = word.charAt(0);
            if (dict.get(key) == null) {
                dict.put(key, new ArrayList());
            }
            dict.get(key).add(word.substring(1));
        }
        return dict.values();
    }
}