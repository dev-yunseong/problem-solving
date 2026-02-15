# 스타 수열

[문제](https://school.programmers.co.kr/learn/courses/30/lessons/70130)

## 문제 푼 방법
- 각 공통 숫자를 정해 놓음
  - list a의 숫자를 중복 제거 해서 각각 공통 숫자로 사용
- dp를 통해서 공통 숫자가 정해졌을 때의 최대 값을 찾음
  - `a[step]`, `a[step-1]` 둘다 공통 숫자가 아니거나, 둘다 공통 숫자 일때
    - `dp[step] = dp[step-1]`
  - `a[step]`, `a[step-1]` 둘 중 하나만 공통 숫자일 땐
    - `dp[step-1] == dp[step-2]` 일 때 (`a[step-1]`가 `a[step]`와 짝이 될 수 있을지 판단)
      - `dp[step] = dp[step-1] + 1` 
    - `dp[step-1] != dp[step-2]` 일 때
        - `dp[step] = dp[step-1]`
- 각 결과들을 종합