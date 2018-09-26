class Solution {
  public int countComponents(int n, int[][] edges) {
      List<List<Integer>> list = new ArrayList<>();
      boolean[] visited = new boolean[n];
      int count = 0;
      
      for(int i = 0; i < n; i++) {
          list.add(new ArrayList<>());
      }
      
      for(int[] edge : edges) {
          list.get(edge[0]).add(edge[1]);
          list.get(edge[1]).add(edge[0]);
      }
      
      Deque<Integer> queue = new ArrayDeque<>();
      
      /* BFS Solution */
      for(int i = 0; i < n; i++) {
          /* Check those nodes haven't been visited */
          if(!visited[i]) {
              /* Start the same area traversal */
              count ++;
              visited[i] = true;
              queue.offerLast(i);
              /* Traverse the neighbors and mark those nodes as visited */
              while(!queue.isEmpty()) {
                  List<Integer> neighbors = list.get(queue.pollFirst());
                  /* Visited the neighbors */
                  for(int j = 0; j < neighbors.size(); j++) {
                      int neighbor = neighbors.get(j);
                      if(!visited[neighbor]){
                          visited[neighbor] = true;
                          queue.offerLast(neighbor);
                      }
                          
                  }
              }
          }
      }
      
      return count;
  }
}