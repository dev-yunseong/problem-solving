# 이중우선순위큐

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/42628)

## 문제 푼 방법
- array list 기반으로 binary search로 insert 하는 방식으로 구현

## 배운점
- `Collections.binarySearch(list, value)`
  - [doc](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Collections.html#binarySearch(java.util.List,T))
  - return 
    - 정확히 같은 값을 찾으면 -> 그 값의 index
    - 같은 값이 없으면 -> (-(insertion point) - 1)
  - `>=0`를 통해서 그 값이 존재하는지 확인할 수 있음
- 이중 우선 순위 큐 구현체
  - Priority Queue 2개, Hash Set 1하나로 구현
  - Queue는 각각 최대, 최소 값을 관리
  - Set은 삭제 된 값의 id를 저장 -> lazy delete로 사용 