public static String reverseVowels(String s) {
  char[] str = s.toCharArray();

  int start = 0, end = s.length() - 1;

  while (start < end) {
      while(!isVowel(str[start]) && start < end){
          start++;
      }

      while (!isVowel(str[end]) && start < end) {
          end--;
      }

      if(start < end) {
          // Swap char in start and end
          char temp = str[start];
          str[start] = str[end];
          str[end] = temp;
          start++ ;
          end-- ;
      }

  }
  return new String(str);
}

public static boolean isVowel(char c) {
  return "aeiouAEIOU".indexOf(c) >= 0;
}