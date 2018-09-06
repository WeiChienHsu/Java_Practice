class Solution {
  public int repeatedStringMatch(String A, String B) {
      int lenA = A.length(); 
      int lenB = B.length();
      
      StringBuilder sb = new StringBuilder();
      
      int n = lenB % lenA == 0? lenB / lenA : lenB / lenA + 1;
      
      for(int i = 0; i < n; i++) {
          sb.append(A);
      }
      
      if(new String(sb.toString()).contains(B)) {
          return n;
      } else if(new String(sb.append(A).toString()).contains(B)) {
          return n + 1;
      } 
      return -1;
  }

  /* Same as contains but with different implementation  */
  public boolean AisSubstringOfB(String A, String B) {
      int lenA = A.length(); /* Shorter */
      int lenB = B.length();
      
      for(int i = 0; i < lenB - lenA + 1; i++) {
          if(B.substring(i, i + lenA).equals(A)) return true;
      }
      return false;
  }
}