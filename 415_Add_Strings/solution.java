class Solution {
  public String addStrings(String num1, String num2) {
      int carry = 0;
      StringBuilder sb = new StringBuilder();
      for(int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry != 0; i--, j--) {
          int n1 = i < 0 ? 0 : num1.charAt(i) - '0';
          int n2 = j < 0 ? 0 : num2.charAt(j) - '0';
          int sum = n1 + n2 + carry;
          carry = sum / 10;
          sb.append(sum % 10);
      }
      
      return sb.length() == 0 ? "0" : sb.reverse().toString();
  }
}