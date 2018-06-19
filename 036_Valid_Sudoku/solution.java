class Solution {
  public boolean isValidSudoku(char[][] board) {
      if (board == null) return false;
      return isRowValid(board) && isColValid(board) && isSubBoxValid(board);
      
  }
  
  public static boolean isRowValid(char[][] board) {
      int row = board.length;
      int col = board[0].length;
      for(int i = 0; i < row; i++) {
          Set<Character> rowSet = new HashSet<>();
          for(int j = 0; j < col; j++) {
              if(board[i][j] == '.') continue;
              if(rowSet.contains(board[i][j])) return false;
              rowSet.add(board[i][j]);
          } 
      }
      return true;
  }
  
  public static boolean isColValid(char[][] board) {
      int row = board.length;
      int col = board[0].length;
      for(int i = 0; i < col; i++) {
          Set<Character> colSet = new HashSet<>();
          for(int j = 0; j < row; j++) {
              if(board[j][i] == '.') continue;
              if(colSet.contains(board[j][i])) return false;
              colSet.add(board[j][i]);
          } 
      }
      return true;
  }
  
  public static boolean isSubBoxValid(char[][] board) {
      int row = board.length;
      int col = board[0].length;
      for(int i = 0; i < row; i = i + 3) {
          for(int j = 0; j < col; j = j + 3) {
              if(!isBoxValid(board, i, j)) return false;
          }
      }
      return true;
  }
  
  public static boolean isBoxValid(char[][] board, int row, int col) {
      Set<Character> boxSet = new HashSet<>();
      for(int i = row; i < row + 3; i++) {
          for(int j = col; j < col + 3; j++) {
              if(board[i][j] == '.') continue;
              if(boxSet.contains(board[i][j])) return false;
              boxSet.add(board[i][j]);
          }
      }
      return true;
  }
}