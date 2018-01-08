public class smallestK2 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };

        int k = 8;

        System.out.println("The " + k + "th Smallest number in the matrix is:  " + kthSmallest(matrix, k));
    }

    public static int kthSmallest(int[][] matrix, int k) {
        if(matrix == null) return Integer.MIN_VALUE;
        int row = matrix.length;
        int col = matrix[0].length;
        int start  = matrix[0][0], end = matrix[row - 1][col - 1];

        while (start <= end) {
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

    public static int noLarger(int[][] m, int cur) {
        int count = 0;
        int row = m.length;
        int col = m[0].length;
        int i = 0;
        int j = col - 1;
        while(i < row && j >= 0) {
            if(m[i][j] > cur) {
                j--;
            } else {
                count += 1 + j;
                i++;
            }
        }
        return count;
    }
}

