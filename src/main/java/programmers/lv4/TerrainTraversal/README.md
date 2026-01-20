# 지형 이동

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/62050)

## 문제를 푼 방법
- greedy 하게 height가 덜 차이나는 쪽으로 BFS

## 배운 점
- Priority Queue
  - `new PriorityQueue((c1, c2) -> ((Coo) c1).delta - ((Coo)c2).delta);`
- Hash Set
  - hashCode와 equals를 둘 다 override 해야함 
