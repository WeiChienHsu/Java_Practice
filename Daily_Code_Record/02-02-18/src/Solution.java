import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2 ,3},
                          {4, 5, 6},
                          {7, 8, 9}};
        System.out.println(rotateMatrix(matrix));
    }

    public static List<Integer> rotateMatrix(int[][] matrix){
        List<Integer> res = new ArrayList<>();
        int row = matrix.length - 1;
        int col = matrix[0].length - 1;
        boolean[][] visited = new boolean[row + 1][col + 1];
        res.add(matrix[0][0]);
        visited[0][0] = true;
        for(int i = 0; i <= row + col; i++ ) {
            for(int j = 0; j <= row; j++){
                int curRow = j;
                int curCol = Math.abs(i-j);
                if(!visited[curRow][curCol]) {
                    res.add(matrix[curRow][curCol]);
                    visited[curRow][curCol] = true;
                }
            }
        }
        return res;
    }
}
