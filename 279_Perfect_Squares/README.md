# Perfect Squares

## Soution
- Start from 0 in queue, keep pushing in perfect square number + cur value, once we reach number n, we found the solution
- 從 0 開始嘗試，層級遍歷，用該曾彈出的值加上每個 perfect Square(1,4,9,16....) ，得到的值的先放入queue，下一層再重複使用。

```

13

Queue 從 0開使

0 -> 1 4 9 

1 4 9 放入 Queue 和 visited , depth ++

Queue 拿出 1

1 -> 2 5 10

2 5 10 放入 Queue 和 visited, depth ++

Queue 拿出 4

4 -> 5 8 13*

-> 得到 13 return depth 
```

```java

public int numSquares(int n) {
  Queue<Integer> q = new LinkedList<>();
  Set<Integer> visited = new HashSet<>();
  q.offer(0);
  visited.add(0);
  int depth = 0;
  while(!q.isEmpty()) {
      int size = q.size();
      depth++;
      while(size-- > 0) {
          int u = q.poll();
          for(int i = 1; i*i <= n; i++) {
              int v = u+i*i;
              if(v == n) {
                  return depth;
              }
              if(v > n) {
                  break;
              }
              if(!visited.contains(v)) {
                  q.offer(v);
                  visited.add(v);
              }
          }
      }
  }
  return depth;
}
```
