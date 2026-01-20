# 최고의 집합
>  집합의 길이, 집합의 총합이 주어질 때, 원소 전체의 곱이 가장 큰 집합을 구하라.

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/12938)


### 문제 푼 방법
- 처음엔 permutation처럼 모든 집합을 생성하여서 풀려고 시도 -> 시간 초과
- 정렬된 모든 종류의 집합만 생성 시도 -> 알고리즘이 너무 복잡함
- 생각해보니깐 모든 원소의 크기가 집합의 평균에 가까울 때 곱이 가능 큼
  - 이때 원소 총 합이 주어지기에 아주 쉽게 풀 수 있었음

### 배운 것
- 좀 더 문제를 분석하자.

#### Array 문법
##### List<Integer> to int[]
```java
List<Integer> list = new List();
list.toArray(); // Object[]가 되어서 사용 불가.

list.stream()
    .mapToInt(Integer.valueInt)
    .toArray();

var array = new int[list.size()];
// 이후 for 문으로 직접 값 넣기
``` 

##### 길이
```java
array.length;
```

##### 그 외

```java
// 복사
Arrays.copyOf(array);
Arrays.copyOfRange(arr, 1,4); // 1~3

// 정렬
Arrays.sort(array);

// 채우기
Arrays.fill(arr, 0);   // 전체 0으로 채우기
Arrays.fill(arr, 1, 4, 7); // index 1~3을 7로

// 비교
Arrays.equals(arr1, arr2);
Arrays.deepEquals(arr1, arr2); // 2차원 배열 비교

// search
int idx = Arrays.binarySearch(arr, target); // array가 정렬됐을 때

// array to list
List<Integer> list = Arrays.stream(arr).boxed().toList(); // boxed: Stream<int> => Stream<Integer>
```
  



  