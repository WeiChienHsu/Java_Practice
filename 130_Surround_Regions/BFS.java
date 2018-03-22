class Solution {
  public void solve(char[][] board) {
      
      if(board.length == 0){
          return;
      }
      
      Deque<Point> queue = new ArrayDeque<>();
      int rowN = board.length;
      int colN = board[0].length;
      
      if(colN == 0) {
          return;
      }
      
      // Row Edges
      for(int i = 0; i < rowN; i++ ) {
          if(board[i][0] == 'O'){
              board[i][0] = '+';
              queue.offerLast(new Point(i, 0));
          }
          
          if(board[i][colN - 1] == 'O'){
              board[i][colN - 1] = '+';
              queue.offerLast(new Point(i, colN - 1));
          }
      }
          
      // Col Edges
      for(int j = 0; j < colN; j++) {
          if(board[0][j] == 'O'){
              board[0][j] = '+';
              queue.offerLast(new Point(0, j));
          }
          
          if(board[rowN - 1][j] == 'O'){
              board[rowN - 1][j] = '+';
              queue.offerLast(new Point(rowN - 1, j));
          }
      }
      
      // BFS all '+' in queue to change all 'O' met by '+'
      while(!queue.isEmpty()){
          
          Point cur = queue.pollFirst();
          int row = cur.x;
          int col = cur.y;
          
          // Upside
          if(row - 1 >= 0 && board[row - 1][col] == 'O'){
              board[row - 1][col] = '+';
              queue.offerLast(new Point(row - 1, col));
          }
              
          // Buttom
          if(row + 1 < rowN && board[row + 1][col] == 'O'){
              board[row + 1][col] = '+';
              queue.offerLast(new Point(row + 1, col));
          }
              
          // Left
          if(col - 1 >= 0 && board[row][col - 1] == 'O') {
              board[row][col - 1] = '+';
              queue.offerLast(new Point(row, col - 1));
          }
          // Right 
          if(col + 1 < colN && board[row][col + 1] == 'O'){
              board[row][col + 1] = '+';
              queue.offerLast(new Point(row, col + 1));
          } 
      }
      
      // Traversal All nodes and change '+' to 'O', 'O' to 'X'
      for(int i = 0; i < rowN ; i++){
          for(int j = 0; j < colN; j++){
              if(board[i][j] == 'O'){
                  board[i][j] = 'X';
              } else if(board[i][j] == '+'){
                  board[i][j] = 'O';
              } else{
                  continue;
              }
          }
      }
  }
}

class Point{
  int x;
  int y;
  public Point(int x, int y) {
      this.x = x;
      this.y = y;
  }
}