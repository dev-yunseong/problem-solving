# 금과 운 운반하기

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/86053)

## 문제 푼 방법
- 이분 탐색, 결정 문제
- 다품종 정수 흐름

## 배운 점
- 최댓값
  - `Long.MAX_VALUE`
- 다품종 정수 흐름
  - 1, 2, 3를 a, b, c 만큼 전송 해야함
    - true <=>
      - 1만 전송 했을 때 >= a
      - 2만 전송 했을 때 >= b
      - 3만 전송 했을 때 >= c
      - 상관 없이 전송 했을 때 >= a, b, c