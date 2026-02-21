# 보석 쇼핑

## 문제 푼 방법
- left cursor와 right cursor를 따로 욺직이며 최소한의 거리를 유지
  - right cursor가 확장 했을 때, left cursor가 축소 시킬 수 있을 때까지 욺직임
  - set, map을 둘다 사용
    - set: 모든 보석을 포함하고 있는지 확인
    - map: 각 보석을 몇개씩 가지고 있는지 확인 (축소 시킬 때 사용됨)