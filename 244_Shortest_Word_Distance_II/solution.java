class WordDistance {
    
  String[] words;
  Map<String, List<Integer>> map; 

  public WordDistance(String[] words) {
      this.words = words;
      this.map = new HashMap<>();
      
      for(int i = 0; i < words.length; i++) {
            String word = words[i];
            if(!map.containsKey(word)) {
                map.put(word, new ArrayList<>());
            }
            map.get(word).add(i);
        }
  }
  
  public int shortest(String word1, String word2) {

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

/**
* Your WordDistance object will be instantiated and called as such:
* WordDistance obj = new WordDistance(words);
* int param_1 = obj.shortest(word1,word2);
*/