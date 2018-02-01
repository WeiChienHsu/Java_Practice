class Solution {
  public int minPathSum(int[][] grid) {
      if(grid.length == 0) return 0;
      if(grid[0].length == 0) return 0;
      return helper(grid, 0, 0);
  }
  
  public int helper(int[][] grid, int i, int j) {
      if(i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
      
      if()
      int down = Integer.MAX_VALUE;
      int right = Integer.MAX_VALUE;
      
      if(i < grid.length - 1){
          down = helper(grid, i + 1, j);
      }
      
      if(j < grid[0].length - 1) {
          right = helper(grid, i, j + 1);
      }
      
      
      return grid[i][j] + Math.min(right, down);
  }
}