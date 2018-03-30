# DFS
Recursion
Combination
Permutation 排列問題
Graph
Non-Recursion

## 什麼時候使用BFS
- 圖的聯通 / 圖的遍歷 / 拓樸排序 / 簡單圖的最短路徑

## 什麼時候使用DFS
- 找所有方案 / 找最優方案（多數是使用動態規劃）

## 組合搜索問題 Combination
問題模型：求出所有滿足條件的組合。
判斷條件：組合中的元素是順序無關的。
時間複雜度：與2^n相關

## 拆解DFS
其實是用BFS的層級思路去討論，從可能的數中挑選一個放入。
技巧：在寫DFS時，先把add和remove寫下來，中間再做DFS的recursion操作，代表著是在同一層之間的變化，recursion才是在深度上的變化

```java
combination.add(candidates[i]);
helper()
combination.remove(combination.size() - 1);
```

### Combination Sum
- There will be Duplicate numbers in List
- Input number will be duplicate [2,2,2,3] -> [2,3]
- There is a target number

## DFS Solution

```java
public void helper(int[] candidates,
int startIndex,
int remainTarget;
List<Integer> combination,
List<List<Integer>> results) {
```
- 標記使用過的數字，來去重，碰到相同的數字直接continue跳過

#### 遞歸的定義：
- 找到所有以combination開頭的那些合為 target 的組合，
- 並丟到 results 當中，其中剩餘的需要加入combination 的數何為 remainTarget，
- 並且下一個加入 combination 的數，至少從 candidates 的startIndex開始
```java
public List<List<Integer>> combinationSum(int[] candidates, int target) {
List<List<Integer>> results = new ArrayList<>();
if(candidates == null) {
return results;
}
Arrays.sort(candidates);
List<Integer> combination = new ArrayList<>();
helper(candidates, 0, target, combination, results );
return results;
```

#### 遞歸拆解：
- 一個for loop 從 startIndex開始
- 先寫combination加入與移除，代表著我們討論從7出發的第一層
- 加入recursion在中間，變動的值是remainTarget - candidates[i]
- 判斷remainTarget < candidate[i] 的狀況
- 判斷兩個duplicate數字的狀況(candidates[i] == candidates[i-1])

```java
for(int i = startIndex; i < candidates.length; i++) {
  if(remainTarget < candidates[i]) {
    break;
  }
  if(i != 0 && candidates[i] == candidates[i-1]){
    continue;
  }
  
  combination.add(candidates[i]); // [2]
  helper(candidates, i, remainTarget - candidates[i], combination, results);
  combination.remove(combination.size() - 1);
}
```
#### 遞歸的出口：

- 當 remainTarget == 0 的時候，將combination加入results中，return!

```java
if(remainTarget == 0) {
  results.add(new ArrayList<Integer>(combination));
  return;
}
```

***

## Combination II (不重複取用）
- 如何處理結果上的去重：選代表。
- 有個很重要的解題技巧，不讓前面已經選入的值，被重複選取
```
[1,1,2,3] target 3
[1,2] -> 第二個1不會再被選
```
- 用當 i > startIndex && nums[i-1] == nums[i]
- 代表現在的數字與前面相同，但跳過了前一個，這個也不會被選

```java
class Solution {
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> results = new ArrayList<>();
      if(candidates == null) {
        return results;
    }

    Arrays.sort(candidates);
    List<Integer> combination = new ArrayList<>();
    dfsHelper(combination, 0, target, candidates, results);
    return results;
}

private void dfsHelper(List<Integer> combination,
                        int startIndex,
                        int remainTarget,
                        int[] candidates,
                        List<List<Integer>> results) {
  if(remainTarget == 0) {
    results.add(new ArrayList<>(combination));
    return;
  }
  
  for(int i = startIndex ; i < candidates.length; i++ ) {
    if(startIndex != i && candidates[i] == candidates[i-1]) {
      continue;
    }
    if(remainTarget < candidates[i]) {
      break;
    }

    combination.add(candidates[i]);
    dfsHelper(combination, i + 1, remainTarget - candidates[i], candidates, results );
    combination.remove(combination.size() - 1);
    }
  } 
}
```

***

## Palindrome Partitioning

找出所有切割方案，切割問題都是組合問題
3 個字母的切割問題，等於兩個數字的選擇問題（切割在哪）
```
a1b2c
[1,2] -> a b c
[1]-> a bc
[2]-> ab c
[]-> abc
```
#### HashSet 調用 String
搜索到的時間複雜度是 O(sizeofKey) ， 並非 O(1)

#### 子字串與子序列
子字串 n^2 種 [1,2,3] : 9種 1,12,123,2,23,231,3,31,312 連續
子序列 2^n 種 [1,2,3] : 8種 [] [1] [2] [3] [1,2] [1,3] [2,3] 非連續 [1,2,3]

