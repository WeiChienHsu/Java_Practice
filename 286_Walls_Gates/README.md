# Wall and Gates
- Find Shortest Distance between Two Nodes
```
INF     -1      0      INF

INF    INF     INF     -1

INF     -1     INF     -1

0       -1     INF     INF

INF = Any number stands for distance to the Gate
-1 = WALL
0  = Gate
```

## Solution
- Count the distance from Date to any point
- Use BFS to conut the distacne near by 
```
room[x+1][y]
room[x][y+1]
room[x-1][y]
room[x][y-1]
```
Chcek if the point is Valid
```java
priate boolean isValid(int x, int y, int row, int col) {
  return x >= 0 && y >= 0 && x < row && y < col;
}
```
- Update the distance if there is a smaller number
```
(if (dis(next) > dis(cur) + 1))
```
From 

```

3     -1      0     INF

2     3       4     -1

1     -1      5     -1

0     -1      6      7

```
```
3     -1      0      1

2     2       1     -1

1     -1      2     -1

0     -1      3      4
```

```java
private void bfsHelper(int[][] rooms, int i, int j) {
  int row = rooms.length;
  int col = rooms[0].lenght;
  Deque<int[]> queue = new ArrayDeque<>();
  queue.offer(new int[]{i,j});
  while(!queue.isEmpty()) {
    int[] cur = queue.pollFirst();
    int x = cur[0];
    int y = cur[1];

    if (isValid(x+1, y, row, col) && rooms[x+1][y] > rooms[x][y] + 1) {
      rooms[x+1][y] = room[x][y] + 1;
      queue.offerLast(new int[] {x+1, y});
    }
    if (isValid(x, y+1, row, col) && rooms[x][y+1] > rooms[x][y] + 1) {
      rooms[x][y+1] = room[x][y] + 1;
      queue.offerLast(new int[] {x, y+1});
    }
    if (isValid(x-1, y, row, col) && rooms[x-1][y] > rooms[x][y] + 1) {
      rooms[x-1][y] = room[x][y] + 1;
      queue.offerLast(new int[] {x-1, y});
    }
    if (isValid(x, y-1, row, col) && rooms[x][y-1] > rooms[x][y] + 1) {
      rooms[x][y-1] = room[x][y] + 1;
      queue.offerLast(new int[] {x, y-1});
    }
  }

  private boolean isValid(int x, int y, int row, int col) {
    return x >= 0 && y >= 0 && x < row && y < col;
  }
}
```