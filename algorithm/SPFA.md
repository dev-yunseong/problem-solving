# Shortest Path Faster Algorithm

음수 가중치가 포함된 그래프에서 최단 경로를 찾기 위한 알고리즘

## 메커니즘
1. 초기화
    - 시작 노드의 거리는 0, 나머지는 무한대로 설정
    - 시작 노드를 큐에 넣음
2. 반복
   - 큐에서 노드 `u`를 꺼냄
   - `u`와 연결된 모든 인접 노드 `v`를 확인
   - `dist[v] > dist[u] + weight(u, v)` 라면 `dist[v]`를 갱신
   - `v`가 큐에 없으면 큐에 넣음
3. 음수 사이클 감지
   - 만약 한 노드가 N번 이상 큐에 들어갔다면 그 그래프에 음수 사이클이 존재

## Example
```kotlin
private fun spfa(source: Int, sink: Int): Boolean {
    dist = IntArray(totalNodes) { Int.MAX_VALUE }
    parent = IntArray(totalNodes) { -1 }
    edgeIdx = IntArray(totalNodes) { -1 }
    val inQueue = BooleanArray(totalNodes)
    val queue: Queue<Int> = LinkedList()
    
    dist[source] = 0
    queue.add(source)
    inQueue[source] = true
    
    while (queue.isNotEmpty()) {
        val curr = queue.poll()
        inQueue[curr] = false
    
        for (i in adj[curr].indices) {
            val e = adj[curr][i]
            if (e.capacity - e.flow > 0 && dist[e.to] > dist[curr] + e.cost) {
                dist[e.to] = dist[curr] + e.cost
                parent[e.to] = curr
                edgeIdx[e.to] = i
                if (!inQueue[e.to]) {
                    queue.add(e.to)
                    inQueue[e.to] = true
                }
            }
        }
    }
    return dist[sink] != Int.MAX_VALUE
}
```