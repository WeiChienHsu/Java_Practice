class Solution {
  public boolean canWin(String s) {
      /* Check for each character */
      for(int i = 0;i < s.length() - 1;i++) {
          /* Convert pair "++" into "--" */
          if(s.charAt(i) == '+' && s.charAt(i+1) == '+') {
              
              /* if opposite can not win, return true */
              if(!canWin(s.substring(0, i) + "--" + s.substring(i + 2)))
                  return true;
          }
      }
      /* There is no pair "++" in the String, return false */
      return false;
  }
}