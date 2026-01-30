package programmers.lv5.DivideTestRooms;

class Solution {
    // k

    int[] num;
    int[][] links;
    int k;
    int cutCount;

    public int solution(int k, int[] num, int[][] links) {
        this.num = num;
        this.links = links;
        this.k = k;

        int rootId = getRootNodeId(0);
        System.out.println("root id: " + rootId);
        int max = sum(num);
        int min = Math.max(max / k, max(num));

        return search(min, max, rootId);
    }
    //       k
    // 1 2 4 4 5 6
    //   n       x
    //         5
    //   n   x
    //     4
    //     n x
    private int search(int min, int max, int rootId) {
        int mid = (min + max) / 2;
        int answer = 0;

        while (min <= max) {
            mid = (min + max) / 2;

            cutCount = 0;
            dfs(rootId, mid);
            System.out.printf("min: %d, mid: %d, max: %d, cut count: %d\n", min, mid, max, cutCount);

            if (cutCount > k-1) {
                min = mid + 1;
            } else if (cutCount <= k-1) {
                answer = mid;
                max = mid - 1;
            }
        }
        return answer;
    }

    private int dfs(int nodeId, int max) {

        if (nodeId == -1 || cutCount > k-1) {
            return 0;
        }

        int leftSum = dfs(links[nodeId][0], max);
        int rightSum = dfs(links[nodeId][1], max);
        int sum = num[nodeId] + leftSum + rightSum;

        // System.out.printf("num: %d, left: %d, right: %d\n", num[nodeId], dfs(links[nodeId][0], max), dfs(links[nodeId][1], max));
        if (sum > max) {
            cutCount++;
            sum -= Math.max(leftSum, rightSum);

            if (sum > max) {
                cutCount++;
                sum -= Math.min(leftSum, rightSum);
            }
            // System.out.println("cut sum: " + sum);
        }

        return sum;
    }

    private int sum(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result += num;
        }
        return result;
    }

    private int max(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result = Math.max(result, num);
        }
        return result;
    }

    private int getRootNodeId(int startId) {
        for (int i = 0; i < links.length; i++) {
            if (links[i][0] == startId || links[i][1] == startId) {
                return getRootNodeId(i);
            }
        }
        return startId;
    }
}