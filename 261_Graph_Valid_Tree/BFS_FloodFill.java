import java.util.*;

public class Solution {
  public boolean validTree(int n, int[][] edges) {
    if( n == 0) {
      return false;
    }

    if(edges.length != n - 1) {
      return false;
    }

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

  // 改成鄰接表的形式：

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
    return graph;
  }
}