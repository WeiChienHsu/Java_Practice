class Solution {
  public int uniquePaths(int m, int n) {
      int[][] matrix = new int[m][n];
      
      for(int i = 0; i < m; i++) {
          for(int j = 0; j < n; j++) {
              // 只有上和左來決定現在的Step
              // 如果是邊邊的話，直接給定1
              matrix[i][j] = i == 0 || j == 0? 1 : matrix[i - 1][j] + matrix[i][j - 1];
          }
      }
      return matrix[m - 1][n - 1];
  }
}