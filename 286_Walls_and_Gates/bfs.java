class Solution {
  public void wallsAndGates(int[][] rooms) {
      if(rooms.length == 0 || rooms[0].length == 0) return;
      int row = rooms.length;
      int col = rooms[0].length;
      Deque<int[]> queue = new ArrayDeque<>();
      
      for(int i = 0; i < rooms.length; i++) {
          for(int j = 0; j < rooms[0].length; j++) {
              if(rooms[i][j] == 0) {
                  queue.offerLast(new int[]{i, j});
              }
          }
      }
      
      while(!queue.isEmpty()) {
          int[] current = queue.pollFirst();
          int r = current[0];
          int c = current[1];
          /* Check Left */
          if(c > 0 && rooms[r][c - 1] == Integer.MAX_VALUE) {
              rooms[r][c - 1] = rooms[r][c] + 1;
              queue.add(new int[]{r, c - 1});
          }
          
          /* Check Right */
          if(c < col - 1 && rooms[r][c + 1] == Integer.MAX_VALUE) {
              rooms[r][c + 1] = rooms[r][c] + 1;
              queue.add(new int[]{r, c + 1});
          }
          
          /* Check Up */
          if(r > 0 && rooms[r - 1][c] == Integer.MAX_VALUE) {
              rooms[r - 1][c] = rooms[r][c] + 1;
              queue.add(new int[]{r - 1, c});
          }
          
          /* Check Down */
          if(r < row - 1 && rooms[r + 1][c] == Integer.MAX_VALUE) {
              rooms[r + 1][c] = rooms[r][c] + 1;
              queue.add(new int[]{r + 1, c});
          }
      }
  }
}