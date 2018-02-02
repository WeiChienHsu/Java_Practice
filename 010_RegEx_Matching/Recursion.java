class Solution {
  public boolean isMatch(String s, String p) {
      if(s == null || p == null) return false;
      return helper(s, p, 0, 0);
  }
  
  private boolean helper(String s, String p, int i, int j) {
      // Base Case
      if(j == p.length()) return i == s.length();
      
      //Case1 : p[i + 1] == '*'
      if(j < p.length() - 1 && p.charAt(j+1) == '*') {
          return i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') 
              && helper(s, p, i+1, j) || helper(s,p, i, j+2); // Match 0 or 1
          
      } else { // Case 2 : p[i + 1] != *
          return i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && helper(s,p, i+1, j+1);
      }
  }
}