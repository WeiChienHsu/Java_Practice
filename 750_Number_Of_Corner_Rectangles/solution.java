/*
          [i0, j0]    i = i0 + 1 ; j = j0 + 1
           /  \
          /    \
[i0, j]   \    / [i, j0]
           \  /
           [i, j] 

    - Find grid[i0][j0] equals to 1
    - Find grid[i][j]: check gird[i][j] equal to 1 -> count += grid[i0][j] * grid[i][j0]
*/

class Solution {
  public int countCornerRectangles(int[][] grid) {
      int row = grid.length;
      int col = grid[0].length;
      int count = 0;
      
      for(int r = 0; r < row; r++) {
          for(int c = 0; c < col; c++) {
              if(grid[r][c] == 0) continue;
              for(int i = r + 1; i < row; i ++) {
                  if(grid[i][c] == 0) continue;
                  for(int j = c + 1; j < col; j++) {
                      count += grid[i][j] * grid[r][j];
                  }
              }
          }
      }
      return count;
  }
}