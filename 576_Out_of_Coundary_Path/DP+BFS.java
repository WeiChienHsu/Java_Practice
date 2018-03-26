class Solution {
  public int findPaths(int m, int n, int N, int a, int b) {
      
      if(N <= 0) {
          return 0;
      }
      
      final int MOD = 1000000007;
      int[][] count = new int[m][n];
      count[a][b] = 1;
      int result = 0;
      int[][] helper = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
      
      for(int step = 0; step < N; step++ ) {
          int[][] temp = new int[m][n];
          for(int i = 0; i < m; i++) {
              for(int j = 0; j < n; j++) {
                  int curM = i;
                  int curN = j;
                  
                  for(int[] dir : helper) {
                      int newM = curM + dir[0];
                      int newN = curN + dir[1];
                      
                      if(outOfBoundry(m, n, newM, newN)) {
                          // Out of boundry -> Record new result
                          result = (result + count[curM][curN]) % MOD;
                      } else {
                          // Not out of boundry -> Record temp value
                          temp[newM][newN] = (count[curM][curN] + temp[newM][newN]) % MOD;
                      }
                  }
              }
          }
          count = temp;
      }
      return result;
  }
  
  public boolean outOfBoundry(int m, int n, int newM, int newN) {
      return newM < 0 || newN < 0 || newM >= m || newN >= n;
  }
}