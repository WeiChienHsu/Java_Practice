# Network Delay Time

There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.


## Solution - BFS
- 題目給了所有起點，終點，所需時間：我們要求的是從K點出發，要花多少時間才能到達“所有”的點，如果沒辦法到達所有的點(到達某一段時間 == MAX)，則回傳 -1 
- 例如 times = {{1,3,10}, {1,3,5}, {3, 2, 4}, {3, 1, 10}}, K = 1
1. 先使用一個HashMap來記錄所有 < Key:起始點, Value: Set of Pair(int des, int time)>  
2. class Pair 用來記錄 目的地與所需時間
```java
  for(int[] time : times) { 
      // time = {source, dest, time}
      
      if(!map.containsKey(time[0])){
          map.put(time[0], new HashSet<>());
      }
      
      map.get(time[0]).add(new Pair(time[1], time[2]));
  }     
```
3. int[X] dp 紀錄到達目的地 X 所需要花的時間（Arrays.fill(dp, Max)) --> default 最大值
4. 先把 K 放入 dp 數列中， dp[K] = dp[0] = 0
```java
  // Record the short time to K dest in int[K]
  int[] shortTimeTo = int[N + 1];
  Arrays.fill(shortTime, Integer.MAX_VALUE);
        
  // Init the 0, K to 0
  shortTimeTo[0] = shortTimeTo[K] = 0;
```

5. 在使用一個Queue，來放入所有可能的起始點，用來更新到達每個點的最短時間
6. 使用Queue進行BFS，每次將新的目的地放入其中，再從該目的地去找其他目的地，更新最短時間


7. 如何更新最短時間？ Queue.pollLast 出一個curSource，在Map當中找有沒有這個Key的存在，如果沒有，直接continue，結束這個點，如果有：
8. 將所有 Key 為這個curSource的點走過一遍(for Loop)
9. 比較原本到達該點所需的時間，以及前一個點到該點的時間加上原本到達前個點的時間 
10. 取得比較短的時間，更新dp[curSource]
```java
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

```

11. 最後，檢查dp[]內的值，取最大的出來當做解（因為裡面記錄的事到達每個點最小的時間，原本的default就是MaX），如果得到Max，代表該點無法到達，回傳-1

```java
  int delay = 0;
  for(int n : shortTimeTo) {
      if(n > delay) {
          delay = n;
      }
  }
  
  return delay == Integer.MAX_VALUE? -1 : delay;
```

