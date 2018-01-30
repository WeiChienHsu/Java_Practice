class Solution {
  public int minDistance(String word1, String word2) {
      return match(word1, word2, 0, 0);
  }
  
  public int match(String word1, String word2, int i, int j) {
      if(i == word1.length()) {
          return word2.length() - j;
      }
      if(j == word2.length()) {
          return word1.length() - i;
      }
      
      int res;
      if(word1.charAt(i) == word2.charAt(j)) {
          res = match(word1, word2, i+1, j+1);
      } else {
          // Case1 : Insert
          int insert = match(word1, word2, i, j + 1);
          // Case2 : Delete
          int delete = match(word1, word2, i + 1, j);
          // Case3 : Replace
          int replace = match(word1, word2, i + 1, j + 1);
          
          res = Math.min(Math.min(insert, delete), replace) + 1;
      }
      return res;
  }
}