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

### String Switch & 找 Vowels

非常好去辨別母音的方式，就是將母音放在一個String裡面，然後把char拿進去找，indexOf(char)，如果return -1 代表不在String裡面。

如果要Switch String 裡面的 character ， 要使用 String string.toCharArray() 讓他變成一個 char[]。

```java
// In order to Switch character in String
char[] str = s.toCharArray();
char temp =  str[i]
str[i] = str[j]
str[j] = temp

// Find the Vowel
public static boolean isVowel(char c) {
    return "aeiouAEIOU".indexOf(c) >= 0;
}
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
## 字串 DFS 處理問題

- [079 Word Seatch - String DFS](../079_Word_Search/)

- 注意條件判斷，容易出錯。
- 使用 currentIndex == string.length() 來代表順利 traversal 結束，應該return true.
- 因為 dfsHelper 不是 void Type，要考慮 return 值，丟入 dfs 當中的鄰居亦會產生 return value
- 過去 visited 過的 char，如果離開的原本的起點，還會繼續被使用，所以要做backtracking處理
- String.length() 以及 const int array.length 區別

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

## Graph 特殊題

順時針與逆時針旋轉圖。

順時針：將上下row依次swap，並且針對[對角]的Node進行swap，關鍵在使用 i = 0, j = i + 1 的 double for loop

```java
/* Clockwise Rotate */
public void rotate(int[][] matrix) {
    if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
    int rows = matrix.length;
    int cols = matrix[0].length;
    for(int first=0, last=rows-1; first<last; first++,last--) {
        int[] tmp = matrix[first];
        matrix[first] = matrix[last];
        matrix[last] = tmp;
    }
    for(int i = 0; i < rows; i++){
        for(int j = i+1; j < cols; j++){
            int tmp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = tmp;
        }
    }
}

/* Counter-clockwise Rotate */
public void antiRotate(int[][] matrix) {
    if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
    int rows = matrix.length;
    int cols = matrix[0].length;
    for(int first=0, last=cols-1; first<last; first++,last--) {
        for(int i=0; i<matrix.length; i++) {
            int tmp = matrix[i][first];
            matrix[i][first] = matrix[i][last];
            matrix[i][last] = tmp;
        }
    }
    for(int i = 0; i < rows; i++){
        for(int j = i+1; j < cols; j++){
            int tmp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = tmp;
        }
    }
}
```

***
## DP 

#### 基本思想与分治法类似，也是将待求解的问题 

- 分解为若干个子问题（阶段） 
- 按顺序求解子问题， 
- 前一个（或前有限个，一般不超过三个）子问题的解，为后一子问题的求解提供了有用的信息。 
- 在求解子问题时，需要求得各种可能的解。这些解是累计关系，是当前子问题的最终解。 
- 依次解决各子问题，最后一个子问题的解就是初始问题的解。 

动态规划的子问题可以用来判定结果的最短子问题，或是前后两个，或是前后三个，总之是有限个。 
由于动态规划解决的问题多数有重叠子问题这个特点，为减少重复计算，对每一个子问题只解一次， 
可将子问题及其输出保存在一个二维数组中。 

#### 与分治法最大的差别是：

适合于用动态规划法求解的问题，经分解后得到的子问题往往不是互相独立的， 
即下一个子问题的求解是建立在上一个（几个）子问题的解的基础上进行求解。 


### 动态规划过程 

每次决策需要依赖于以前的状态，随即又产生新的状态。 
每步都选择最优状态，但是其它状态也需要保留，以便为后面的决策提供依据。 
一个决策序列就是在变化的状态中产生出来的，所以这种多阶段最优化决策解决问题的过程就称为动态规划。 

[1、Best Time to Buy and Sell Stock -- LeetCode](http://blog.csdn.net/linhuanmars/article/details/23162793)

[2、Best Time to Buy and Sell Stock II -- LeetCode](http://blog.csdn.net/linhuanmars/article/details/23164149)

[3、Best Time to Buy and Sell Stock III -- LeetCode](http://blog.csdn.net/linhuanmars/article/details/23236995) 

[4. Best Time to Buy and Sell Stock with Cooldown](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown)


***

## Linked List

### Reverse LinkedList

使用 prev, cur 以及 每次操作前加入的 next 指針來反轉鍊錶。

```java
public static ListNode reverseLinkedList(ListNode root){
    ListNode pre = null;
    ListNode cur = root;
    while(cur != null) {
        ListNode next = cur.next;
        cur.next = pre;
        pre = cur;
        cur = next;
    }
    
    return pre;     
}
```

### Record Lenght of LinkedList

While Loop

```java
ListNode node = root;
int len = 0;
while(node != null) {
    len++;
    node = node.next;
}
```

簡化成For Loop

```java
int len = 0;
for (ListNode node = root; node != null; node = node.next)
    len++;
