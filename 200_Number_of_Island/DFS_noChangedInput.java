class Solution {
  // 1. 遍歷整個圖，當遇到 沒有 visited 過的，以及 '1' 的時候，小島數+1，並丟入DFS中處理
  // 2. DFS: 當該點為 '1' 且 沒有 visited 過，處理該點：
  // - 標記為 visited
  // - 檢查邊界（上下左右），如果都在邊界內，丟入DFS處理
public int numIslands(char[][] grid) {
  if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
  int row = grid.length;
  int col = grid[0].length;
  int count = 0;
  
  boolean[][] visited = new boolean[row][col];
  for(int i = 0; i < row; i++) {
      for(int j = 0; j < col; j++) {
          if(!visited[i][j] && grid[i][j] == '1') {
              count ++;
              Solution.dfsHelper(i, j, row, col, grid, visited);
          }
      }
  }
  return count;
}

public static void dfsHelper(int r, int c, int row, int col, char[][] grid, boolean[][] visited) {
  if(grid[r][c] == '1' && !visited[r][c]) {
      visited[r][c] = true;
      // upper
      if(r - 1 >= 0 && !visited[r - 1][c]) dfsHelper(r - 1, c, row, col, grid, visited);
      // down
      if(r + 1 < row && !visited[r + 1][c]) dfsHelper(r + 1, c, row, col, grid, visited);
      // left
      if(c - 1 >= 0 && !visited[r][c - 1]) dfsHelper(r, c - 1, row, col, grid, visited);
      // right
      if(c + 1 < col && !visited[r][c + 1]) dfsHelper(r, c + 1, row, col, grid, visited);
  } 
}
}