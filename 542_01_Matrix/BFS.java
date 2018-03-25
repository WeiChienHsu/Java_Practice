class Solution {
  public int[][] updateMatrix(int[][] matrix) {
      Deque<int[]> queue = new ArrayDeque<>();
      int row = matrix.length;
      int col = matrix[0].length;
      
      int[][] helper = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
      
      // Update matrix and Queue
      for(int i = 0; i < row; i++) {
          for (int j = 0; j < col; j++) {
              if(matrix[i][j] == 0) {
                  queue.offer(new int[]{i, j});
                  continue;
              } 
              matrix[i][j] = Integer.MAX_VALUE;
          }
      }
      
      while(!queue.isEmpty()) {
          int[] cur = queue.pollFirst();
          int curRow = cur[0];
          int curCol = cur[1];
          for(int[] dir : helper) {
              int newRow = curRow + dir[0];
              int newCol = curCol + dir[1];
              
              if( isValid(newRow, newCol, row, col) && 
                 matrix[newRow][newCol] > matrix[curRow][curCol] + 1) {
                  queue.offerLast(new int[]{newRow, newCol});
                  matrix[newRow][newCol] = matrix[curRow][curCol] + 1;
              }

          }
      }
      
      return matrix;
  }
  
  public boolean isValid(int newRow, int newCol, int row, int col){
      return newRow >= 0 && newCol >= 0 && newRow < row && newCol < col;
  }
}