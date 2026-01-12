# 퍼즐 조각 채우기

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/84021)

## 문제 푼 방법
- 구현
  - findBlock
    - table, game_board에서 빈공간들과 block들을 찾아서 list로 반환
  - Cell
    - (x, y)
    - hashCode, equals를 overriding해서 hash set을 이용
  - Block
    - cell들을 가지고 있음
    - 다른 블럭과 비교할 수 있음 (돌려서)
  - blocks, blanks를 다 구하고 각각 비교해서 채움

## 배운 점
- 블럭 돌리기
  - (x, y)
  - (-y, x)
  - (y, -x)
  - (-x, -y)