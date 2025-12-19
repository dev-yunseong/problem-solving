# 전력망을 둘로 나누기
> node의 수 차이가 가장 덜 나게, Tree의 edge를 끊어라 

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/86971)

## 문제 푼 방법
- BFS로 node 수를 세아림
- greedy하게 splitted edge를 업데이트

## 배운 점
```java
import java.util.stream.*;

// .toList()가 없는 java version
list.strean().collect(Collectors.toList());
```
