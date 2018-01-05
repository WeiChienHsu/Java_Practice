public class Matrix {
        public static void main(String[] args) {
            int[][] matrix = {
            {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                    {3,   6,  9, 16, 22},
                        {10, 13, 14, 17, 24},
                            {18, 21, 23, 26, 30}
        };
            int m = matrix.length;
            int n = matrix[0].length;
            System.out.println(searchMatrix(matrix,12, 0,0, m-1, n-1));
        }

        public static boolean searchMatrix(int[][] matrix, int target, int startX, int startY, int endX, int endY) {
            if(startX > endX || startY > endY) {
                return false;
            }

            int midX = startX + (endX - startX) / 2 ;
            int midY = startY + (endY - startY) / 2 ;

            if(matrix[midX][midY] == target){
                return true;
            } else if(matrix[midX][midY] > target) {
                // Case 2: go left and upper sub matrix
                return searchMatrix(matrix,target,startX,startY,endX,midY-1) ||
                        searchMatrix(matrix, target, startX, midY, midX -1, endY);
            } else {
                // Case 3: go down and right sub matrix
                return searchMatrix(matrix,target, startX,midY+1,endX,endY) ||
                        searchMatrix(matrix,target,midX+1,startY,endX,midY);
            }
        }
    }
