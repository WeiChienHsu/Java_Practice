class Solution {
  public boolean isPerfectSquare(int num) {
      int start = 1;
      int end = num;
      while(start + 1 < end) {
          long mid = start + (end - start) / 2;
          
          if(mid * mid == num) return true;
          
          if(mid * mid > num) {
              end = (int) mid;
          } else {
              start = (int) mid;
          }
      }

      return start * start == num || end * end == num;
  }
}