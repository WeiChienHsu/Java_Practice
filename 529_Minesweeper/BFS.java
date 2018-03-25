class Solution {
  public char[][] updateBoard(char[][] board, int[] click) {
      
      
      int row = board.length;
      int col = board[0].length;
      
      Deque<int[]> queue = new ArrayDeque<>();
      int[][] helper = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, -1}, {-1, 1}, {1, 1}, {-1, -1}};
      
      char[][] res = new char[row][col];
  

      queue.offerLast(click);
      
      while(!queue.isEmpty()) {
          int[] cur = queue.pollFirst();
          int x = cur[0];
          int y = cur[1];
          
          // If click is 'M', mark it as 'X' and return 
          if(board[x][y] == 'M'){
              board[x][y] = 'X';
          } else {
              
              // Count the mines sround
              int count = 0;
              for(int[] dir : helper) {
                  int newRow = x + dir[0];
                  int newCol = y + dir[1];
                  if(newRow < 0 || newRow >= row || newCol < 0 || newCol >= col) {
                      continue;
                  }
                  if(board[newRow][newCol] == 'M' || board[newRow][newCol] == 'X') {
                      count++;
                 }
              }

              
              // Two condition: 
              // count == 0 : BFS the rest of nodes by puting into the Queue and mark them as 'B'
              // count > 0 : Add the number into cur position and return 
              
              if(count > 0) {
                  board[x][y] = (char)(count + '0');
              } else {
                  board[x][y] = 'B';
                  for(int[] dir : helper) {
                      int newRow = x + dir[0];
                      int newCol = y + dir[1];
                      if(newRow < 0 || newRow >= row || newCol < 0 || newCol >= col){
                          continue;
                      }
                      if(board[newRow][newCol] == 'E') {
                          queue.offerLast(new int[]{newRow, newCol});
                          board[newRow][newCol] = 'B';                               
                      }

                  }
              }
          }
          
      }
      return board;
  }
}