```java
public class subStringTest {
  public static void main(String[] args) {
    String s = "aac";
    System.out.println(partition(s));
  }

public static List<List<String>> partition(String s) {
  List<List<String>> results = new ArrayList<>();
      if(s.length() == 0 ) {
        return results;
    }

    List<String> combination = new ArrayList<>();
    dfsHelper(results, combination, 0, s);
    return results;
}

private static void dfsHelper(List<List<String >> results,
                              List<String> combination,
                              int startIndex,
                              String s) {
  if(s.length() == startIndex) {
    results.add(new ArrayList<>(combination));
    return;
  }

  for(int i = startIndex; i < s.length(); i++) {
    if(isPart(s,startIndex,i)){
      combination.add(s.substring(startIndex,i + 1));
      dfsHelper(results,combination, i + 1, s);
      combination.remove(combination.size() - 1);
    }
  }
}

private static boolean isPart(String s , int low, int high) {
  while (low < high) {
    if(s.charAt(low++) != s.charAt(high--)){
      return false;
    }
    }
  return true;
  }
}
```

*** 

# Permutations 排列問題
和 Combination 不一樣：
- 不需要一個 StartIndex 紀錄已經使用過的值
- 要判斷List裡面是否有放過要放得值（不需要額外使用Set紀錄）


```java

class Solution {
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> results = new ArrayList<>();
    if(nums == null || nums.length == 0) {
      return results;
    }

    List<Integer> permutation = new ArrayList<>();
    dfsHelper(results, permutation, nums);
    return results;
}

private void dfsHelper(List<List<Integer>> results, List<Integer> permutation, int[] nums) {
    if(permutation.size() == nums.length) {
      results.add(new ArrayList<>(permutation));
      return;
    }

    for(int i = 0; i < nums.length; i++) {
      if(permutation.contains(nums[i])) {
      continue;
    }

    permutation.add(nums[i]);
    dfsHelper(results, permutation, nums);
    permutation.remove(permutation.size() - 1);
    }
  }
}

```

***

## Permutation II
- 如果前面的數字一樣，但沒有被visited，後面的數字也不需要
```java
class Solution {
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> results = new ArrayList<>();
    if(nums == null || nums.length == 0) {
      return results;
  }

  List<Integer> permutation = new ArrayList<>();
  boolean[] visited = new boolean[nums.length];
  dfsHelper(results, permutation, visited, nums);
  return results;
}

public void dfsHelper(List<List<Integer>> results, List<Integer> permutation, boolean[] visited, int[] nums){
  if(permutation.size() == nums.length){
      results.add(new ArrayList<>(permutation));
      return;
    }

  for(int i = 0; i < nums.length; i++) {
    if(visited[i]){
      continue;
    }

    if(i != 0 && nums[i] == nums[i - 1] && !visited[i-1]){
      continue;
    }

    permutation.add(nums[i]);
    visited[i] = !visited[i];

    dfsHelper(results, permutation, visited, nums);
    visited[i] = !visited[i];
    permutation.remove(permutation.size() - 1);
    }
  }
}
```

***

