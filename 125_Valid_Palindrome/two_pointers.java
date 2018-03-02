class Solution {
  public boolean isPalindrome(String s) {
      if(s.length() == 0) {
          return true;
      }
      
      s = s.toLowerCase().trim();
      int start = 0;
      int end = s.length() - 1;
      System.out.println(s);
      char cHead, cTail;
      while(start <= end) {
          cHead = s.charAt(start);
          cTail = s.charAt(end);
          if(!Character.isLetterOrDigit(cHead)) {
              start++;
          } else if (!Character.isLetterOrDigit(cTail)) {
              end--;
          } else {
              if(cHead != cTail) {
                  return false;
              }
              start++;
              end--;
          }
      }
      return true;
  }
}