# 모두 0으로 만들기

- [문제](https://school.programmers.co.kr/learn/courses/30/lessons/76503)
  - 연결된 노드 간에 한쪽은 증가 시키고 한쪽은 감소 시킬 수 있음
  - 트리가 주어졌을 때 최소한의 행동으로 모든 가중치를 0으로 만들어야함

## 문제 푼 방법
- 구현
  - leaf node들을 queue에 모으고 하나씩 0으로 만들어간다.
    - leaf node가 0이 됐을 때, parent node가 leaf node가 되면 leaf node queue에 넣는다.
- 배운 점: 자료형을 주의하자