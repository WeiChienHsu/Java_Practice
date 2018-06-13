# Redundant Connection 


## Solution - DFS
消去造成環的那個邊。

For each edge {u, v}, use DFS to check wheter u, v have already been connected.

Time Compliety: O(n^2)

使用 graph 來記錄每個造訪過的 vertex connected 的 neighbor

每一次檢查vertex前都要new 一個新的set (visited) 來記錄處理過的 vertex

利用 dfs 來找尋該 edge 的兩個點 (start, end) 是否已經存在一個Path( 能夠透過graph 找 neighbor 連到對方那)

如果 dfs 遍歷後，沒有Cycle，將此兩點加入 graph 當中（變成下次dfs造訪時的參考對象）

判斷條件:
1. 當 start == end  return true
2. 造訪時，visited登記，避免二次造訪（上次造訪沒有Cycle，二次造訪一樣不會有Cycle)
3. 如果當前的graph內，沒有這兩點，return false
4. 用起始點做dfs，看能不能到達end

```java
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        
        if (edges == null) return new int[]{};
        
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        for(int[] edge : edges) {
            // Get the start and end point
            int u = edge[0];
            int v = edge[1];
            Set<Integer> visited = new HashSet<>();
            
            if(Solution.hasPath(u, v, graph, visited)) {
                return edge;
            }
            
            // Put the neighbor into the Map
            List<Integer> neighbors = graph.getOrDefault(u, new ArrayList<>());
            neighbors.add(v);
            graph.put(u, neighbors);
            
            neighbors = graph.getOrDefault(v, new ArrayList<>());
            neighbors.add(u);
            graph.put(v, neighbors);
        }
        
        return new int[]{ };
    }
    
    public static boolean hasPath(int start, int end, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        if (start == end) return true;
        visited.add(start);
        if(!graph.containsKey(start) || !graph.containsKey(end)) return false;
        
        for(int neighbor : graph.get(start)) {
            if(visited.contains(neighbor)) continue;
            if(Solution.hasPath(neighbor, end, graph, visited)) return true;
        }
        return false;
    }
    
}
```

## Solution - Union Find
Weighted quick-union with path compression. 
For each edge {u, v},check wheter u, v have already been connected.

Time Comlexity : O(nlogn*) ~= O(n)



使用一個 UnionFindSet Class:
(一個array紀錄Parent, 一個array紀錄Rank，Init的時候讓Parent pointer指向自己，Rank == 1)
1. Find：找尋該node的root node，並且做好Path Compression 
```java
        public int Find(int u) {
            while (parents_[u] != u) {
                parents_[u] = parents_[parents_[u]];
                u = parents_[u];
            }
            return u;
        }
```
2. Union:比較兩個Node的 root node 是否相同，如果相同 Return False，如果不相同
3. 處理兩個Node，將Ranks 比較小的 並到 Ranks 比較大的 root node 上面

```java
    bool Union(int u, int v) {
        int pu = Find(u);
        int pv = Find(v);
        if (pu == pv) return false;
        
        // Meger low rank tree into high rank tree
        if (ranks_[pv] > ranks_[pu])
            parents_[pu] = pv;           
        else if (ranks_[pu] > ranks_[pv])
            parents_[pv] = pu;
        else {
          // Randomly Chose
            parents_[pv] = pu;
            ranks_[pv] += 1;
        }
        
        return true;
    }
```

遍歷一次整個 edges，將所有 u and v 丟入 UnionFindSet.Union當中，只要Return false，回傳該點。


```java
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFindSet set = new UnionFindSet(edges.length);
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            System.out.println(u);
            System.out.println(v);
            
            if(!set.Union(u, v)) {
                return edge;
            }
        }
        return new int[]{};
    }
}

class UnionFindSet {
    private int[] ranks;
    private int[] parents;
    public UnionFindSet(int n) {
        this.ranks = new int[n + 1];
        this.parents = new int[n + 1];
        for(int i = 0; i < n + 1; i++) {
            ranks[i] = 1;
            parents[i] = i;
        }    
    }
    
    public boolean Union(int u, int v) {
        int rootU = Find(u);
        int rootV = Find(v);
        
        if(rootU == rootV) return false;
        
        if(this.ranks[rootU] > this.ranks[rootV]) {
            this.parents[rootV] = rootU;
        } else if(this.ranks[rootU] < this.ranks[rootV]) {
            this.parents[rootU] = rootV;
        } else {
            parents[rootV] = rootU;
            this.ranks[rootU]++;
        }
        
        return true;
    }
    
    public int Find(int u) {
        while(this.parents[u] != u) {
            this.parents[u] = this.parents[this.parents[u]];
            u = this.parents[u];
        }
        return u;
    }
}

```