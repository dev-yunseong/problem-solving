# 길 찾기 게임
> 규칙에 따라 tree를 구성하고 전위 순회, 후위 순회를 구현

## 문제 푼 방법
- recursive 하게 tree를 구성
- recursive 하게 순회

## 배운 점
#### stream max
  `ints.stream().max(Integer::compareTo)`
### 정렬
#### Comparable interface를 상속 안 받았을 때
`Collections.sort(nodes, (n1, n2) -> -n1.compareTo(n2))`
#### Comparable interface를 상속 받았을 때
`Collections.sort(nodes)`