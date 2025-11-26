# 단체사진 찍기

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/1835)

### 문제 푼 방법
- List를 permutation 하여 모든 순열을 생성
- 조건을 문자열을 파싱하여 조건 체크, 조건 통과 횟수 체크

### 배운 것

- java 문법
  - String에서 char 가져오기
    ```java
    private char getChar(){
        return string.charAt(index);
    }
    ```
  - List 다루기
    ```java
    // list에서 i, j위치의 원소를 바꿈
    Collections.swap(list, i, j);

    // list에서 list[i:j] 부분만 reverse
    // subList는 list의 메모리를 참조, 즉 subList 수정 시 list 또한 수정이 된다.
    Collections.reverse(list.subList(i, j));
    ``` 

- permutation 방법
  - java에선 permutation을 생성해주는 내장함수가 없기에 직접 구현해야함
    ```java
    private boolean nextPermutation(List<Character> last) {
        // a, b, e, d, c
        // 뒤에서부터 순회하여 오름차순이 아닌 첫 원소 즉 b의 위치를 찾음
        int i = last.size() - 2;
        while (i >= 0 && last.get(i) > last.get(i + 1)) { i -= 1;}
    
        if (i < 0) return false;

        // a, b, e, d, c
        // 뒤에서부터 순회하여 b보다 큰 원소를 찾음 즉 c
        int j = last.size() - 1;
        while (last.get(i) > last.get(j)) { j -= 1;}
    
        // a, b, e, d, c
        // c, d의 위치를 바꿈
        // a, c, e, d, b
        Collections.swap(last, i, j);
        
        // a, c, e, d, b
        // 오름차순 부분을 reverse하여 정렬함
        // a, c, b, d, e
        Collections.reverse(last.subList(i+1, last.size()));
        return true;
    }
    ```