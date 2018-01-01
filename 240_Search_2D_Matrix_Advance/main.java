class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length;
            if(m == 0 || matrix[0] == null) return false;
            int n = matrix[0].length;
            if(matrix == null) return false;
            
        
        return binarySearch(matrix,target,0,0,m-1,n-1);
    }
    
    public boolean binarySearch(int[][] matrix, int target, int startX, int startY, int endX, int endY) {
            if(startX > endX || startY > endY) {
                return false;
            }

            int midX = startX + (endX - startX) / 2 ;
            int midY = startY + (endY - startY) / 2 ;

            if(matrix[midX][midY] == target){
                return true;
            } else if(matrix[midX][midY] > target) {
                // Case 2: go left and upper sub matrix
                return binarySearch(matrix,target,startX,startY,endX,midY-1) ||
                        binarySearch(matrix, target, startX, midY, midX -1, endY);
            } else {
                // Case 3: go down and right sub matrix
                return binarySearch(matrix,target, startX,midY+1,endX,endY) ||
                        binarySearch(matrix,target,midX+1,startY,endX,midY);
            }
        }
}