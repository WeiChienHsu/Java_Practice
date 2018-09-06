class Solution {
  public int countSubstrings(String s) {
      int[] res = new int[]{0};
      for(int i = 0; i < s.length(); i++) {
          palindromic(s, i, i, res); /* odd */
          palindromic(s, i, i + 1, res); /* even */
      }
      return res[0];
  }
  
  public void palindromic(String s, int left, int right, int[] res) {
      while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
          left--; right++; res[0]++;
      }
  }
}