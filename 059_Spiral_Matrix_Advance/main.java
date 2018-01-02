class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        if(n == 0) return matrix;
        
        int colMin = 0;
        int colMax = n - 1;
        int rowMin = 0;
        int rowMax = n - 1;
        int count = 1;
        
        while(colMin <= colMax && rowMin <= rowMax) {
            for(int i = colMin; i <= colMax; i++){
                matrix[rowMin][i] = count++;
            }
            rowMin++;
            
            for(int i = rowMin; i <= rowMax; i++) {
                matrix[i][colMax] = count++;
            }
            colMax--;
            
            if(rowMin > rowMax || colMin > colMax) break;
            
            for(int i = colMax; i >= colMin; i--) {
                matrix[rowMax][i] = count++;
            }
            rowMax--;
            
            for(int i = rowMax; i >= rowMin; i--) {
                matrix[i][colMin] = count++;
            }
            colMin++;
        }
        return matrix;
    }
}