class Solution {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
      Set<String> dict = new HashSet<>(wordList);  // 記得要改成 Set ， 縮短查詢時間 O(n) -> O(1)
      Set<String> visited = new HashSet<>();
      Deque<String> queue = new ArrayDeque<>();
      int level = 1;
      if(beginWord.equals(endWord)) return level;
      
      queue.offerLast(beginWord);
      visited.add(beginWord);
      
      while(!queue.isEmpty()) {
          
          int size = queue.size();
          for(int i = 0; i < size; i++){
              String curWord = queue.pollFirst();
              if(curWord.equals(endWord)){
                  return level;
              }
              switchWord(queue, curWord, endWord, dict, visited);
          }
          level++;
      }
      return 0;
  }
  
  private void switchWord(Deque<String> queue, String startWord, String endWord, Set<String> dict, Set<String> visited ){
      for(int i = 0; i < startWord.length(); i++){
          StringBuilder sb = new StringBuilder(startWord);
          for(char c = 'a'; c <= 'z' ; c ++){
              if(startWord.charAt(i) == c) continue;
              sb.setCharAt(i,c);
              String word = sb.toString();
              if(dict.contains(word) && !visited.contains(word)){
                  queue.offerLast(word);
                  visited.add(word);
                 }
              }
          }
      }
}