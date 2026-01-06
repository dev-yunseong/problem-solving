# 야근 지수

- [문제](https://school.programmers.co.kr/learn/courses/30/lessons/12927)
  - works [1, 3, 4, 5]
  - 총 n을 뺌 works에서
  - 그때 works의 원소를 제곱하여 합한 값이 최소가 되도록

## 문제 푼 방법
- works를 고르게 만드는 것이 제곱합이 가장 작음
- works에서 가장 큰 원소를 깎는 방식으로 구현

## 배운 점
- `PriorityQueue queue = new PriorityQueue(Collections.reverseOrder());`