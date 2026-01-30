package programmers.lv0.CtrlZ;

class Solution {
    public int solution(String s) {
        String[] values = s.split(" ");
        int answer = 0;
        int lastValue = -1;
        for (var value : values) {
            if (value.equals("Z")) {
                answer -= lastValue;
            } else {
                lastValue = Integer.parseInt(value);
                answer += lastValue;
            }
        }
        return answer;
    }
}
