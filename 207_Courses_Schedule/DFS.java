class Solution {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
      ArrayList[] graph = new ArrayList[numCourses];
      
      // Create List 
      for(int i = 0; i < numCourses; i++) {
          graph[i] = new ArrayList();
      }
      
      // Add into the list
      for(int[] vector: prerequisites) {
          graph[vector[1]].add(vector[0]);
      }
      
      boolean[] visited = new boolean[numCourses];
      
      for(int i = 0; i < numCourses; i++) {
          if(!dfs(graph, visited, i)) {
              return false;
          }
      }
      return true;
      }
      
      private boolean dfs(ArrayList[] graph, boolean[] visited, int course) {
          if(visited[course]) {
              return false;
          } else {
              visited[course] = true;
          }
          
          // DFS to all negibors
          for(int i = 0; i < graph[course].size(); i++) {
              if(!dfs(graph, visited, (int)graph[course].get(i)))
                  return false;
          }
          visited[course] = false;
          return true;
      }
}