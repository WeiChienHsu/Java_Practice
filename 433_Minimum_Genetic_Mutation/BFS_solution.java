class Solution {
  public int minMutation(String start, String end, String[] bank) {
      int level = 0;
      if(start.equals(end)) return level;
      
      Deque<String> queue = new ArrayDeque<>();
  
      Set<String> wordBank = new HashSet<>(Arrays.asList(bank));
      Set<String> visited = new HashSet<>();
      char[] charSet = new char[]{'A', 'C', 'G', 'T'};
      
      queue.offerLast(start);
      visited.add(start);
      
      while(!queue.isEmpty()) {
          int size = queue.size();
          for(int i = 0; i < size; i++) {
              String current = queue.pollFirst();
              /* Meet the end String */
              if(current.equals(end)) return level;
              
              /* Change character one by one */
              char[] currentArray = current.toCharArray();
              for(int j = 0; j < currentArray.length; j++) {
                  char originalChar = currentArray[j];
                  for(char c : charSet) {
                      currentArray[j] = c;
                      String temp = new String(currentArray);
                      /* Check if this temp String is in the bank and was not visited before */
                      if(!visited.contains(temp) && wordBank.contains(temp)) {
                          visited.add(temp);
                          queue.offerLast(temp);
                      }
                  }
                  /* Have been changed all 4 characters in the specific position and now change back */
                  currentArray[j] = originalChar;
              } /* End of characters switching */
          } /* Level Traversal */
          level ++;
      }
      return -1;
  }
}