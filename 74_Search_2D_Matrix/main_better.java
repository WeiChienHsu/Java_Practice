public class matrix2 {
    public static void main(String[] args) {
        int[][] m = {{1,   3,  5,  7},{10, 11, 16, 20},{23, 30, 34, 50}};
        System.out.println(searchMatrix(m,55));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) {
            return false;
        }
        if(matrix[0] == null || matrix[0].length ==0 ) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int start = 0;
        int end = m * n -1;

        while(end >= start){
            int mid = start + (end - start)/2;
            if(matrix[mid / n][mid % n] == target){
                return true;
            } else if(matrix[mid / n][mid % n] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

    }
