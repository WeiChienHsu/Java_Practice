class Solution {
  public int numSquares(int n) {
      Deque<Integer> queue = new ArrayDeque<>();
      Set<Integer> used = new HashSet<>();
      queue.offerLast(0);
      used.add(0);
      int depth = 0;
      
      while(!queue.isEmpty()) {
          depth++;
          int size = queue.size();
          for(int i = 0; i < size; i++){
              int curVal = queue.pollFirst();
              for(int j = 1; j * j <= n; j++) {
                  int totalVal = curVal + j * j;
                  if(totalVal == n) {
                      return depth;
                  }
                  
                  if(totalVal > n) {
                      break;
                  }
                  
                  if(!used.contains(totalVal)) {
                      queue.offerLast(totalVal);
                      used.add(totalVal);
                  }
              }
          }
      }
      return depth;
  }
}



