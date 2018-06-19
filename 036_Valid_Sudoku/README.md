# Valid Sudoku

Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

1. Each row must contain the digits 1-9 without repetition.
2. Each column must contain the digits 1-9 without repetition.
3. Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

```
Example 1:

Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true
```
## Solution
1. 檢查 Row, 檢查 Col, 檢查每個Sub Box 是否有重複

```java
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
```