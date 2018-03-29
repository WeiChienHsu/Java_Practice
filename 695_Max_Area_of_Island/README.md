# Max Area of Island
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
```
Example 1:
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
```

## DFS Solution
- Go through the grid, if met 1 and not visited before, put it into DFS Helper function
- What DFS helper function did?
- Return the value of maxArea!
- dfs(i + 1, j) + dfs(i - 1, j) + dfs(i, j + 1) + dfs(i, j - 1) + 1(current Node)
- Lists the situation that will reutrn 0 (notValid)
- NotValid condition: i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || visited[i][j]
- Marks visited[i][j] when the current Node is valid
- if currentNode == 0, return 0 as well

```java
class Solution {
  public int maxAreaOfIsland(int[][] grid) {
      int maxArea = 0;
      
      if(grid == null) return maxArea;
      int col = grid.length;
      
      if(col == 0) return maxArea;
      int row = grid[0].length;
      
      boolean[][] visited = new boolean[col][row];
      
      for(int i = 0; i < col; i++) {
          for(int j = 0; j < row; j++) {
              if(!visited[i][j] && grid[i][j] == 1 ) {
                  maxArea = Math.max(maxArea, dfsHelper(grid, i, j, visited));
              }
          }
      }
      
      return maxArea;
      
  }
  
  public int dfsHelper(int[][] g, int i, int j, boolean[][] visited) {
      
      if(i < 0 || j < 0 || i >= g.length || j >= g[0].length || visited[i][j]) return 0;
      
      visited[i][j] = true;
      
      if(g[i][j] == 0) return 0;
      
      return 1 + dfsHelper(g, i + 1, j, visited) + dfsHelper(g, i - 1, j, visited) 
               + dfsHelper(g, i, j + 1, visited) + dfsHelper(g, i, j - 1, visited);
  }
}
```