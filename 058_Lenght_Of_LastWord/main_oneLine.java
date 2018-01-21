class Solution {
  public static int lengthOfLastWord(String s) {
    return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
  }
}