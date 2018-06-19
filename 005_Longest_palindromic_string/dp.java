class Solution {
  public String longestPalindrome(String s) {
      int length = s.length();
      if(length < 2 || s == null) return s;
      
      int leftLongest = 0;
      int rightLongest = 0;
      
      boolean[][] isPalindrome = new boolean[length][length];
      
      for(int right = 1; right < length; right++) {
          for(int left = 0; left < right; left++) {
              boolean isInnerWordPalindorme = isPalindrome[left + 1][right - 1] || right - left <= 2;
              
              if(s.charAt(right) == s.charAt(left) && isInnerWordPalindorme) {
                  isPalindrome[left][right] = true;
                  // If the current Left and Right point to a longer String
                  if(right - left > rightLongest - leftLongest) {
                      leftLongest = left;
                      rightLongest = right;
                  }
              }
          }
      }
      return s.substring(leftLongest, rightLongest + 1);
  }
}