class Solution {
  public int islandPerimeter(int[][] grid) {
      int row = grid.length;
      int col = grid[0].length;
      int island = 0;
      
      for(int i = 0; i < row; i++) {
          for(int j = 0; j < col; j++) {
              if(grid[i][j] == 1) {
                  island += 4;
                  if(i + 1 < row && grid[i + 1][j] == 1) island -= 2;
                  if(j + 1 < col && grid[i][j + 1] == 1) island -= 2;
              }
          }
      }
      return island;
  }

}