# N Queens
- 有N個皇后，就會產生N個col & row
- 每個col都只能有一個Queen，斜的地方也只能有兩個皇后
(isValid判斷）
- [Q x x]
- [x x Q]
- [x Q x]
- dfs 去找 "colIndex應該在什麼位置"，如果 isValid就放進List中

#### isValid判斷方式：
- 判斷 cols.get(rowIndex) 有沒有 == colIndex(傳入的col)
- 判斷右上左下和左上右下，有無放置的Q
- col.get(rowIndex) + rowIndex = row + column
- col.get(roeIndex) - rowIndex = row - column

#### Transfer col into a String (drawChessboard):
- StringBuilder
- 兩個for循環：第一個從cols[]中拿出Q要放置的位置
- 第二個for loop 從第一個放置到最後一個，遇到Q的位置放Q，其餘放.

```java
class Solution {
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> results = new ArrayList<>();
    if(n == 0) {
      return results;
    }

    List<Integer> cols = new ArrayList<>();
    dfsHelper(results, cols, n);
    return results;
}

////////

private void dfsHelper(List<List<String>> results, List<Integer> cols, int n) {
  if(cols.size() == n) {
      results.add(drawChessboard(cols));
      return;
}

for(int colIndex = 0; colIndex < n; colIndex++) {
  if(!isValid(cols, colIndex)) {
    continue;
  }

  cols.add(colIndex);
  dfsHelper(results, cols, n);
  cols.remove(cols.size() - 1);
  }
}
////////
// rwoIndex + col.get(rowIndex) == row + column (右上左下)
// rowIndex - col.get(rowIndex) == row - column (右下左上)
// cols.get(rowIndex) == column -> 使否使用過
////////

  private boolean isValid(List<Integer> cols, int column) {
      int row = cols.size();
      for(int rowIndex = 0; rowIndex < row; rowIndex++ ){
      // 檢查這個col是否有在前面cols[]內被選用過
          if(cols.get(rowIndex) == column){
            return false;
          }

          if(rowIndex + cols.get(rowIndex) == row + column){
            return false;
          }
          // 檢查左上，右下
          if(rowIndex - cols.get(rowIndex) == row - column) {
            return false;
          }
        }

       return true;
}
//////////
// cols [0,2,1]
// 拿 0 出來， for loop: Q . .
// 拿 2 出來， for loop: . . Q
//////////
  private List<String> drawChessboard(List<Integer> cols) {
    List<String> chessboard = new ArrayList<>();
    
    for(int i = 0; i < cols.size(); i++){
      StringBuilder sb = new StringBuilder();
      for(int j = 0; j < cols.size(); j++){
        sb.append(j == cols.get(i) ? 'Q' : '.');
      }
    chessboard.add(sb.toString());
  }

  return chessboard;
}
}
```
***


## 搜索，動態規劃與二叉樹的時間複雜度通用公式

- 搜索的時間複雜： O(答案總數＋構造每個答案的時間）
Subset問題，求所有的子集，子即個數一共有2^n個，每個集合均長度是O(n)，所以時間複雜度是O(n*2^n)，同理Permutations時間複雜度是O(n*n)

- Conquer Divide : O(二叉樹結點數 + 每個節點間）
最大深度問題，每節點個數為亦算時間為O(1)，節點個數為N-> 時間O(n)

***

# Reminder
## 695. Max Area of Island
[DFS - solution](https://www.youtube.com/watch?v=R4Nh-EgWjyQ)
- 1. 簡單的 DFS(recursion) return 
```java
public int dfsHelper(int[][] g, int i, int j, boolean[][] visited) {
  
  if(i < 0 || j < 0 || i >= g.length || j >= g[0].length || visited[i][j]) return 0;
  
  visited[i][j] = true;
  
  if(g[i][j] == 0) return 0;
  
  return 1 + dfsHelper(g, i + 1, j, visited) + dfsHelper(g, i - 1, j, visited) 
            + dfsHelper(g, i, j + 1, visited) + dfsHelper(g, i, j - 1, visited);
}
```
- 2. 利用for loop 的 return 
```java
public int dfsHelper(int[][] grid, boolean[][] visited, int row, int col, int size) {
    // boundry case or visited -> return 0
    if(row < 0 || col < 0 || row >= grid.length || 
       col >= grid[0].length || visited[row][col]) {
         return 0;
    }
    
    int[][] helper = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    // Mark as visited and check if current node equal to 0 -> return 0
    visited[row][col] = true;
    if(grid[row][col] == 0) return 0;
    
    
    // Go through the four directions
    for(int[] dir : helper) {
        int newRow = row + dir[0];
        int newCol = col + dir[1];
        size += dfsHelper(grid, visited, newRow, newCol, 1);
    }
    
    return size;
}       
```

## 105 Construct Binary Tree from Preorder and Inorder Traversal
- 1. Recursion的問題，要先找規律
- 2. Array的問題很多需要找start and end point 來切割
- 3. Binary Tree會先找到一個Root，剩餘的部分分成root.left 與 root.right來解決


## 106. Construct Binary Tree from Inorder and Postorder Traversal
- 1. End: index 必須是 num.length - 1

## 129. Sum Root to Leaf Numbers
- 1. 思考樹的Recursion問題:
- 考慮每一次要傳入什麼給下層？
- 考慮每一次希望下層回傳什麼給上層？
- 上往下的時候會遇到的三種狀況，該如何反應？
- 1) 超過Leaf -> root == null
- 2) 到達了Leaf -> root.right == null && root.left == null
- 3) 中間Nodes

- 2. 此題解：
- 傳入下層Node 以及 當層總數
- 下層回傳更新後的總數給上層 (上層*10 + 當層)
- 1) 檢查是否為null，直接回傳 0，或更新
```java
if(root == null) {
    return 0;
} 

curSum =  curSum * 10 + root.val;
```
- 2) 直接回傳 更新值
```java
if(root.left == null && root.right == null) {
    return curSum;
}
```
- 3) 搜集 左節點 與 右節點 的回傳更新值 (DFS)
```java
int leftSum = dfsHelper(root.left, curSum);  
int rightSum = dfsHelper(root.right, curSum);
return leftSum + rightSum;
```

## 394. Decode String
- 1. 如何檢查char，是否為數字-> Character.isDigit(c) || 如何得到char的數值 -> Character.getNumericValue()
- 2. 如將char push進 Stack中的 StringBuilder
```java
StringBuilder sb = new StringBuilder();
Deque<StringBuilder> stringStack = new ArrayDeque<>();
stringStack.offerLast(sb);

char c = 'a';
stringStack.offerLast(stringStack.pollLast().append(c));
```
- 3. 抓到String中的char -> string.charAt(i)
- 4. char 用 "==" || string 用 equals


## 494. Target Sum
- 1. 解決DFS問題時，會有一種不需要return value，但要有個global variable紀錄結果的方式
- 2. 畫一個 strategy Tree來討論每一層之間的關係，這層到下層，如何變動？
- 3. 使用Target還是RemainTarget？ 我們要的解是在底層發生嗎？返回條件為何？
- 4. 有些題目可以減枝來優化，當符合某種條件時，繼續處理下去是沒有意義的。（這題就是remainTarget大於剩下的值可以組合成最大的數，直接reture回上層。