class Solution {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
      ArrayList[] graph = new ArrayList[numCourses];
      int[] degree = new int[numCourses];
      Deque<Integer> queue = new ArrayDeque<>();
      int count = 0;
      
      // Init the Graph
      for(int i = 0; i < numCourses; i++) {
          graph[i] = new ArrayList();
      }
      
      // Init the Level
      for(int i = 0; i < prerequisites.length; i++){
          degree[prerequisites[i][0]]++;;
          graph[prerequisites[i][1]].add(prerequisites[i][0]);
      }
      
      
      for(int i = 0; i < degree.length; i++) {
          if(degree[i] == 0) {
              queue.offerLast(i);
              count++;
          }
      }
      
      while(!queue.isEmpty()) {
          int course = queue.pollFirst();
          for(int i = 0; i < graph[course].size(); i++) {
              int pointer = (int) graph[course].get(i);
              degree[pointer]--;
              if(degree[pointer] == 0) {
                  queue.offerLast(pointer);
                  count++;
              }
          }
      }
      
      return count == numCourses? true : false;
  }
}