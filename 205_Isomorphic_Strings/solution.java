class Solution {
  public boolean isIsomorphic(String s, String t) {     
      int[] s_map = new int[256 * 2];
      // int[] t_map = new int[256];
      
      for(int i = 0; i < s_map.length; i++) {
          s_map[i] = -1;
          // t_map[i] = -1;
      }
      
      for(int i = 0; i < s.length(); i++) {
          /* Chech if the same characters have the same previous existed index */
          if(s_map[s.charAt(i)] != s_map[t.charAt(i) + 256]) return false;
          s_map[s.charAt(i)] = i;
          s_map[t.charAt(i) + 256] = i;
      }
      return true;
  }
}