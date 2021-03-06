class Solution {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
      int[] ans = new int[numCourses];
      
      if(numCourses == 0) {
          return ans;
      }
      
      if(prerequisites == null){
          for(int i = 0; i < numCourses; i++) {
              ans[i] = numCourses--;
          }
          return ans;
      }
      
      List<Set<Integer>> graph = getGraph(numCourses, prerequisites);
      int[] indegree = getIndegree(prerequisites, numCourses);
      
      // put all indegree == 0 into a Queue
      Deque<Integer> queue = new ArrayDeque<>();
      
      for(int i = 0; i < numCourses; i++) {
          if(indegree[i] == 0) {
              queue.offerLast(i);
          }
      }
      
      int[] emptyArray = new int[0];
      
      // Reduce All indegree to 0 and record the index of Courses
      int curNum = 0;
      
      while(numCourses > 0) {
          int size = queue.size();
          if(size == 0)  return emptyArray;
          
          for(int i = 0; i < size; i++) {
              int curCourse = queue.pollFirst();
              ans[curNum++] = curCourse;
              numCourses--;
              
              // Go Througth the Graph to Reduce the number of indegree which curCourse point to
              for(int n : graph.get(curCourse)) {
                  indegree[n]--;
                  if(indegree[n] == 0){
                      queue.offerLast(n);
                  }
              }   
          }
      }
      
      return ans;
      
  }
  
  // [0, 1, 1, 2]
  public int[] getIndegree(int[][] pre, int n) {
      int[] indegreeNum = new int[n];
      for(int i = 0; i < pre.length; i++){
          indegreeNum[pre[i][0]]++;
      }
      return indegreeNum;
  }
  
  // [[1,2], [3], [3], []]
  public List<Set<Integer>> getGraph(int n, int[][] pre) {
      List<Set<Integer>> res = new ArrayList<>();
      
      for(int i = 0; i < n; i++) {
          res.add(new HashSet<>());
      }
      
      for(int i = 0; i < pre.length; i++) {
          res.get(pre[i][1]).add(pre[i][0]);
      }
      
      return res;
  }
}