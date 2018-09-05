class Solution {
  public int shortestWordDistance(String[] words, String word1, String word2) {
      Map<String, List<Integer>> map = new HashMap<>();
      for(int i = 0; i < words.length; i++) {
          String word = words[i];
          if(!map.containsKey(word)) {
              map.put(word, new ArrayList<>());
          }
          map.get(word).add(i);
      }
      
      List<Integer> word1_index = map.get(word1);
      List<Integer> word2_index = map.get(word2);
      
      int min = Integer.MAX_VALUE;
      
      for(int i = 0; i < word1_index.size(); i++) {
          int currentIndex = word1_index.get(i);
          for(int j = 0; j < word2_index.size(); j ++) {
              if(currentIndex == word2_index.get(j)) continue;
              min = Math.min(min, Math.abs(currentIndex - word2_index.get(j)));
          }
      }
      
      return min;
  }
}