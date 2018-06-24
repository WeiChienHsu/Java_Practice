class Solution {
  public static String reverseWords(String s) {
      StringBuilder sb = new StringBuilder();
      String[] strs = s.split(" ");
      for(String str : strs) {
          sb.append(reverse(str));
          sb.append(" ");
      }
      return sb.toString().trim();
  }

  public static String reverse(String s) {
      StringBuilder sb = new StringBuilder();
      for(int i = s.length() - 1; i >= 0; i--) {
          sb.append(s.charAt(i));
      }
      return sb.toString();
  }

}