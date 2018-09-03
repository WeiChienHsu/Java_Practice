class Solution {
  public boolean rotateString(String A, String B) {
      if(A.length() != B.length()) return false;
      if(A.equals(B)) return true;
      for(int i = 0; i < A.length() - 1; i++) {
          StringBuilder sb = new StringBuilder(A);
          char firstWord = sb.charAt(0);
          sb.deleteCharAt(0);
          sb.append(firstWord);
          if(B.equals(sb.toString())) return true;
          A = sb.toString();
      }
      return false;
  }
}