# Number of Island
- 1: isLand
- 0: Osean
```
1 0 1 0
1 0 0 0
0 0 1 0
```
- Got to have three island

## Solution
- 遍歷整個Matrix
- 只要遇到 1 ，就把它變成0，然後把它上下左右的點都變成 0
- DFS作法：
- 從左上角開始，一但遇到1進行BFS，將周遭的點變成0，如果已經變成0則跳過
```java
    public void DFSFindIsland(char[][] grid, int i, int j) {
        if(i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == '0') return;
        grid[i][j] = '0';
        DFSFindIsland(grid, i + 1, j);
        DFSFindIsland(grid, i - 1, j);
        DFSFindIsland(grid, i, j + 1);
        DFSFindIsland(grid, i, j - 1);
    }
```
- 成功把所有1旁邊的1變成0，紀錄一個島，繼續遍歷完整個Matrix