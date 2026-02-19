# 도둑질

[문제](https://school.programmers.co.kr/learn/courses/30/lessons/42897)

## 문제 푼 방법
- 문제가 원형이기에 dp를 두번 사용
- 첫 칸을 포함하는 경우, 마지막 칸을 포함하는 경우를 따로 구함
- 두 경우 중 더 높은 쪽을 선택
- dp: `dp[i] = Math.max(dp[i-1], dp[i-2] + money[i])`