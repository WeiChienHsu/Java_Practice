class Solution {
  public int integerBreak(int n) {
      if(n == 2 || n == 3) return n - 1;
      if(n == 4) return 4;
      
      int temp = n;
      int sum = 1;
      
      while(temp > 4) {
          temp -= 3;
          sum *= 3;
      }
      
      return sum * temp;
  }
}