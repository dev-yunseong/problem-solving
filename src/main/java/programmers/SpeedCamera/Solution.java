package programmers.SpeedCamera;

import java.util.*;

class Solution {

    public int solution(int[][] routes) {
        sortSubArray(routes);
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);

        List<Route> routeList = mapToRoutes(routes);

        int answer = 0;

        while (routeList.size() != 0) {
            int mid = routeList.get(0).end;

            routeList.removeIf(route -> route.isOnMid(mid));
            answer++;
        }

        return answer;
    }

    private List<Route> mapToRoutes(int[][] routes) {
        List<Route> result = new LinkedList();

        for (int[] route : routes) {
            result.add(new Route(route[0], route[1]));
        }

        return result;
    }

    private class Route {
        int start, end;

        public Route(int start, int end) {
            this.start = start; this.end = end;
        }

        public boolean isOnMid(int mid) {
            return start <= mid && mid <= end;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Route)) return false;
            Route otherRoute = (Route) other;
            return start == otherRoute.start && end == otherRoute.end;
        }
    }

    private void sortSubArray(int[][] routes) {
        for (int[] route : routes) {
            route[0] = Math.min(route[0], route[1]);
            route[1] = Math.max(route[0], route[1]);
        }
    }
}