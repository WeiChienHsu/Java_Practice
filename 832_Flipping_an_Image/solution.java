class Solution {
  public int[][] flipAndInvertImage(int[][] A) {
      int row = A.length;
      for(int i = 0; i < row; i++) {
          flip(A[i]);
          invert(A[i]);
      }
      return A;
  }
  
  public void flip(int[] row) {
      for(int i = 0, j = row.length - 1; i < j; i++, j--) {
          int temp = row[i];
          row[i] = row[j];
          row[j] = temp;
      }
  }
  
  public void invert(int[] row) {
      for(int i = 0; i < row.length; i++) {
          row[i] = row[i] == 0 ? 1 : 0;
      }
  } 
}