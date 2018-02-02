class Solution {
  public boolean isMatch(String s, String p) {
      if(s == null || p == null) return false;
      // 0 = default / 1 = true / -1 = false
      int[][] match = new int[s.length() + 1][p.length() + 1];
      helper(s, p, 0, 0, match);
      return match[s.length()][p.length()] == 1;
  }
  
  private boolean helper(String s, String p, int i, int j, int[][] match) {
      // Base Case
      if(j == p.length()) {
          match[i][j] = (i == s.length())? 1 : -1;
          return match[i][j] == 1;
      }
      
      if(match[i][j] != 0) return match[i][j] == 1;

      
      //Case1 : p[i + 1] == '*'
      if(j < p.length() - 1 && p.charAt(j+1) == '*') {
          match[i][j] =  (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') 
              && helper(s, p, i+1, j, match) || helper(s,p, i, j+2, match)) ? 1 : -1; // Match 0 or 1
          
      } else { // Case 2 : p[i + 1] != *
          match[i][j] =  (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') 
              && helper(s,p, i+1, j+1, match))? 1 : -1;
      }
      return match[i][j] == 1;
  }
}