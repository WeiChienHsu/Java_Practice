class Solution {
  public int minDistance(String word1, String word2) {
      if(word1 == null || word2 == null) return 0;
      if(word1.length() == 0 || word2.length() == 0) return word1.length() == 0? word2.length() : word1.length();
      int[][] match = new int[word1.length() + 1][word2.length() + 2];
      
      for(int i = 0; i <= word1.length(); i++) {
          match[i][0] = i;
      }
      
      for(int j = 0; j <= word2.length(); j++ ) {
          match[0][j] = j;
      }
      
      for(int i = 0; i < word1.length(); i++) {
          for(int j = 0; j < word2.length(); j++) {
              if(word1.charAt(i) == word2.charAt(j)) {
                  match[i+1][j+1] = match[i][j];
              } else {
                  match[i+1][j+1] = Math.min(Math.min(match[i][j], match[i][j+1]), match[i+1][j]) + 1;
              }
          }
      }
      return match[word1.length()][word2.length()];
  }
}