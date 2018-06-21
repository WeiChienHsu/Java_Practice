class Solution {
  public String reverseStr(String s, int k) {
      StringBuilder sb = new StringBuilder();
      boolean needToReverse = true;
      
      // Corner Case
      if(k > s.length()) {
          sb.append(s);
          return sb.reverse().toString();
      }
      
      // Init index out of the for scope since we need to track the rest of our string
      int i;
      for(i = 0; i <= s.length() - k; i = i + k) {
          StringBuilder temp = new StringBuilder();
          if(needToReverse) {
              temp.append(s.substring(i, i + k)).reverse();
          } else {
              temp.append(s.substring(i, i + k));
          }
          
          sb.append(temp);
          needToReverse = !needToReverse;
      }
      
      
      // Rest of the String not larger than k, will have two situation
      if(i != s.length()) {
          if(needToReverse) {
              StringBuilder last = new StringBuilder(s.substring(i, s.length()));
              sb.append(last.reverse());  
          } else {
              sb.append(s.substring(i, s.length()));
          }
          
      }
      
      return sb.toString();
  }
}