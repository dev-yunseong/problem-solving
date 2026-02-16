## Monotone Queue
> 단조 큐

list에서 지정된 길이의 sublist의 최댓값을 구하는 방법

## 메커니즘
1. first 중에 지정된 길이를 넘어서는 원소들을 지움
   - 지정된 길이 유지
2. last 중에 현재 값보다 작은 값들을 지움
   - 내림차순으로 유지
3. add last로 현재 index 추가
4. 이때 last first 값이 최댓값의 index

