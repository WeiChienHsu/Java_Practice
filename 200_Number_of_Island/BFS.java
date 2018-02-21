class Solution {
    
  public int numIslands(boolean[][] grid) {
      if(grid == null || grid.length == 0 || grid[0].length == 0) {
          return 0;
      }
      
      int row = grid.length;
      int col = grid[0].length;
      int islands = 0;
      
      for(int i = 0; i < row; i++) {
          for(int j = 0; j < col; j++) {
              if(grid[i][j]){
                  markByBFS(grid,i, j);
                  islands++;
              }
          }
      }
      return islands;
  }
  
  private void markByBFS(boolean[][] grid, int x, int y) {
      int[] directionX = {0, 1, -1, 0};
      int[] directionY = {1, 0, 0, -1};
      
      Deque<Coordinate> queue = new ArrayDeque<>();
      
      queue.offer(new Coordinate(x, y));
      grid[x][y] = false;
      
      while(!queue.isEmpty()) {
        Coordinate coor = queue.pollFirst();
          for(int i = 0; i < 4; i++) {
              Coordinate adj = new Coordinate(
                  coor.x + directionX[i];
                  coor.y + directionY[i];
              );
              if(!inBound(adj, grid)) {
                  continue;
              }
              
              if(grid[adj.x][adj.y]) {
                  grid[adj.x][adj.y] = false;
                  queue.offer(adj);
              }
          }
      }
  }
  
  private boolean isBound(Coordinate coor, boolean[][] grid) {
      int row = grid.length;
      int col = grid[0].length;
      return coor.x >= 0 && coor.x < row && coor.y >= 0 && coor.y < col;
  }
}

class Coordinate {
  int x, y;
  public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
  }
}