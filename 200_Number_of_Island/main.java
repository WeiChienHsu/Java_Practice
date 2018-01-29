class Solution {
    
    private int n;
    private int m;
    
    public int numIslands(char[][] grid) {
        int count = 0;
        n = grid.length;
        if(n == 0) return 0;
        m = grid[0].length;
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if(grid[i][j] == '1') {
                    DFSFindIsland(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    
    public void DFSFindIsland(char[][] grid, int i, int j) {
        if(i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == '0') return;
        grid[i][j] = '0';
        DFSFindIsland(grid, i + 1, j);
        DFSFindIsland(grid, i - 1, j);
        DFSFindIsland(grid, i, j + 1);
        DFSFindIsland(grid, i, j - 1);
    }
}