```



***

## Example
[example](./example.java)



***
## 必刷題

#### 79 Word Search
練習較多判斷條件的DFS以及String處理

#### 130 Surrounded Regions
練習 BFS(Queue) 與 DFS (Recursion || Stack) 解法

#### 392 Is Subsequence (+ 792. Number of Matching Subsequences)
練習從Two Pointer優化到BinarySearch的思維，小Input到大Input的Fellow-up Question

#### 406 Queue Reconstruction by Height
練習自己寫一個 Linked List 結構以及Override compare Comparator的題目。

#### 022 Generate Parentheses
#### 017 Letter Combination of Phone Number
練習應用類型的 DFS Combination 題目，有別於一般只處理單一 List 。

#### 093 Restore IP Addresses
練習切數字問題，以及判斷條件

#### 445 Add Two Numbers II
練習進位(carry)問題、反轉鍊錶(reverse LinkedList)、Stack操作（不改變input LinkedList)，相同時間複雜度，不同的trade-off。

#### 043 Multiply Strings
#### 415 Add Strings
練習處理將String轉換成Integer(不使用Bulit-in Function)的運算。
乘法原理是要處理每一位之間的運算與進位，加法原理只需要處理相同位數與進位的運算。

#### 685 Find K Closest Elements
練習 Collections.sort() 改寫，並瞭解 Arrays.sort 與 Collections.sort (前者ArrayList, 後者 int[]) 的差異，處理 BinarySearch 的額外要求。

***

# Time Complexy Cheat Sheet

|Data Structure | Time  |        |           |        |       |        |           |        |           Space  |
|:-------------:|:-----:|:------:|:---------:|:------:|:-----:|:------:|:---------:|:------:|:----------------:|
|               |Average|        |           |        | Worst |        |           |        | Worst            |
|               |Indexing | Search | Insertion | Delete |Indexing | Search | Insertion | Delete |                  |
| Basic Array   | O(1)  | O(n)   |     -      | -  | O(1)  | O(n)   | -      | -          | O(n)             |
| Dynimic Array | O(1)  | O(n)   | O(n)      | O(n)   | O(1)  | O(n)   | O(n)      | O(n)   | O(n)             |
| Stack         | O(n)  | O(n)   | O(1)      | O(1)   | O(n)  | O(n)   | O(1)      | O(1)   | O(n)             |
| Queue         | O(n)  | O(n)   | O(1)      | O(1)   | O(n)  | O(n)   | O(1)      | O(1)   | O(n)             |
|Single LinkedList| O(n)  | O(n)   | O(1)      | O(1)   | O(n)  | O(n)   | O(1)      | O(1)   | O(n)             |
|Double LinkedList| O(n)  | O(n)   | O(1)      | O(1)   | O(n)  | O(n)   | O(1)      | O(1)   | O(n)             |
| Hash Table    | N/A  | O(1)   | O(1)      | O(1)   | N/A  | O(n)   | O(n)      | O(n)   | O(n)             |
| BST           | O(logN)  | O(logN)    | O(logN)  | O(logN) | O(n)  | O(n)   | O(n)      | O(n)   | O(n)    |
| Red Black Tree| O(logN)  | O(logN) | O(logN) | O(logN)  | O(logN)  | O(logN) | O(logN) | O(logN)   | O(n)      |
| AVL Tree      | O(logN)  | O(logN) | O(logN) | O(logN)  | O(logN)  | O(logN) | O(logN) | O(logN)   | O(n)      |


***

## A company 題目
|No| Question | Acceptnce | Diffs | My Solution | Finish |
|:-:|:-------:|:---------:|:------:|:---------:|:--:|
|1 |Two Sum  |37.3% |Easy| | V|
|2 |Add Two Numbers| 28.5% |Medium| |V |
|3 |Longest Substring Without Repeating Characters| 24.6%| Medium| |V|
|5 |Longest Palindromic Substring |25.2% |Medium| |V|
|8 |String to Integer (atoi) | 14.0% |Medium| | V|
|15| 3Sum |21.8%| Medium| |V|
|17| Letter Combinations of a Phone Number |36.1% |Medium| | V |
|20| Valid Parentheses |33.9% |Easy| | V |
|21| Merge Two Sorted Lists  |40.5%| Easy| | V|
|23| Merge k Sorted Lists  |28.0%| Hard| |V|
|42| Trapping Rain Water | 37.5% |Hard| |
|48| Rotate Image  |41.2% |Medium| | V|
|49| Group Anagrams |37.8%| Medium| | V|
|73| Set Matrix Zeroes|  36.5% |Medium| |V|
|78| Subsets  |44.0%| Medium| Important | V |
|89| Gray Code |42.3%| Medium| |
|98| Validate Binary Search Tree | 24.0% |Medium| |
|102| Binary Tree Level Order Traversal |42.0%| Medium| | V|
|119| Pascal's Triangle II  |38.0% |Easy| | V|
|121| Best Time to Buy and Sell Stock |42.7%| Easy| | V|
|126| Word Ladder II | 14.8%| Hard| | V|
|127| Word Ladder |19.9%| Medium| | V|
|138| Copy List with Random Pointer |25.9% |Medium| | V|
|139| Word Break| 31.3% |Medium| | V|
|141| Linked List Cycle | 35.1% |Easy| | V|
|146| LRU Cache |19.5%| Hard| |
|151| Reverse Words in a String | 20% | Medium | | V | 
|155| Min Stack  |30.7% |Easy| | V|
|160| Intersection of Two Linked Lists |30.9% |Easy| | V |
|167| Two Sum II - Input array is sorted |47.3% |Easy| | V |
|186| Reverse Words in String II | 20.0 % | Medium | | V |
|189| Rotate Array  |25.2%| Easy| | V |
|199| Binary Tree Right Side View |42.2% |Medium| | V |
|200| Number of Islands |36.3% |Medium| | V|
|204| Count Primes| 26.6%| Easy| |V|
|206| Reverse Linked List| 46.6%| Easy| | V|
|215| Kth Largest Element in an Array |40.4%| Medium| | V|
|234| Palindrome Linked List |33.4%| Easy| | V|
|235| Lowest Common Ancestor of a Binary Search Tree |39.7% |Easy| | V|
|236| Lowest Common Ancestor of a Binary Tree |30.0%| Medium| | V|
|238| Product of Array Except Self | 50.2% |Medium| |V|
|239| Sliding Window Maximum  |34.1% |Hard| |
|240| Search a 2D Matrix II |39.1% |Medium| |V|
|242| Valid Anagram |47.3%| Easy| | V|
|297| Serialize and Deserialize Binary Tree |34.6%| Hard| |
|380| Insert Delete GetRandom O(1)| 39.8%| Medium| | V |
|387| First Unique Character in a String |47.2% |Easy| | V|
|535| Encode and Decode TinyURL |73.9%| Medium| |
|617| Merge Two Binary Trees |67.5% |Easy| | V|
|682| Baseball Game |58.1% |Easy| | 
|746| Min Cost Climbing Stairs| 43.4% |Easy| | V|