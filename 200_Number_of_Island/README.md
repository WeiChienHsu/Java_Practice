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

## BFS (boolean[][] grid 和原題 0, 1 不同)
- 首先定義座標Class
- 利用一次走四個方向的方式，將原本為island的部分變成false
- 其中，要判斷是否超出限定範圍 (inBound)
- 將所有點遍歷過一次，如果為false，不進入BFS中，判斷島嶼有幾個

### 分解
- 神奇座標： 上，下，左，右
- {-1, 1, 0, 0}
- {0, 0, -1, 1}
```
 y  0   1   2 

 0______|_X-1__|_______
        |      |
 1__Y-1_|__XY__|_Y+1___
        |      |
 2______|_X+1__|_______
 X      |      |

```


- 定義座標：
```java
class Coordinate {
    int x, y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
```

- 座標間的 BFS:
```java
private void markByBFS(boolean[][] grid, int x, int y) {
    int[] directionX = {0, 1, -1, 0};
    int[] directionY = {1, 0, 0, -1};
    
    Deque<Coordinate> queue = new ArrayDeque<>();
    
    queue.offer(new Coordinate(x, y));
    grid[x][y] = false;
    
    while(!queue.isEmpty()) {
        Coordiate coor = queue.pollFirst();
        for(int i = 0; i < 4; i++) {
            Coordinate adj = new Coordinate(
                coor.x + directionX[i];
                coor.y + directionY[i];
            );
            if(!inBound(adj, grid)) {
                continue;
            }
            
            if(grid[adj.x][adj.y]) {
                grid[adj.x][adj.y] = false;
                queue.offer(adj);
            }
        }
    }
}
```

- 判定是否超出邊界：
```java
    private boolean isBound(Coordinate coor, boolean[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        return coor.x >= 0 && coor.x < row && coor.y >= 0 && coor.y < col;
    }
```

- BFS
```java
    public int numIslands(boolean[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int row = grid.length;
        int col = grid[0].length;
        int islands = 0;
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j]){
                    markByBFS(grid,i, j);
                    islands++;
                }
            }
        }
        return islands;
    }
```

## Solution - DFS
1. 遍歷整個圖，當遇到 沒有 visited 過的，以及 '1' 的時候，小島數+1，並丟入DFS中處理
2. DFS: 當該點為 '1' 且 沒有 visited 過，處理該點：
- 標記為 visited
- 檢查邊界（上下左右），如果都在邊界內，丟入DFS處理