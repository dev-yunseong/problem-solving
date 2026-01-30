package programmers.lv5.DivideTestRooms;

import java.util.*;
import java.util.stream.Collectors;

// 1. 간선을 중복 없이 자름
// - 간선을 줄을 세움
// -
// 2. 각 그룹의 합을 빠르게 구함

// 정확성 - 100
// 효율성 - 0
class Solution1 {

    int answer = 1000000;
    int k;
    int[] num;
    int[] flatedlinks;

    private Node root;
    private Map<Integer, Node> nodes = new HashMap();

    public class Node {

        public int id;
        public int num;

        public Node parent;
        public Node left;
        public boolean ll = false;
        public Node right;
        public boolean lr = false;

        public List<Node> getRoots() {
            var roots = new ArrayList();
            if (parent == null) {
                roots.add(this);
            }

            if (ll && left != null) {
                roots.add(left);
            }

            if (lr && right != null) {
                roots.add(right);
            }

            if (left != null)
                roots.addAll(left.getRoots());
            if (right != null)
                roots.addAll(right.getRoots());

            return roots;
        }

        public int getNumSum() {
            int sum = this.num;
            if (!ll && left != null) {
                sum += left.getNumSum();
            }
            if (!lr && right != null) {
                sum += right.getNumSum();
            }
            return sum;
        }

        public Node getRoot() {
            if (parent == null) {
                return this;
            }
            return parent.getRoot();
        }

        public Node getLeft() {
            if (ll) {
                return null;
            }
            return left;
        }

        public Node getRight() {
            if (lr) {
                return null;
            }
            return right;
        }

        public void clearCut() {
            ll = false;
            lr = false;
        }

        public void cutLeft() {
            ll = true;
        }

        public void cutRight() {
            lr = true;
        }
    }

    public int solution(int k, int[] num, int[][] links) {
        this.flatedlinks = flatArray(links);
        this.k = k;
        this.num = num;
        constructNode(num, links);

        recursiveLoop(new ArrayList(), k);

        return answer;
    }

    private void solve(List<Integer> cuts) {
        nodes.values().stream().forEach(Node::clearCut);
        cutNodes(cuts);
        List<Node> roots = root.getRoots();

        var sums = roots
                .stream()
                .map(Node::getNumSum)
                .collect(Collectors.toList());
        // System.out.println("sums" + sums);

        var m = Collections.max(sums);
        answer = Math.min(answer, m);
    }

    private void cutNodes(List<Integer> cuts) {
        for (int cut : cuts) {
            int parentIndex = cut / 2;
            int c = cut % 2;
            Node node = nodes.get(parentIndex);
            if (c == 0) {
                node.cutLeft();
            } else if (c == 1) {
                node.cutRight();
            }
        }
    }

    private void constructNode(int[] num, int[][] links){
        int i = 0;
        for (int n : num) {
            var node = new Node();
            node.num = n;
            nodes.put(i, node);
            i++;
        }

        i = 0;
        for (int[] link : links) {
            Node node = nodes.get(i);
            if (links[i][0] != -1) {
                node.left = nodes.get(link[0]);
                node.left.parent = node;
            }

            if (links[i][1] != -1) {
                node.right = nodes.get(link[1]);
                node.right.parent = node;
            }

            i++;
        }
        root = nodes.get(0).getRoot();
    }

    private void recursiveLoop(List<Integer> cuts, int k) {
        if (k == 1) {
            // System.out.println("cuts: " + cuts);
            solve(cuts);
            return;
        }

        int firstIndex = 0;
        if (cuts.size() != 0) {
            firstIndex = cuts.get(cuts.size() - 1) + 1;
        }

        for (int i = firstIndex; i < flatedlinks.length; i++) {
            if (flatedlinks[i] == -1) {
                continue;
            }

            cuts.add(i);
            recursiveLoop(cuts, k - 1);
            cuts.remove((int) cuts.size() - 1);
        }
    }


    private int[] flatArray(int[][] links) {
        int[] result = new int[links.length * 2];
        int index = 0;
        for (var link : links) {
            result[index * 2] = link[0];
            result[index * 2 + 1] = link[1];
            index++;
        }
        return result;
    }
}