class Solution {
  public int maxAreaOfIsland(int[][] grid) {
      int maxArea = 0;
      
      if(grid == null) return maxArea;
      int col = grid.length;
      
      if(col == 0) return maxArea;
      int row = grid[0].length;
      
      boolean[][] visited = new boolean[col][row];
      
      for(int i = 0; i < col; i++) {
          for(int j = 0; j < row; j++) {
              if(!visited[i][j] && grid[i][j] == 1 ) {
                  maxArea = Math.max(maxArea, dfsHelper(grid, i, j, visited));
              }
          }
      }
      
      return maxArea;
      
  }
  
  public int dfsHelper(int[][] g, int i, int j, boolean[][] visited) {
      
      if(i < 0 || j < 0 || i >= g.length || j >= g[0].length || visited[i][j]) return 0;
      
      visited[i][j] = true;
      
      if(g[i][j] == 0) return 0;
      
      return 1 + dfsHelper(g, i + 1, j, visited) + dfsHelper(g, i - 1, j, visited) 
               + dfsHelper(g, i, j + 1, visited) + dfsHelper(g, i, j - 1, visited);
  }
}