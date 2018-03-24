

## Solution
- 先把所有邊界的點分別放入a和p的Queue當中，並標記visited，用BFS處理過所有Queue內的點，最後比對visited出現在兩個路經中的，代表都可以走到彼此的邊界陸地，加入結果。
- BFS作法：從兩邊的陸地往中間移動，如果旁邊比現在的點大或是相等，代表可以從該點流到現在的點，把該點放入Queue和visited當中。

- We need Two Queue and add all the Pacific border to one queue; Atlantic border to another queue.
- Keep a visited boolean[][] for each queue. In the end, add the cell visited by two queue to the result.
- BFS: Water flood from ocean to the cell. Since water can only flow from high/equal cell to low cell, add the neighboor cell with height larger or equal to current cell to the queue and mark as visited.

```java
Queue<int[] new{x, y}> pQueue
Queue<int[] new{x, y}> aQueue
```
```java
boolean[][] pVisited
boolean[][] aVisited
```
- Add Vertical and Horizontal boarder in Queue
```java
  for(int i=0; i<row; i++){ //Vertical border
      pQueue.offer(new int[]{i, 0});
      aQueue.offer(new int[]{i, col-1});
      pacific[i][0] = true;
      atlantic[i][col-1] = true;
  }
  for(int i=0; i<col; i++){ //Horizontal border
      pQueue.offer(new int[]{0, i});
      aQueue.offer(new int[]{row-1, i});
      pacific[0][i] = true;
      atlantic[row-1][i] = true;
  }
```
- Used helper dir to check four directions from current node
```java
int[][] dir = new int[][] {{1,0},{-1, 0}, {0, 1}, {0, -1}}
```
### How to Do BFS
- poll out the current nodes in the Queue 
- Used dir helper to check if the nodes around current node is valid
- Not Valid condition: 
- If it's valid, mark it in the visited matrix and put it into the Queue
- Until the Queue is empty
- Since water can only flow from high/equal cell to low cell, add the neighboor cell with height larger or equal to current cell to the queue and mark as visited.

```java
for(int[] d : dir) {
  int x = cur[0] + d[0];
  int y = cur[1] + d[1];
  if(x < 0 || x >= row || y < 0 || y >= col || visited[x][y] || matrix[x][y] < matrix[cur[0][cur[1]]]) {
    continue;
  }
  visited[x][y] = true;
  queue.offerLast(new int[] {x, y});
}
```

### In the End, Check if those nodes were visited twice
```java
  for(int i=0; i<n; i++){
      for(int j=0; j<m; j++){
          if(pacific[i][j] && atlantic[i][j])
              res.add(new int[]{i,j});
      }
  }
  return res;
```

## Code
```java
class Solution {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        
        List<int[]> res = new ArrayList<>();
        if(matrix.length == 0 || matrix[0] == null) {
            return res;
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        
        Deque<int[]> pQueue = new ArrayDeque<>();
        Deque<int[]> aQueue = new ArrayDeque<>();
        boolean[][] pVisited = new boolean[row][col];
        boolean[][] aVisited = new boolean[row][col];
        
        // Put All Vertical boarder into Queue and visited
        // p -> [i][0]
        // a -> [i][col - 1]
        for(int i = 0; i < row; i++) {
            pQueue.offerLast(new int[]{i, 0});
            aQueue.offerLast(new int[]{i, col - 1});
            pVisited[i][0] = true;
            aVisited[i][col - 1] = true;
        }
        
        // Put All Horizontal boarder into Queue
        // p -> [0][j]
        // a -> [row - 1][j]
        for(int j = 0; j < col; j++) {
            pQueue.offerLast(new int[]{0, j});
            aQueue.offerLast(new int[]{row - 1, j});
            pVisited[0][j] = true;
            aVisited[row - 1][j] = true;
        }
        
        // BFS two Queue
        bfsHelper(matrix, pQueue, pVisited);
        bfsHelper(matrix, aQueue, aVisited);
        
        // Check the two visited
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                // Both Queue have visited
                if(pVisited[i][j] && aVisited[i][j]) {
                    res.add(new int[] {i, j});
                }
            }
        }
        
        return res;
    }
    
    public void bfsHelper(int[][] matrix, Deque<int[]> queue, boolean[][] visited) {
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        int row = matrix.length;
        int col = matrix[0].length;
        
        while(!queue.isEmpty()) {
            // poll out the Current node
            int[] cur = queue.pollFirst();
            // Check if the nodes around current are valid
            // If Valid -> marked as visited and put it into a queue
            // Used dir helper to check
            for(int[] d : dir) {
                int x = cur[0] + d[0];
                int y = cur[1] + d[1];
                if(x < 0 || x >= row || y < 0 || y >= col || visited[x][y] || matrix[x][y] < matrix[cur[0]][cur[1]]){
                    continue;
                }
                visited[x][y] = true;
                queue.offerLast(new int[]{x, y});
            }
        }
    }
}
```