# 네트워크

- [문제](https://school.programmers.co.kr/learn/courses/30/lessons/43162)
  - 비연결 그래프의 연결 성분 개수를 구하는 문제

## 문제 푼 방법
- 무작위 정점을 시작으로 연결 성분을 구함
- counter+=1 후, 구한 연결 성분의 정점들을 뺀 무작위 정점을 시작으로 연결 성분 구하기를 반복