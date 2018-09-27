class Solution {
  public int[] countBits(int num) {
      int[] digits = new int[]{0, 1, 1, 2, 1,
                               2, 2, 3, 1, 2,
                               2, 3, 2, 3, 3,
                               4};
      
      int[] results = new int[num + 1];
      
      for(int i = 0; i <= num; i++) {
          if(i < 16) {
              results[i] = digits[i];
          } else {
              results[i] = results[i / 16] + results[i % 16];
          }
      }
      return results;
  }
}