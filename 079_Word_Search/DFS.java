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