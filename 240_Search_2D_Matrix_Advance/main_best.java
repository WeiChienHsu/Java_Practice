public class matrix2 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        System.out.println(searchMatrix(matrix,32));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if(row == 0 || matrix[0] == null) return false;
        int col = matrix[0].length;
        if(col == 0) return false;


        // Start from the top-right point
        int curRow = 0;
        int curCol = col - 1;

        while(curRow < row && curCol >= 0) {
            if(matrix[curRow][curCol] > target) {
                curCol--;
            } else if(matrix[curRow][curCol] < target) {
                curRow++;
            } else {
                return true;
            }
        }

        return false;
    }
}
