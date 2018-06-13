# 刷題總結


***

## Interval 類

### 56 Merge Intervals
Override the Comparator

- [Example code](../056_Merge_Intervals/Iteration_Comparator.java)
- [Details Description](../056_Merge_Intervals/)

#### Override
```java
class Intervals {
  int start;
  int end;
  Intervals(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

Collections.sort(intervals, new Comparator<Interval>(){
  public int compare(Intervals a1, Inteverals a2) {
    return Integer.compare(a1.start, a2.start);
    // return a1.start - a2.start (遞增)
  }
});
```
***

## StringBuilder 類別
利用 StringBuilder.toString() 以及 string.toCharArray 來處理字串問題

### 38 Count and Say
這題要使用Recursion的方式，不段的對新的字串做處理。

- [Example code](../038_Count_n_Say/stringbuilder.java)
- [Details Description](../038_Count_n_Say/)


### 注意邊界條件：
- 從index == 0 開始處理時，如果要比較 i - 1 與 i 是否相同時，我們要加入一個 i - 1 >= 0 的條件，並且在一開始 loop through char array 時，將 index == char.length 這個點也考慮進去，當 index == char.length 時，進行最後處理

```java
StringBuilder sb = new StringBuilder();
char [] c = string.toCharArray();
int count = 0;
char[] c = currentString.toCharArray();
for(int i = 0; i <= c.length; i++) {
    if(i == c.length || (i - 1 >= 0 && c[i] != c[i - 1])) {
        sb.append(count);
        sb.append(c[i - 1]);
        count = 0;
    }
    count ++;
}
return sb.toString();

```

***

## Graph 類

## 200 Number of Islands
練習使用 DFS 與 BFS 不同的解法。

