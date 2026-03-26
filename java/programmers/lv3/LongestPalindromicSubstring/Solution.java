package programmers.lv3.LongestPalindromicSubstring;

class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            int temp = getAnswerFromSeed(s, i, i);
            answer = Math.max(answer, temp);
        }
        
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                continue;
            }
            
            int temp = getAnswerFromSeed(s, i, i+1);
            answer = Math.max(answer, temp);
        }
        
        return answer;
    }
    
    private int getAnswerFromSeed(String s, int seedLeft, int seedRight) {
        int nextLeft = seedLeft - 1;
        int nextRight = seedRight + 1;

        if (nextLeft < 0 || nextRight >= s.length()) {
            return seedRight - seedLeft + 1;
        }
        
        if (s.charAt(nextLeft) == s.charAt(nextRight)) {
            return getAnswerFromSeed(s, nextLeft, nextRight);
        }
        return seedRight - seedLeft + 1;
        
    }
}