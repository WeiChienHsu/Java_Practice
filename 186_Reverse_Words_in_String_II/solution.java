class Solution {
  public void reverseWords(char[] str) {
      reverseCharArray(str, 0, str.length - 1);
      int start = 0;
      int end = 0;
      
      for(int i = 0; i < str.length; i++) {
          if(str[i] == ' ') {
              end = i - 1;
              reverseCharArray(str, start, end);
              start = i + 1;
          }
      }
      
      reverseCharArray(str, start, str.length - 1);
  }
  
  public void reverseCharArray(char[] str, int start, int end) {
      for(int i = start, j = end; i < j; i++, j--) {
          char temp = str[i];
          str[i] = str[j];
          str[j] = temp;
      }
  }
}