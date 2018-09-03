class Solution {
  public int firstUniqChar(String s) {
      Map<Character, Integer> map = new LinkedHashMap<>();
      for(int i = 0; i < s.length(); i++) {
          if(map.containsKey(s.charAt(i))) {
              map.put(s.charAt(i), -1);
          } else {
              map.put(s.charAt(i), i);
          }
      }
      
      for(char c : map.keySet()) {
          if(map.get(c) != -1) return map.get(c);
      }
      return -1;
  }
}