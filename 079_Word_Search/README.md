# Word Search

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

```
Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
```

## Solution

用DFS一個一個搜尋下個String index是否存在鄰居當中，記得要backtracking把拜訪過的紀錄移除，因為起始點不同。

- 注意條件判斷，容易出錯。
- 使用 currentIndex == string.length() 來代表順利 traversal 結束，應該return true.
- 因為 dfsHelper 不是 void Type，要考慮 return 值，丟入 dfs 當中的鄰居亦會產生 return value
- 過去 visited 過的 char，如果離開的原本的起點，還會繼續被使用，所以要做backtracking處理
- String.length() 以及 const int array.length 區別


```java
class Solution {
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        
        boolean[][] visited = new boolean[row][col];
        
        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                // 從第一個 Match 的字母開始做 dfs
                if(word.charAt(0) == board[r][c] && Solution.dfsHelper(board, word, r, c, row, col, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean dfsHelper(char[][] board, String word, 
                                    int r, int c, int row, int col, 
                                    int currentIndex, boolean[][] visited) {
        // Base Case
        if(currentIndex == word.length()) return true;        
        
        // Invalid Input
        if(r < 0 || c < 0 || r >= row || c >= col || board[r][c] != word.charAt(currentIndex) || visited[r][c]) return false;

        visited[r][c] = true;
        
        // Put the neighbors into the dfsHelper
        if(
        Solution.dfsHelper(board, word, r - 1, c, row, col, currentIndex + 1, visited) ||
        Solution.dfsHelper(board, word, r + 1, c, row, col, currentIndex + 1, visited) ||
        Solution.dfsHelper(board, word, r, c - 1, row, col, currentIndex + 1, visited) ||
        Solution.dfsHelper(board, word, r, c + 1, row, col, currentIndex + 1, visited)) {
            return true;
        }

        visited[r][c] = false;
        return false;
    }
}
```