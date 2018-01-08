class Solution {
   
    public  int kthSmallest(int[][] matrix, int k) {
        if(matrix == null) return Integer.MIN_VALUE;
        int row = matrix.length;
        int col = matrix[0].length;
        int start = matrix[0][0];
        int end = matrix[row - 1][col - 1];
        
        while(start <= end) {
            int mid = start + (end - start) / 2;
            int count = noLarger(matrix, mid);
            if(count < k) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
    
    public int noLarger(int[][] matrix, int cur) {
        int row = matrix.length;
        int col = matrix[0].length;
        int i = 0;
        int j = col - 1;
        int count = 0;
        
        while(i < row && j >= 0) {
            if(matrix[i][j] > cur) {
                j--;
            } else {
                i++;
                count += 1 + j;
            }
        }
        
        return count;
        
        
    }

}