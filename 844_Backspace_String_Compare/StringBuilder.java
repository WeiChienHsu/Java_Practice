class Solution {
  public boolean backspaceCompare(String S, String T) {
      StringBuilder sb1 = new StringBuilder();
      StringBuilder sb2 = new StringBuilder();
      
      
      for(int i = 0; i < S.length(); i++) {
          if(S.charAt(i) == '#') {
              if(sb1.length() != 0) {
                  sb1.deleteCharAt(sb1.length() - 1);
              }
              continue;
          }
          
          sb1.append(S.charAt(i));
      }
      
      String newS = String.valueOf(sb1);
      
      for(int i = 0; i < T.length(); i++) {
          if(T.charAt(i) == '#') {
              if(sb2.length() != 0) {
                  sb2.deleteCharAt(sb2.length() - 1);
              }
              continue;
          }
          
          sb2.append(T.charAt(i));
      }
      
      String newT = String.valueOf(sb2);
      
      return newS.equals(newT);
  }
}