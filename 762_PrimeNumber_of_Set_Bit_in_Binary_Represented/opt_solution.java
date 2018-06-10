class Solution {
  public int countPrimeSetBits(int L, int R) {
      int count = 0;
      Set<Integer> primeSet = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19));
      for(int i = L; i <= R; i++) {
          if(primeSet.contains(Integer.bitCount(i))) {
              count++;
          }
      }
      return count;
  }
}