class Solution {
  public int countPrimeSetBits(int L, int R) {
      int count = 0;
      for(int i = L; i <= R; i++) {
          if(isPrime(Integer.bitCount(i))) {
              count++;
          }
      }
      return count;
  }
  
  public static boolean isPrime(int num) {
      if(num == 1) return false;
      for(int i = 2; i < num; i++) {
          if(num % i == 0) {
              return false;
          }
      }
      return true;
  }
}