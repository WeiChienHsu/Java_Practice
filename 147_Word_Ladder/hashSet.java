class Solution {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
          if (!wordList.contains(endWord)) return 0;
          Set<String> dict = new HashSet<>(wordList);
          Set<String> visited = new HashSet<>();
          Deque<String> queue = new ArrayDeque<>(1000);
  
          int count = 1;
          queue.offerLast(beginWord);
          visited.add(beginWord);
  
          while (!queue.isEmpty()) {
              int size = queue.size();
              for (int i = 0; i < size; ++ i) {
                  String head = queue.pollFirst();
                  if (head.equals(endWord)) return count;
                  for (String nextWord : getNextWords(head, dict))
                      if (visited.add(nextWord)) queue.offerLast(nextWord);
  
              }
              ++ count;
          }
          return 0;
      }
  
      private static Set<String> getNextWords(String curr, Set<String> dict) {
          Set<String> nextWords = new HashSet<String>();
          for (int i = 0; i < curr.length(); i++) {
              char[] chars = curr.toCharArray();
              for (char c = 'a'; c <= 'z'; c++) {
                  chars[i] = c;
                  String temp = new String(chars);
                  if (dict.contains(temp)) {
                      nextWords.add(temp);
                  }
              }
          }
          return nextWords;
      }
  }
  