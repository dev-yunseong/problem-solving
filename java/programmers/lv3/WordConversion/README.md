# 단어 변환

- [문제](https://school.programmers.co.kr/learn/courses/30/lessons/43163)
  - 단어 start, target와 단어 공간 words가 있을 때
  - 단어를 한글자 씩 변환하여 start에서 target으로 변환에 필요한 변환 횟수를 구하는 문제 

## 문제 푼 방법
- words를 파싱해서 인접 리스트로 만듦
- BFS로 가장 짧은 거리 구하기

## 배운 점
- Collectors 패키지
  - `import java.util.stream.*;`
- Queue
  - Queue는 Stack과 다르게 abstract class
    - LinkedList가 대표적인 구현체
  - 메서드
    - add(e), remove(), peek(), addAll(list)