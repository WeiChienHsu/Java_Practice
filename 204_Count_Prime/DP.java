class Solution {
  public int countPrimes(int n) {
      boolean[] notPrime = new boolean[n];
      int count = 0;
      int bound = (int)Math.sqrt(n);
      for(int i = 2; i < n ; i++) {
          if(!notPrime[i]) {
              count++;
              if( i <= bound) {
                  for(int j = i*i ; j < n; j += i) {
                      notPrime[j] = true;
                  }
              }
          }
      }
      return count;
  }
}