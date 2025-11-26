package programmers.AntArmy;

class Solution {
    public int solution(int hp) {
        int answer = (int) hp / 5;
        hp = hp % 5;
        answer += hp % 3 + (int) hp / 3;
        return answer;
    }
}