/* DFS Solution - But do not change the current states differently 
    Marked the original value as different number to represent its original state with new one.
    
    live -> live : 2
    live -> die  : -1
    die  -> live : 3
    die  -> die  : -2
    
    In the end, switch [2, 3, 1] to 1 and [-1, -2, 0] to 0.
*/

class Solution {
  public void gameOfLife(int[][] board) {
      int row = board.length;
      int col = board[0].length;
      /* Gives eight directions */
      int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
      
      for(int i = 0; i < row; i++) {
          for(int j = 0; j < col; j++) {
              int liveCount = 0;
              /* count its neighbor */
              /* Live: 2, -1, 1(original) */
              /* Dead: 3, -2, 0(original) */
              for(int[] dir : dirs) {
                  int newRow = i + dir[0];
                  int newCol = j + dir[1];
                  if(newRow >= 0 && newCol >= 0 && newRow < row && newCol < col 
                    && (board[newRow][newCol] == 1 || board[newRow][newCol] == -1 || board[newRow][newCol] == 2)) {
                      liveCount++;
                  }
              }
              
              if(board[i][j] == 1) {
                  /* The situation of live */
                  if(liveCount < 2 || liveCount > 3) {
                      /* Marked as die */
                      board[i][j] = -1;
                  } else {
                      /* Marked as live */
                      board[i][j] = 2;
                  }
              } else if(board[i][j] == 0) {
                  /* The situation of dead */
                  if(liveCount == 3) {
                      board[i][j] = 3;
                  } else {
                      board[i][j] = -2;
                  }
              }
          }
      }
      
      /* Transfer the board */
      for(int i = 0; i < row; i++) {
          for(int j = 0; j < col; j++) {
              if(board[i][j] == 2 || board[i][j] == 3) board[i][j] = 1;
              else if(board[i][j] == -1 || board[i][j] == -2) board[i][j] = 0;
          }
      }
      
  }
}