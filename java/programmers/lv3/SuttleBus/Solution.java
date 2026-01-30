package programmers.lv3.SuttleBus;

import java.util.*;

class Solution {

    private List<TimeData> timeDatas;
    private String[] timetable;
    private int n, t, m;

    public String solution(int n, int t, int m, String[] timetable) {
        this.n = n; this.t = t; this.m = m;
        this.timetable = timetable;
        Arrays.sort(timetable);
        initTimeDatas();
        System.out.println(timeDatas);
        return timeDatas.get(timeDatas.size() - 1).whenCanRide();
    }

    private void initTimeDatas() {
        timeDatas = new ArrayList(n);
        for (int i = 0; i < n; i++) timeDatas.add(new TimeData(i));

        int i = 0;
        for (TimeData timeData : timeDatas) {

            for (; i < timetable.length; i++) {
                if (!timeData.isRideable(timetable[i])) {
                    break;
                }
                timeData.add(timetable[i]);
            }
        }
    }

    private class TimeData {

        public TimeData(int index) {
            int temp = index * t;
            hour = 9 + (temp / 60);
            min = temp % 60;
            timeInString = String.format("%02d:%02d", hour, min);
        }

        public int hour, min;
        public String timeInString;
        public List<String> times = new ArrayList();

        public String whenCanRide() {
            if (times.size() < m || times.size() == 0) {
                return timeInString;
            }

            String lastTime = times.get(times.size() - 1);
            int lastH = Integer.parseInt(lastTime.substring(0, 2));
            int lastM = Integer.parseInt(lastTime.substring(3, 5));

            lastM--;
            if (lastM == -1) {
                lastH--;
                lastM = 59;
            }
            return String.format("%02d:%02d", lastH, lastM);
        }

        public boolean isRideable(String time) {
            if (times.size() >= m) return false;

            return timeInString.compareTo(time) >= 0;
        }

        public void add(String time) {
            times.add(time);
        }

        @Override
        public String toString() {
            return  "at " + timeInString + " " + times.toString() + "\n";
        }
    }
}