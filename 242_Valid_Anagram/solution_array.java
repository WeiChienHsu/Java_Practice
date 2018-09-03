class Solution {
  public boolean isAnagram(String s, String t) {
      int[] alph = new int[26];
      for(int i = 0; i < s.length(); i++) alph[s.charAt(i) - 'a'] ++;
      for(int i = 0; i < t.length(); i++) alph[t.charAt(i) - 'a'] --;
      for(int i = 0; i < alph.length; i++) if(alph[i] != 0) return false;
      return true;
  }
}