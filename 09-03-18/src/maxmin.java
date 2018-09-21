public class maxmin {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{8, 4, 7}, {6, 5, 9}};
        System.out.println(maxMinPath(matrix));


    }

    public static int maxMinPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] dp = new int[row][col];
        /* Initialize the dp matrix */
        dp[0][0] = matrix[0][0];

        /* Get the min Path in the first row */
        for(int i = 1; i < row; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], matrix[i][0]);
        }

        /* Get the min Path in the first column */
        for(int j = 1; j < col; j++) {
            dp[0][j] = Math.min(dp[0][j - 1], matrix[0][j]);
        }

        /* Get the min Path selected from upper or left side */
        for(int i = 1; i < row; i++) {
            for(int j = 1; j < col; j++) {
                dp[i][j] = Math.min(Math.max(dp[i - 1][j], dp[i][j - 1]), matrix[i][j]);
            }
        }
        return dp[row - 1][col - 1];
    }
}