- [Example code - DFS](../200_Number_of_Island/DFS.java)
- [Example code - DFS(didn't change input grid)](../200_Number_of_Island/DFS_noChangedInput.java)
- [Example code - BFS(used helper Class)](../200_Number_of_Island/BFS.java)
- [Details Description](../200_Number_of_Island/)

### DFS 處理

- 第一種邏輯：直接判斷這個input是否在邊界內
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
- 第二種邏輯：分開判斷上下左右是否都在邊界內
```java
public static void dfsHelper(int r, int c, int row, int col, char[][] grid, boolean[][] visited) {
  if(grid[r][c] == '1' && !visited[r][c]) {
      visited[r][c] = true;
      // upper
      if(r - 1 >= 0 && !visited[r - 1][c]) dfsHelper(r - 1, c, row, col, grid, visited);
      // down
      if(r + 1 < row && !visited[r + 1][c]) dfsHelper(r + 1, c, row, col, grid, visited);
      // left
      if(c - 1 >= 0 && !visited[r][c - 1]) dfsHelper(r, c - 1, row, col, grid, visited);
      // right
      if(c + 1 < col && !visited[r][c + 1]) dfsHelper(r, c + 1, row, col, grid, visited);
  } 
```

### BFS 處理
- 首先定義座標Class
- 利用一次走四個方向的方式，將原本為island的部分變成false
- 其中，要判斷是否超出限定範圍 (inBound)
- 將所有點遍歷過一次，如果為false，不進入BFS中，判斷島嶼有幾個

#### 定義座標：
```java
class Coordinate {
    int x, y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
```

#### 座標間的 BFS:
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

#### 判斷邊界
```java
private boolean isBound(Coordinate coor, boolean[][] grid) {
    int row = grid.length;
    int col = grid[0].length;
    return coor.x >= 0 && coor.x < row && coor.y >= 0 && coor.y < col;
}
```
***

## 207 Course Schedule (Topological sorting)
練習判斷有像圖中，是否有環。

- [Example code - DFS](../207_Courses_Schedule/DFS.java)
- [Example code - BFS(Best Solution)](../207_Courses_Schedule/BFS.java)
- [Details Description](../207_Courses_Schedule/)


### BFS
1. 使用一個int[]，記錄每堂課的 indegree 數量（被多少個Node指向），另外，使用另一個int[]存放 ArrayList 紀錄該堂課指向的課程。

- ex. [[1,2], [2], [0]]
- 上完 0th 課程，可以上 1 和 2，上完 1th 課程，可以上 2
```java
ArrayList[] graph = new ArrayList[numCourses];

// Init the graph 
for(int i = 0; i < numCourses; i++) {
    graph[i] = new ArrayList<Integer>();
}

// Record All indegree value of each course
// Add the Courses that prerequisite course points to 
for(int i = 0; i < prerequisites.length; i++) {
    indegree[prerequisites[i][0]]++;
    graph[prerequisites[i][1]].add(prerequisites[i][0]);
}
```


2. 將所有 indegree == 0 的課堂加入Queue當中，目的要把這些Node指向的課堂indegree數量漸漸減少

3. BFS: 將Queue當中的課堂poll出來，並利用剛才紀錄的課堂指向，將其指向的課程indegree數值 --，如果該數值也 == 0，計算該數量，並加入Queue當中繼續遍歷

4. 處理期間要計算 indegree == 0 的個數，如果最終此數量不等於課程總數（有的課程indegree > 0，代表產生”環“），return false


### DFS : 利用兩個額外Set紀錄造訪狀況
1. 使用和 BFS 一樣的list和ArrayList紀錄課程關係
2. 額外使用兩個Set，一個紀錄是否passed(無環)，一個紀錄是否造訪 visited(重複造訪產生環)
3. 遍歷整個 graph，如果 passed == truee 略過不看，沒有passed的丟入DFS中檢查是否有環，全部遍歷完畢，沒有環出現，return true
4. DFS: boolean containCircle()
- 如果passed == true 沒有環 return false
- 如果visited == true 重複造訪，有環return true
- 都沒有，進行鄰居的遍歷，依據graph裡面記錄得到該點指向的所有點，如果其中一個點有環(visited == ture)，return true
- 如果順利遍歷完都沒有環，marked passed 然後 return true

```java
public static boolean containsCircle(int course, Set<Integer> passed, Set<Integer> visited, ArrayList[] graph) {
    if(passed.contains(course)) return false;
    if(visited.contains(course)) return true;
    
    // Marked the course as visited
    visited.add(course);
    
    // Traverse all neighbors of current course
    for(int i = 0; i < graph[course].size(); i++) {
        int nextCourse = (int) graph[course].get(i);
        if(Solution.containsCircle(nextCourse, passed, visited, graph)){
            return true;
        }
    }
    // After DFS, if there is no circle means this course is passed
    passed.add(course);
    return false;
}
```

***

## Tree 類

## 113. Path Sum II 
Divide and Conquer + Backtracking
分辨有兩種處理“將Target丟到下一層"處理的算法。
Hard Copy and Soft Copy

### 將 ArrayList<Integer> list 中的值 Softcopy 到新的 ArrayList Instance當中，並且add入 List<List<Integer>> 

```java
res.add(new ArrayList<Integer>(list));
```

### 當層處理 Target ， 不需要事先判斷 root.right 和 root.left 是否為 Null

```java
public static void dfsHelper(TreeNode root, int sum, List<List<Integer>> answer, List<Integer> result) {
    if(root == null) return;
    // 讓扣除Current Value的工作在每一層中處理
    result.add(root.val);
    sum -= root.val;
    if(root.left == null && root.right == null && sum == 0) {
            List<Integer> res = new ArrayList<>(result);
            answer.add(res);
    }
    // 分治法：處理左右子樹
    dfsHelper(root.left, sum, answer, result);
    dfsHelper(root.right, sum, answer, result);
    
    // 當這層處理完畢，回到上層時，需要backtracking，將array內新加入的這層數值移出
    result.remove(result.size() - 1);
}
```
### 丟到下一層處裡 Sum

```java
private void helper(TreeNode root, int sum, List<Integer> list, List<List<Integer>> res) {
    if (root == null) return;

    // Add the number we go through
    list.add(root.val);

    if( sum == root.val && root.right == null && root.left == null ) {
        res.add(new ArrayList<Integer>(list));
    } else {
        helper(root.left, sum - root.val, list, res);
        helper(root.right, sum - root.val, list, res); 
    }

    // Remove the node value from list since we'll back to last level
    list.remove(list.size() - 1);
}
```

***


***

## Inorder Traversal


## Preorder Traversal

***

## DP 類

***

## Union Find Forest

當需要判斷該點的源頭，或是兩個點是否有相同的Root時，使用此算法（或資料結構亦可）

[Example Template](../UnionFind)
[Example 684 Redundant Connection](../684_Redundant_Connection/)
[Example 547 Friend Circles](../547_Friend_Circles)
[Example 737 Sentence Similarity II](../737_Sentence_SimilarityII)


```java
class UnionFindSet {
  private int[] ranks;
  private int[] parents;
  public UnionFindSet(int n) {
      this.ranks = new int[n + 1];
      this.parents = new int[n + 1];
      for(int i = 0; i < n + 1; i++) {
          ranks[i] = 1;
          parents[i] = i;
      }    
  }
  
  public boolean Union(int u, int v) {
      int rootU = Find(u);
      int rootV = Find(v);
      
      if(rootU == rootV) return false;
      
      if(this.ranks[rootU] > this.ranks[rootV]) {
          this.parents[rootV] = rootU;
      } else if(this.ranks[rootU] < this.ranks[rootV]) {
          this.parents[rootU] = rootV;
      } else {
          parents[rootV] = rootU;
          this.ranks[rootU]++;
      }
      
      return true;
  }
  
  public int Find(int u) {
      while(this.parents[u] != u) {
          this.parents[u] = this.parents[this.parents[u]];
          u = this.parents[u];
      }
      return u;
  }
  ```

***

## Example
[example](./example.java)



***
## 必刷題

#### 130 Surrounded Regions
練習 BFS(Queue) 與 DFS (Recursion || Stack) 解法

