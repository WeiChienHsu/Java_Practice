public static String addBinary(String a, String b) {
  StringBuilder sb = new StringBuilder();
  int aStrL = a.length() - 1;
  int bStrL = b.length() - 1;
  int carry = 0;
  while(aStrL >= 0 || bStrL >= 0) {
      int sum = carry;
      if(aStrL >= 0) sum += a.charAt(aStrL--) - '0';
      if(bStrL >= 0) sum += b.charAt(bStrL--) - '0';
      sb.append(sum % 2);
      carry = sum / 2;
  }

  if(carry != 0) {
      sb.append(carry);
  }

  return sb.reverse().toString();

}