class Solution {
  public void wallsAndGates(int[][] rooms) {
      if(rooms.length == 0 || rooms[0].length == 0) return;
      int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0},{0, -1}};
      for(int i = 0; i < rooms.length; i++) {
          for(int j = 0; j < rooms[0].length; j++) {
              if(rooms[i][j] == 0) {
                  /* Find the GATE */
                  int dis = 0;
                  boolean[][] visited = new boolean[rooms.length][rooms[0].length];
                  dfsHelper(rooms, i, j, dis, visited, dirs);
              }
          }
      }
  }
  
  public void dfsHelper(int[][] rooms, int r, int c, int dis, 
                        boolean[][] visited, int[][] dirs) {
      dis ++;

      for(int[] dir : dirs) {
          int newRow = r + dir[0];
          int newCol = c + dir[1];
          if(newRow >= 0 && newCol >= 0 && 
             newRow < rooms.length && newCol < rooms[0].length &&
             rooms[newRow][newCol] > 0) {
              if(dis < rooms[newRow][newCol]) {
                  rooms[newRow][newCol] = dis;
                  dfsHelper(rooms, newRow, newCol, dis, visited, dirs);
              }
          }
      }
  }
}