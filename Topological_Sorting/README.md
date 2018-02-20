# Topological Sorting 拓樸排序

必用BFS解

- 1. 先找InDegree，有多少邊指向這個點 : Count indegree
```java
Map<DirectedGraphNode, Integer> indegree = getIndegree(graph)
```

- 2. Topological Sorting : 把所有的StartNode拿到（indegree為0）

- 3. 丟到queue裡面進行bfs： 訪問過所有的鄰居，被訪問的map中Integer -1，接著把其他 Indegree 為 0 的node放入Queue當中。 （ 同時紀錄 order )

