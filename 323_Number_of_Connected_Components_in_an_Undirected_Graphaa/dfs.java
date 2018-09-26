class Solution {
    
  public int countComponents(int n, int[][] edges) {
      List<List<Integer>> list = new ArrayList<>();
      boolean[] visited = visited = new boolean[n];
      int count = 0;
      
      for(int i = 0; i < n; i++) {
          list.add(new ArrayList<>());
      }
      
      for(int[] edge : edges) {
          list.get(edge[0]).add(edge[1]);
          list.get(edge[1]).add(edge[0]);
      }
      
      /* DFS Solution */ 
      for(int i = 0; i < n; i++) {
          if(!visited[i]) {
              count++;
              dfs(i, visited, list);
          }
      }
      
      return count;
  }
  
  public void dfs(int num, boolean[] visited, List<List<Integer>> list) {
      visited[num] = true;
      for(int i : list.get(num)) {
          if(!visited[i]) {
              dfs(i, visited, list);
          }
      }
  }
}