# Minimum Height Trees
For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Example 1:
```
Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]
```

Example 2:
```
Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]
```

## Solution
The actual implementation is similar to the BFS topological sort. Remove the leaves(aka degree = 1), update the degrees of inner vertexes. Then remove the new leaves. Doing so level by level until there are 2 or 1 nodes left. What’s left is our answer!
-  鄉村包圍都市，等同於 拓樸排序的題目，把只有一個鄰居（最外圍的點）移除，直到最後剩下一或兩個點。

- The time complexity and space complexity are both O(n).

- 1 Get the indegree Value of all Nodes
- 2 Used a List to Set those connected Nodes with a Set
- [[1],[0,2],[1,3,4],[2],[4]]
- 3 Initize the List<Set<Itnger>> implemented by ArrayList
- 4 Save all the leaves into a Queue(aks indegree == 1)
- 5 Start to deal with all Nodes stored in the List, while n < 2 stop the loop
- 6 Since we need to remove the leaf by leavels, so we give a current size of Queue first
- 7 Get the connected Node by geting the only node save in the index of leave by using iterator in the Set
- 8 remove the leaf from the index of connected Node we got and check if it became a leaf(aka indegree). If Yes, put it into the Queue and we'll deal with it later
- 9 Get all Nodes(1 or 2) from the Queue into List and return the List.

```js
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        
        if (n <= 1) return Arrays.asList(0);
        
        // Get indegree of each Node
       List<Set<Integer>> graph = indegree(n, edges);
        
        // Put those Leaves into Queue
        // [0,3,4]
        
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            if(graph.get(i).size() == 1) {
                queue.offerLast(i);
            }
        }
        
        // Remove Leaf from original Set and node connected with it
        
        while(n > 2) {
            
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                // poll out the current leaf
                int curNode = queue.pollFirst();
                n --;
                
                // Find the connectd node from leaf
                int connected = graph.get(curNode).iterator().next();
                
                // Remove leaf from their connection and check if it's degree(size) equal to 1
                graph.get(connected).remove(curNode);
                if(graph.get(connected).size() == 1) {
                    queue.offerLast(connected);
                }   
            }
        }
        
        
        while(!queue.isEmpty()) {
            int cur = queue.pollFirst();
            ans.add(cur);
        }
        
        return ans;
        
        
        
    }
    
    // [[1],[0,2],[1,3,4],[2],[4]]
    
    public List<Set<Integer>> indegree(int n, int[][] edges) {
        List<Set<Integer>> result = new ArrayList<>();
        
        for (int i = 0; i < n; ++i) result.add(new HashSet<>());
        for (int[] edge : edges) {
            result.get(edge[0]).add(edge[1]);
            result.get(edge[1]).add(edge[0]);
        }
        return result;
    }
}

```