class Solution {
  public int findCircleNum(int[][] M) {
      if(M == null || M.length == 0 || M[0].length == 0) return 0;
      int n = M.length; // row == col, we used n to present number of students
      
      boolean[] visited = new boolean[n];
      int count = 0;
      
      // 遍歷過一次所有的row，代表遍歷過所有的學生
      // 標記visited過的，代表已經進入了朋友圈
      for(int i = 0; i < n; i++) {
              if(!visited[i]) {
                  count++;
                  Solution.dfsHelper(M,i, n, visited);
              }
      }
      return count;
  }
  public static void dfsHelper(int[][] M, int currentStudent, int n, boolean[] visited) {
      
      // If this student has been visited
      // it means he or she will be someone's friend in the current friend circle
      // Dismiss
      
      if(visited[currentStudent]) return;
      
      // Marked the current Studnet as visited
      visited[currentStudent] = true;
      
      // Loop through the relationship of currentStudent with others
      for(int i = 0; i < n; i++) {
          // Find currentStudent's friend and put it into DFS
          if(M[currentStudent][i] == 1) {
              Solution.dfsHelper(M, i, n, visited);
          }
      }
  }
}