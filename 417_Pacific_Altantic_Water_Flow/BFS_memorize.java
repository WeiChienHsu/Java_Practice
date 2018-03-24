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