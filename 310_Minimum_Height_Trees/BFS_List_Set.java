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