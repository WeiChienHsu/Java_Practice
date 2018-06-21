class Solution {
  public String multiply(String num1, String num2) {
      int[] pos = new int[num1.length() + num2.length()];
      for(int i = num1.length() - 1; i >= 0; i--) {
          for(int j = num2.length() - 1; j >= 0; j--) {
              int p1 = i + j; // 十位數
              int p2 = i + j + 1; // 個位數
              int mult = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + pos[p2]; // 補上前一個的十位數
              pos[p1] += mult / 10;
              pos[p2] = mult % 10;        
          }
      }
      
      StringBuilder sb = new StringBuilder();
      for(int n : pos) {
          if(sb.length() == 0 && n == 0) continue;
          sb.append(n);
      }
      
      return sb.length() == 0? "0" : sb.toString() ;
  }
}