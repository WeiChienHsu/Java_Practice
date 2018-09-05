public class Solution {
  // you need treat n as an unsigned value
  public int reverseBits(int n) {
      String binaryString = (Integer.toBinaryString(n));
      char[] binaryChar = binaryString.toCharArray();
      StringBuilder sb = new StringBuilder();
      for(char c : binaryChar) {
          sb.insert(0, c);
      }
      
      while(sb.length() < 32) {
          sb.append(0);
      }
      
      return (int)Long.parseLong(new String(sb), 2);
  }
}