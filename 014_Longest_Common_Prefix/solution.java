class Solution {
  public String longestCommonPrefix(String[] strs) {
      if(strs.length == 0 || strs == null) return "";
      String newString = strs[0];
      for(int i = 0; i < strs.length; i++) {
          while(strs[i].indexOf(newString) != 0 && newString.length() > 0 ) {
              newString = newString.substring(0, newString.length() - 1);
              System.out.println(newString);
          }
      }
      return newString;
  }
}