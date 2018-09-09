/* DFS Solution */

class Solution {
  public int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
  
  public int numDistinctIslands(int[][] grid) {
      int row = grid.length;
      int col = grid[0].length;
      
      Set<List<List<Integer>>> set = new HashSet<>(); /* Store the separated islands */
      boolean[][] visited = new boolean[row][col]; /* Stroed the visited records */
      
      for(int i = 0; i < row; i++) {
          for(int j = 0; j < col; j++) {
              if(grid[i][j] == 0 || visited[i][j]) continue;
              List<List<Integer>> list = new ArrayList<>();
              if(dfsHelper(i, j, i, j, row, col, visited, grid, list)) {
                  set.add(list);
              }
          }
      }
      return set.size();
  }
  
  public boolean dfsHelper(int i0, int j0, int i, int j, int row, int col, 
                           boolean[][] visited, int[][] grid, List<List<Integer>> list) {
      if(i < 0 || j < 0 || i >= row || j >= col || grid[i][j] == 0 || visited[i][j]) return false;
      
      list.add(Arrays.asList(i - i0, j - j0)); /* Add the differences */
      visited[i][j] = true; /* Mark as visited */
      
      for(int[] dir : dirs) {
          dfsHelper(i0, j0, i + dir[0], j + dir[1], row, col, visited, grid, list);
      }
      
      return true;
  }
}