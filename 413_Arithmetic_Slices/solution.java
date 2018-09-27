class Solution {
  public int numberOfArithmeticSlices(int[] A) {
      int result = 0;
      int diff = 0;
      for(int i = 0; i < A.length - 2; i++) {
          diff = A[i + 1] - A[i];
          for(int j = i + 1; j < A.length - 1; j++) {
              if(A[j + 1] - A[j] != diff) {
                  break;
              }
              result++;
          }
      }   
      return result;
  }
}