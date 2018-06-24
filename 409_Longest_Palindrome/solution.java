public static int longestPalindrome(String s) {
  if(s.length() == 0) return 0;
  Map<Character, Integer> map = new HashMap<>();
  int len = 0;
  boolean isOdd = false;

  for(int i = 0; i < s.length(); i++) {
      map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
  }

  for(char c : map.keySet()) {


      if(map.get(c) % 2 != 0 && !isOdd) isOdd = true;
      len += (map.get(c) / 2) * 2;
  }

  return len = isOdd ? len + 1 : len ;
}