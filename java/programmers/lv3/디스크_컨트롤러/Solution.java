package programmers.lv3.디스크_컨트롤러;

/*

*/

import java.util.*;

class Solution {

    class Job implements Comparable {
        int id, requestedTime, workTime;

        Job(int id, int[] job) {
            this.id = id;
            this.requestedTime = job[0];
            this.workTime = job[1];
        }

        @Override
        public int compareTo(Object otherObject) {
            Job other = (Job)otherObject;
            if (workTime != other.workTime) return workTime - other.workTime;
            if (requestedTime != other.requestedTime) return requestedTime - other.requestedTime;

            return id - other.id;
        }
    }

    public int solution(int[][] jobs) {
        int count = jobs.length;
        List<Job> jobList = new LinkedList();
        int id = 0;
        for (int[] job : jobs) {
            jobList.add(new Job(id, job));
            id++;
        }
        Collections.sort(jobList, (a, b) -> a.requestedTime - b.requestedTime);

        Queue<Job> scheduler = new PriorityQueue();

        int sum = 0;
        int time = jobList.get(0).requestedTime;

        while (jobList.size() != 0 || scheduler.size() != 0) {
            while (!jobList.isEmpty() && jobList.get(0).requestedTime <= time) {
                Job job = jobList.remove(0);
                scheduler.add(job);
            }

            if (scheduler.size() == 0) {
                time = jobList.get(0).requestedTime;
                continue;
            }

            Job job = scheduler.remove();
            time += job.workTime;

            sum += (time - job.requestedTime);
        }
        return sum / count;
    }
}