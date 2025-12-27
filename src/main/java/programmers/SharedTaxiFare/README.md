# 합승 택시 요금
> 플로이드 워셜 알고리즘 문제
[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/72413)

## 문제 푼 방법
- 플로이드 워셜 알고리즘으로 모든 cost를 계산한다.
- 경유하는 점 x가 있을 때, (s -> x) + (x -> a) + (x -> b) 중 최솟값을 찾는다.

## 배운 점
### 플로이드 워셜 알고리즘
> 모든 vertex에서 vertex까지의 weight를 구하는 알고리즘
- 초기화
  - 연결 안 된 vertex은 Inf
  - 연결 된 vertex는 edge의 weight
  - 본인에서 본인은 0
- 계산
    ```java
    for(int k = 0; k < number; k++) {
        for(int i = 0; i < number; i++) {
            for(int j = 0; j < number; j++) {
                if(d[i][k] + d[k][j] < d[i][j]) {
                    d[i][j] = d[i][k] + d[k][j];
                }
            }
        }
    } 
    ```
