public class matrix {
    public static void main(String[] args) {
        int[][] m = {{1,   3,  5,  7},{10, 11, 16, 20},{23, 30, 34, 50}};
        System.out.println(searchMatrix(m,55));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int n = matrix.length - 1;
        int m = matrix[0].length - 1;
        int i = 0; // i < n
        if(target > matrix[n][m]) return false;

        while(target >= matrix[i][m] && i <= n) {
            if(target == matrix[i][m]) return true;
            i++;
        }

        int[] newArray = matrix[i];
        int left = 0;
        int right = m;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(newArray[mid] == target) {
                return true;
            } else if(newArray[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}
