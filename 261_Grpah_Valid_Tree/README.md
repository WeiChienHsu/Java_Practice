# Graph Valid Tree
- 判斷每個點是否聯通？ 使用BFS (Connected Component)
- 判斷是否符合N個節點 N-1條邊

### Example
```

Given n = 5 and edges = [[0,1],[0,2],[0,3],[1,4]] reutrn true
Given n = 5 and edges = [[0,1],[1,2],[2,3],[1,3],[1,4]] reutrn false

```
### 轉化結構，變成適合使用 Queue + HashSet (InitializeGraph)
- 把 pair 改成方便存儲跟改變的圖狀結構（鄰接表）: Adjent List
- 通常不常存成矩陣表，會存成這種表：最外成是個Map，內容是該點配上一個HashSet

```java
  private Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    for(int i = 0; i < n; i++) {
      graph.put(i, new HashSet<>());
    }

    for(int i = 0; i < edges.length; i++) {
        int u = edges[i][0];
        int v = edges[i][1];
        graph.get(u).add(v);
        graph.get(v).add(u);
    }
  }
```

## Flood Fill 灌水法 
Queue 與 HashSet 配合用！
- 從頭到尾走過一次neighbor，只要hash內有的，代表已經處理過，每個丟入queue處理後，同時丟到hash！

```java
    Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);

    Queue<Integer> queue = new ArrayDeque<>(); // 處理點
    Set<Integer> hash = new HashSet<>(); // 紀錄使用過的點

    queue.offerLast(0);
    hash.add(0);

    while(!queue.isEmpty()) {
      int node = queue.pollFirst();
      for(Integer neighbor : graph.get(node)){
        if(hash.contains(neighbor)) {
          continue;
        }
        queue.offerLast(neighbor);
        hash.add(neighbor);
      }
    }
    return (hash.size() == n); // 是否所有點都被記錄了
  }
```




