class Solution {
  public int hammingDistance(int x, int y) {
      int result = 0;
      int n = x ^ y;
      String nBinary = Integer.toBinaryString(n);
      for(int i = 0; i < nBinary.length(); i++) {
          result += nBinary.charAt(i) == '1' ? 1 : 0;
      }
      return result;
  }
}