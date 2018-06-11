class Solution {
  public void solve(char[][] board) {
      if(board.length == 0) return;
      int row = board.length;
      if(board[0].length == 0) return;
      int col = board[0].length;
      
      // Traverse First and Last Row 
      // board[0][i]  & board[row - 1][i] (row > 1)
      for(int i = 0; i < col; i++) {
          if(board[0][i] == 'O') {
              Solution.dfsHelper(0, i, row, col, board);
          }
          
          if(row > 1 && board[row - 1][i] == 'O') {
              Solution.dfsHelper(row - 1, i, row, col, board);
          }
      }
      
      // Traverse First and Last Col
      // board[i][0]  & board[i][col - 1] (col > 1)
      for(int j = 0; j < row; j++) {
          if(board[j][0] == 'O') {
              Solution.dfsHelper(j, 0, row, col, board);
          }
          
          if(col > 1 && board[j][col - 1] == 'O') {
              Solution.dfsHelper(j, col - 1, row, col, board);
          }
      }
      
      // Change all 'O' into 'X && all '*' into 'O'
      for(int i = 0; i < row; i++) {
          for(int j = 0; j < col; j++) {
              if(board[i][j] == 'O') board[i][j] = 'X';
              if(board[i][j] == '*') board[i][j] = 'O';
          }
      }
  }
  
  public static void dfsHelper(int r, int c, int row, int col, char[][] board) {
      if(board[r][c] == 'O') {
          board[r][c] = '*';
          
          // Upper
          if(r - 1 > 0 && board[r - 1][c] == 'O') {
              Solution.dfsHelper(r - 1, c, row, col, board);
          }
              
          // Down
          if(r + 1 < row && board[r + 1][c] == 'O') {
              Solution.dfsHelper(r + 1, c, row, col, board);
          }
          
          // Left
          if(c - 1 > 0 && board[r][c - 1] == 'O') {
              Solution.dfsHelper(r, c - 1, row, col, board);
          }    
          
          // Right
          if(c + 1 < col && board[r][c + 1] == 'O') {
              Solution.dfsHelper(r, c + 1, row, col, board);
          }
      }
  }
}