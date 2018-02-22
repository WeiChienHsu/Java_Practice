class Solution {
  public boolean isPalindrome(int x) {
      if (x<0 || (x!=0 && x%10==0)) return false;
      return x == reverseNum(x);
  }
  
  private int reverseNum(int x) {
      int temp = 0;
      while(x >= 10) {
          temp += x % 10;
          temp *= 10;
          x = x / 10;
      }
      return temp + x;
  }
}