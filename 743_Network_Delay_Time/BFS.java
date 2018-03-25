class Solution {
    
  public int networkDelayTime(int[][] times, int N, int K) {
      Map<Integer, Set<Pair>> map = new HashMap<>();
      
      // Record all sources and their dest and time in Pair
      for(int[] time : times) { 
          // time = {source, dest, time}
          
          if(!map.containsKey(time[0])){
              map.put(time[0], new HashSet<>());
          }
          
          map.get(time[0]).add(new Pair(time[1], time[2]));
      }
      
      // Record the short time to K dest in int[K]
      int[] shortTimeTo = new int[N + 1];
      Arrays.fill(shortTimeTo, Integer.MAX_VALUE);
      
      // Init the 0, K to 0
      shortTimeTo[0] = shortTimeTo[K] = 0;
      
      Deque<Integer> startPoint = new ArrayDeque<>();
      startPoint.offerLast(K);
      
      // Update the shortTimeTo value by processing each source from Queue
      // After getting a new value for shortTimeTo, add that new dest into Queue as a new source
      
      while(!startPoint.isEmpty()) {
          int curSource = startPoint.pollFirst();
          if(!map.containsKey(curSource)) {
              continue;
          }
          
          for(Pair p : map.get(curSource)) {
              int time = shortTimeTo[curSource] + p.time;
              
              if(time < shortTimeTo[p.dest]) {
                  shortTimeTo[p.dest] = time;
                  startPoint.offerLast(p.dest);
              }
          }
      }
      
      int delay = 0;
      for(int n : shortTimeTo) {
          if(n > delay) {
              delay = n;
          }
      }
      
      return delay == Integer.MAX_VALUE? -1 : delay;
  }
}

class Pair{
  int dest, time;
  public Pair(int dest, int time) {
      this.dest = dest;
      this.time = time;
  }
}