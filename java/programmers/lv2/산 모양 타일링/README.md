# 산 모양 타일링

## 문제 푼 방법
- dp를 사용
- 점화식
```
if i % 2 == 0 
    dp[i] = dp[i-1] + dp[i-2]
if i % 2 == 1 && tops[i] == 0
    dp[i] = dp[i-1] + dp[i-2]
if i % 2 == 1 && tops[i] == 1
    dp[i] = dp[i-1] * 2 + dp[i-2]
```