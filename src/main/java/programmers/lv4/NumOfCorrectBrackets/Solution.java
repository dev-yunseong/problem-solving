package programmers.lv4.NumOfCorrectBrackets;

class Solution {
    
/*
- n = 괄호의 수
- 12, 11
- 123, 121, 112, 111
*/
    
    public int solution(int n) {
        return createCases(1, n - 1);
    }
    
    private int createCases(int lastNum, int n) {
        if (n == 0) {
            return 1;
        }
        
        int cases = 0;

        for (int i = 1; i <= lastNum + 1; i++) {
            cases += createCases(i, n - 1);
        }
        return cases;
    }
}