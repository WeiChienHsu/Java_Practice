class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0) return res;
        if(matrix[0] == null || matrix[0].length == 0)  return res;
        
        int row = matrix.length;
        int col = matrix[0].length;
        helper(res, matrix, row, col, 0); // 0 - > start point
        return res;
    } 
    
    private void helper(List<Integer> res, int[][] m, int row, int col, int offset) {
        // Base Case
        if(row == 0 || col == 0) return;
        if(row == 1) {
            for(int i = offset; i < col + offset; i++) {
                res.add(m[offset][i]);
            }
            return;
        }
        if(col == 1) {
            for(int i = offset; i < row + offset; i++) {
                res.add(m[i][offset]);
            }
            return;
        }
        
    // Up ROW
        for(int i = offset; i < offset + col - 1; i++ ) {
            res.add(m[offset][i]);
        }
        
    // Right COL
        for(int i = offset; i < offset + row - 1; i++ ) {
            res.add(m[i][offset + col - 1]);
        }
        
    // Down ROW
        for(int i = offset + col - 1; i > offset; i-- ) {
            res.add(m[offset + row - 1][i]);
        }
        
    // left COL
        for(int i = offset + row - 1; i > offset; i --) {
            res.add(m[i][offset]);
        }
    
    // Next Level
        helper(res, m, row - 2, col - 2, offset + 1);
        
        
    }
}