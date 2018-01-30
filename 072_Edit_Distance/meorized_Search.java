class Solution {
  public int minDistance(String word1, String word2) {
      int[][] count = new int[word1.length()][word2.length()];
      return match(word1, word2, 0, 0, count);
  }
  
  public int match(String word1, String word2, int i, int j, int[][] count) {
      if(i == word1.length()) {
          return word2.length() - j;
      }
      if(j == word2.length()) {
          return word1.length() - i;
      }
      
      if(count[i][j] != 0) {
          return count[i][j];
      }
      
      int res;
      if(word1.charAt(i) == word2.charAt(j)) {
          res = match(word1, word2, i+1, j+1, count);
      } else {
          // Case1 : Insert
          int insert = match(word1, word2, i, j + 1, count);
          // Case2 : Delete
          int delete = match(word1, word2, i + 1, j, count);
          // Case3 : Replace
          int replace = match(word1, word2, i + 1, j + 1, count);
          
          res = Math.min(Math.min(insert, delete), replace) + 1;
      }
      count[i][j] = res;
      return res;
  }
}