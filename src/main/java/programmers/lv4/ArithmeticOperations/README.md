# 사칙연산

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/1843)

## 문제 푼 방법
- dynamic programming
  - 점화식
    - minDp[x][y] = min{minDp[x][i] - maxDp[i+1][x] or minDp[x][i] + minDp[i+1][x]}
    - maxDp[x][y] = max{maxDp[x][i] - minDp[i+1][x] or maxDp[x][i] + maxDp[i+1][x]}
