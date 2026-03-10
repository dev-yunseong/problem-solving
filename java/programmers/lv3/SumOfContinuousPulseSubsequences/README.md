# 연속 펄스 부분 수열의 합

[문제](https://school.programmers.co.kr/learn/courses/30/lessons/161988)

## 문제 푼 방법
- dynamic programming 처럼 품
  - 누적합을 통해서 전처리를 진행
  - 그 전처리한 array에서 가장 큰 값과 가장 작은 값의 차를 통해서 답을 구함

## 배운 점
- 인덱스 예외 처리를 대신하는 더미 패딩(Padding) 활용
  - i=0일 때의 별도 조건문 분기 대신, 배열 크기를 n+1로 설정하여 로직의 일관성을 확보함.