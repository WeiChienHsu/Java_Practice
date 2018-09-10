# Graph Valid Tree
- 判斷每個點是否聯通？ 使用BFS (Connected Component)
- 判斷是否符合N個節點 N-1條邊

### Example
```

Given n = 5 and edges = [[0,1],[0,2],[0,3],[1,4]] reutrn true
Given n = 5 and edges = [[0,1],[1,2],[2,3],[1,3],[1,4]] reutrn false

```
### 轉化結構，變成適合使用 Queue + HashSet (InitializeGraph)
- 把 pair 改成方便存儲跟改變的圖狀結構（鄰接表）: [Adjacency List](https://www.geeksforgeeks.org/graph-and-its-representations/)
- 通常不常存成矩陣表，會存成這種表：最外成是個Map，內容是該點配上一個HashSet
```
edges = [[0,1],[0,2],[0,3],[1,4]]
graph <0> set(1,2,3)
graph <1> set(0,4)
graph <2> set(0)
graph <3> set(0)
graph <4> set(1)


```


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


## DFS - 檢查是否有環 以及 無連接的部分



```java
public class Solution {
public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            map.get(edges[i][0]).add(edges[i][1]);
            map.get(edges[i][1]).add(edges[i][0]);
        }
        boolean[] visited = new boolean[n];

        if (findCircle(0, -1, map, visited)) {
            // check if there is loop
            return false;
        }

        // check if every node is included
        for (boolean isVisited : visited) {
            if (!isVisited) {
                return false;
            }
        }
        return true;
    }
    
    private boolean findCircle(int curr, int prev, Map<Integer, List<Integer>> map,
                               boolean[] visited) {
        if (visited[curr]) {
            return true;
        }
        visited[curr] = true;
        
        for (int next : map.get(curr)) {
            if (next != prev && findCircle(next, curr, map, visited)) {
                return true;
            }
        }
        return false;
    }
}
```