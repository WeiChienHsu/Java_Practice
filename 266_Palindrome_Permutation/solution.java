class Solution {
  public boolean canPermutePalindrome(String s) {
      Map<Character, Integer> map = new HashMap<>();
      int count = 0;
      for(char c : s.toCharArray()) {
          map.put(c, map.getOrDefault(c, 0) + 1);
      }
      for(char c : map.keySet()) {
          if(map.get(c) % 2 != 0) count ++;
      }
      return count == 0 || count == 1;
  }
}