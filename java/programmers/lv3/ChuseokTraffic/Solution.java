package programmers.lv3.ChuseokTraffic;

import java.util.*;

class Solution {

    Queue<Float> startTimes;
    Queue<Float> endTimes;

    public int solution(String[] lines) {
        initTimes(lines);

        return search();
    }

    private int search() {
        int maxValue = 0;
        int currentValue = 0;

        while (!startTimes.isEmpty() || !endTimes.isEmpty()) {

            float startTime = Optional.ofNullable(startTimes.peek()).orElse(Float.MAX_VALUE) - 0.999f;
            float endTime = Optional.ofNullable(endTimes.peek()).orElse(Float.MAX_VALUE);

            if (startTime < endTime) {
                currentValue++;
                // System.out.printf("Start at %.3f\n", startTime);
                startTimes.remove();
            } else if (endTime < startTime) {
                currentValue--;
                // System.out.printf("End at %.3f\n", endTime);
                endTimes.remove();
            } else {
                startTimes.remove();
                currentValue++;
                maxValue = Math.max(maxValue, currentValue);
                endTimes.remove();
                currentValue--;
            }


            maxValue = Math.max(maxValue, currentValue);
        }

        return maxValue;
    }

    private void initTimes(String[] lines) {
        var startTimeList = new ArrayList<Float>(lines.length);
        var endTimeList = new ArrayList<Float>(lines.length);

        for (String line : lines) {
            applyLine(line, startTimeList, endTimeList);
        }

        Collections.sort(startTimeList);
        Collections.sort(endTimeList);

        startTimes = new LinkedList(startTimeList);
        endTimes = new LinkedList(endTimeList);
    }

    private void applyLine(String line, List<Float> startTimeList, List<Float> endTimeList) {
        int hourStart = 11;
        int hourEnd = 13;
        int minStart = 14;
        int minEnd = 16;
        int secStart = 17;
        int secEnd = 23;
        int duraStart = 24;
        int duraEnd = line.length() - 1;

        int hour = Integer.parseInt(line.substring(hourStart, hourEnd));
        int min = Integer.parseInt(line.substring(minStart, minEnd));
        float sec = Float.parseFloat(line.substring(secStart, secEnd));
        float duration = Math.min(Float.parseFloat(line.substring(duraStart, duraEnd)), 3.0f);

        // System.out.printf("%s: {hour: %d, min: %d, sec: %.3f, dura: %.3f}", line, hour, min, sec, duration);

        float endTime = hour * 60 * 60 + min * 60 + sec;
        float startTime = endTime - duration + 0.001f;

        startTimeList.add(startTime);
        endTimeList.add(endTime);
    }
}