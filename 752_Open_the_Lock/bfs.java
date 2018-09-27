/* 
  DFS Solution:
    1. Use a Queue to record each one changed characters.
    2. If the changed string is exists in the Set, continue.
    3. If the changed string is not exist in the Set, put it into the Set and alos put it into the Queue.
    4. Level travsal: After traversing single level in the queue, increment the level as 1.
    5. Use substring to deal with changing.

    StringBuilder sb = new StringBuilder(str);

    for(int i = 0; i < 4; i++) {
      String s1 = sb.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + sb.substring(i + 1);
      String s2 = sb.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + sb.substring(i + 1);
    }

*/


class Solution {
  public int openLock(String[] deadends, String target) {
      Deque<String> queue = new ArrayDeque<>();
      Set<String> deads = new HashSet<>(Arrays.asList(deadends));
      Set<String> visited = new HashSet<>();
      
      /* Start from 0000 */
      queue.offerLast("0000");
      visited.add("0000");
      int level = 0;
      
      while(!queue.isEmpty()) {
          int size = queue.size();
          for(int i = 0; i < size; i++) {
              String currentString = queue.pollFirst();
              if(deads.contains(currentString)) continue;
              if(currentString.equals(target)) return level;
              
              /* Check one character changed String */
              StringBuilder sb = new StringBuilder(currentString);
              for(int j = 0; j < 4; j++) {
                  char c = sb.charAt(j);
                  String s1 = sb.substring(0, j) + (c == '9' ? 0 : c - '0' + 1) + sb.substring(j + 1);
                  String s2 = sb.substring(0, j) + (c == '0' ? 9 : c - '0' - 1) + sb.substring(j + 1);
                  
                  if(!visited.contains(s1) && !deads.contains(s1)){
                      queue.offerLast(s1);
                      visited.add(s1);
                  }
                  
                  if(!visited.contains(s2) && !deads.contains(s2)){
                      queue.offerLast(s2);
                      visited.add(s2);
                  }                    
              }
          }
          /* Level traverse end */
          level++;
      }
      
      return -1;
      
  }
}