/*
    Two methods that I could think of this answer:
        1. Less Add more Search : Stroed all possible words, search in the Set.
            ex(Add("bad") -> bad, .ad, b.d, ba., b.., ..d, ...  take n! n is the lenght of word)
        
        2. More Add less Search : When searching, go throgh every possible answer. Only stored the add-in word
            ex(Search("a....") -> a_____ O(n) time) 
*/


class WordDictionary {
    
  Map<Integer, List<String>> map;

  /** Initialize your data structure here. */
  public WordDictionary() {
      this.map = new HashMap<>();
  }
  
  /** Adds a word into the data structure. */
  /** Implement the backtracking algorithm */
  public void addWord(String word) {
      int hashIndex = word.length();
      if(!map.containsKey(hashIndex)) {
          map.put(hashIndex, new ArrayList<>());
      } 
      map.get(hashIndex).add(word);
  }

  
  /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
  public boolean search(String word) {
      int hashIndex = word.length();
      /* The lenght of this input word is not in the Map */
      if(!map.containsKey(hashIndex)) {
          return false;
      } else {
          List<String> targetList = map.get(hashIndex);
          /* Search on the target list */
          for(String target : targetList) {
              if(isSame(target, word)) {
                  return true;
              }
          }
      }
      return false;
  }
  
  public boolean isSame(String target, String word) {
      /* word coould have some '.' inside, just skip them and search for the next character */
      if(target.length() != word.length()) return false;
      for(int i = 0; i < target.length(); i++) {
          if(word.charAt(i) == '.') continue;
          if(word.charAt(i) != target.charAt(i)) return false;
      }
      return true;
  }
}

/**
* Your WordDictionary object will be instantiated and called as such:
* WordDictionary obj = new WordDictionary();
* obj.addWord(word);
* boolean param_2 = obj.search(word);
*/