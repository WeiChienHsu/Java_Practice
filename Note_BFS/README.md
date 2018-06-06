# BFS - 最省時省力的算法

### 圖的遍歷 Traversal in Graph
- Level Order Traversal 層級遍歷 ： 給起點，分層次的找下去
- Connected Component 由點及面: 看A和B之間可否聯通(是否為樹）
- Topological Sorting

### 最短路徑 Shortest Path in Simple Graph(只解決邊長為1問題)
- 簡單圖的最短路徑：圖中每條「邊長都是1」，且沒有方向。
- 從A狀態變到B狀態，字符串變換，一共需要多少次變換。（每次變換在圖中行走一條邊）
- 問最短路徑，除了BFS外，還可以用DP。
- 最長路徑，肯定不是BFS。


## BFS in Binary Tree
樹是圖的一種特殊狀態。
- 層級遍歷我需要一個int size 紀錄每一層有的結點數。

```java
class Solution {
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> results = new ArrayList<List<Integer>>();
    if(root == null) {
      return results;
    }
    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.offerLast(root);
    
    while(!queue.isEmpty()) {
      List<Integer> res = new ArrayList<>();
      int size = queue.size();
      for(int i = 0; i < size; i++){
        TreeNode cur = queue.pollFirst();
        if(cur.left != null){
          queue.offerLast(cur.left);    
        }
        if(cur.right != null) {
          queue.offerLast(cur.right);
        }
        res.add(cur.val);
      }
      results.add(res);
    }
    return results;
    }
  }
```

- 用 LinkedList 實踐 Queue : get -> O(n)
- 用 ArrayList 實踐 Queue : get -> O(1)
- A函數調用B函數，A函數只關心return的結果(使用接口），不會指定具體要如何實踐。

## Binary Tree Serialization 序列化
將“內存”中結構化的數據，變成字符串的過程( Object -> String )

- 將內存中的數據持久話存儲時 (內存中重要的數據不能只待在內存，需要寫入硬盤。
- 網絡傳輸時，不可能相互去讀對方的內存，只能將數據變成字符流數據後，偷過網路傳輸過去。

## BFS in Graph (Topological Sorting)

圖是什麼？ 點(Vetex)和邊(Edge)組成的結構，不一定要全部相連。

考圖的知識，最常套用 Social Network 的問題，
- Tiwwter A 和 B 之間的關注是單向好有關係 (有向圖）
- Facebook A 和 B 之間的好友關係是雙向的 (無向圖)

#### 圖和樹的BFS有什麼差別？

樹上的關係為父子關係(Parent - Child)，圖上是地位平等的關係（A可能是B的鄰居，B也是A的鄰居）

#### HashNap 圖中存在環，意味同個節點可能重複進入Queue
BFS時，把A放入Queue內後，要把A的鄰居都放進去Queue，BC放進去後，又把C的鄰居A放入Queue，會產生問題，所以需要在BFS時，用HashMap支持一個紀錄哪些點已經處理過了。

## Graph Valid Tree
- 判斷每個點是否聯通？ 使用BFS (Connected Component)
- 判斷是否符合N個節點 N-1條邊

### Example
```
Given n = 5 and edges = [[0,1],[0,2],[0,3],[1,4]] reutrn true
Given n = 5 and edges = [[0,1],[1,2],[2,3],[1,3],[1,4]] reutrn false
```

### 轉化結構，變成適合使用 Queue + HashSet (InitializeGraph)

- 把 pair 改成方便存儲跟改變的圖狀結構（鄰接表）: [Adjacency List](https://www.geeksforgeeks.org/graph-and-its-representations/)

- 通常不常存成矩陣表，會存成這種表：最外成是個Map，內容是該點配上一個HashSet
```
edges = [[0,1],[0,2],[0,3],[1,4]]
轉變成 Graph:
graph <0> set(1,2,3)
graph <1> set(0,4)
graph <2> set(0)
graph <3> set(0)
graph <4> set(1)
```
- 使用一個Map，Key就是要被放入的Node，Value該Node對應到的鄰居

```java
private Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
  Map<Integer, Set<Integer>> graph = new HashMap<>();
    for(int i = 0; i < n; i++) {
      graph.put(i, new HashSet<>());
    }

    for(int i = 0; i < edges.length; i++) {
      int u = edges[i][0];
      int v = edges[i][1];
      graph.get(u).add(v);
      graph.get(v).add(u);
    }
}
```

## Flood Fill 灌水法

Queue 與 HashSet 配合用！
- 從頭到尾走過一次neighbor，只要hash內有的，代表已經處理過，每個丟入queue處理後，同時丟到hash！

```java
Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);

Queue<Integer> queue = new ArrayDeque<>(); // 處理點
Set<Integer> hash = new HashSet<>(); // 紀錄使用過的點

queue.offerLast(0);
hash.add(0);

  while(!queue.isEmpty()) {
    int node = queue.pollFirst();
    for(Integer neighbor : graph.get(node)){
      if(hash.contains(neighbor)) {
        continue;
      }
    
    queue.offerLast(neighbor);
    hash.add(neighbor);
    }
  }
  return (hash.size() == n); // 是否所有點都被記錄了

```

## Soft Copy - 只 Copy Reference 沒有 內容
List<Integer> list1 = new ArrayList<>();
List<Integer> list2 = list1;

如果把 list1 刪除， list2指向的Object也會被刪除。
如果在 list1.add(1) ， list2內會增加1

## Hard Copy - 將內容傳入
List<Integer> list1 = new ArrayList<>();
List<Integer> list3 = new ArrayList<>(list1);

如果把list1刪除，list3不受影響
如果在list1.add(1)，list3不受影響

## Clone Graph ( 訓練 code Structure )

- return 的新Node在內存中存儲不同位置
- UndirectiedGraphNode
```java
class UndirectedGraphNode {
  int label;
  List<UndirectedGraphNode> neighbors;

  UndirectedGraphNode(int x) {
  label = x;
  neighbors = new ArrayList<UndirectedGraphNode>();
  }
};
```

不要把所有功能寫在同一個function內，步驟明確：
1. 只給了一個點，要如何透過該點找到「所有點」： 從 node 出發，找到所有點 （node -> nodes)
2. 複製所有的點，成為一個新的圖 (copy nodes)
3. 原本圖中有連接的部分，到了新圖中也要連接 (copy edges)
4. return 的值為map.get(node)，Map中對應的node

```java
public class Solution {
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
   
    if(node == null) {
      return node;
  }

// Use bfs to traverse the graph and get all nodes
ArrayList<UndirectedGraphNode> nodes = getNodes(node);

// copy nodes, store the old -> new mapping information in a hash map
HashMap<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();

for(UndirectedGraphNode n : nodes) {
    mapping.put(n, new UndirectedGraphNode(n.label));
}


// copy neighbors(edges)
for(UndirectedGraphNode n : nodes) {
    UndirectedGraphNode newNode = mapping.get(n);

    for(UndirectedGraphNode neighbor : n.neighbors) {
        UndirectedGraphNode newNeighbor = mapping.get(neighbor);
        newNode.neighbors.add(newNeighbor);
    }
  }
return mapping.get(node);
}


private ArrayList<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
    Deque<UndirectedGraphNode> queue = new ArrayDeque<UndirectedGraphNode>();
    HashSet<UndirectedGraphNode> set = new HashSet<>();

    queue.offer(node);
    set.add(node);
    
    while(!queue.isEmpty()){
        UndirectedGraphNode head = queue.pollFirst();
        for(UndirectedGraphNode neighbor : head.neighbors) {
            if(!set.contains(neighbor)) {
              set.add(neighbor);
              queue.offerLast(neighbor);
            }
        }
    }
    return new ArrayList<UndirectedGraphNode>(set);
  }
}
```


### BFS！
能夠使用BFS解決的問題，一定不要用DFS，因為Recursion實現DFS可能會造成StackOverflow。


## Topological Sorting 拓樸排序

- 1. 計算每個點的 indegree
- 2. 取讀 startNode

```java
public ArrayList<DirectdGraphNode> topSort(ArrayList<DirectdGraphNode> graph) {
  ArrayList<DirectdGraphNode> order = new ArrayList<>();

  if(graph == null) {
    return null;
  }

  // 1. count indegree
  Map<DirectdGraphNode, Integer> indegree = getIndegree(graph);

  // 2. Top Sorting - Put all start Nodes into startNodes List
  ArrayList<DirectdGraphNode> startNodes = getStartNodes(indegree, graph);
  
  // 3. BFS -> get order
  order = bfs(indegree, startNodes);

  if(order.size() == graph.size()) {
    return order;
  }

  return null;
}
```


### 先找InDegree
有多少邊指向這個點 : Count indegree

```java
public Map<DirectdGraphNode, Integer> indegree(ArrayList<DirectdGraphNode> graph) {
  Map<DirectdGraphNode, Integer> indegree = new HashMap<>();
  for(DirectdGraphNode node : graph) {
    for(DirectdGraphNode neighbor : node.neighbors) {
          indegree.put(neighbor, indegree.getOrDefault(neighbor,0) + 1);
      }
    }
    return indegree;
  }
```
### 取得startNode
把所有的StartNode拿到（indegree為0），然後丟到queue裡面進行bfs： 訪問過所有的鄰居，被訪問的map中Integer -1，接著把其他 Indegree 為 0 的node放入Queue當中。 （ 同時紀錄 order )

```java
public ArrayList<DirectdGraphNode> getStartNodes(Map<DirectdGraphNode,Integer> indegree,ArrayList<DirectdGraphNode> graph ) {
    ArrayList<DirectdGraphNode> nodes = new ArrayList<>();
    for(DirectdGraphNode node : graph) {
      if(indegree.get(node) == 0){
        nodes.add(node);
      }
    }
    return nodes;
}
```

### 將 Indegree == 0 的Node，丟入Queue中，並造訪其鄰居
- 遍歷所有Indegree == 0的點，放入Order與Queue內
- 對Queue內的點進行BFS遍歷
- 其鄰居的indegree - 1，並且將 indegree為0的點放入Order和Queue內
- 直到Queue沒有點為止

```java
// BFS，把indegree == 0的點丟入Queue中，造訪鄰居
public ArrayList<DirectdGraphNode> bfs(Map<DirectdGraphNode,Integer> indegree, ArrayList<DirectdGraphNode> startNodes){
  ArrayList<DirectdGraphNode> order = new ArrayList<>();
  Deque<DirectdGraphNode> queue = new ArrayDeque<>();
  for(DirectdGraphNode node : nodes) {
    order.add(node);
    queue.offerLast(node);
  }

  while(!queue.isEmpty()) {
    DirectdGraphNode cur = queue.pollFirst();
    for(DirectdGraphNode neighbor : cur.neighbors) {
      indegree.put(neighbor, indegree.get(neighbor) - 1);
      if(indegree == 0) {
        order.add(neighbor);
        queue.offerLast(neighbor);
      }
    }
  }
  return order;
}

```


### 補充：檢查是否有環？

```java
class Solution {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
      ArrayList[] graph = new ArrayList[numCourses];
      int[] degree = new int[numCourses];
      Deque<Integer> queue = new ArrayDeque<>();
      int count = 0;

      // Init the Graph
      for(int i = 0; i < numCourses; i++) {
        graph[i] = new ArrayList();
      }

      // Init the Level
      for(int i = 0; i < prerequisites.length; i++){
          degree[prerequisites[i][0]]++;
          graph[prerequisites[i][1]].add(prerequisites[i][0]);
      }

      for(int i = 0; i < degree.length; i++) {
          if(degree[i] == 0) {
              queue.offerLast(i);
              count++;
          }
      }

      // Traversal all neighbors

      while(!queue.isEmpty()) {
          int course = queue.pollFirst();
          for(int i = 0; i < graph[course].size(); i++) {
              int pointer = (int) graph[course].get(i);
              degree[pointer]--;
              if(degree[pointer] == 0) {
                  queue.offerLast(pointer);
                  count++;
              }
          }
      }

  return count == numCourses? true : false;
  }
}
```

## 矩陣 vs 圖
### 圖 Graph
N個點，M條邊
M最大是O(n^2)的級別
圖上BFS的時間複雜度 = O(N+M)
最壞的情況可能是O(n^2)

### 矩陣 Matrix
R行C列
R x C個點，R x C x 2 條邊
矩陣中BFS時間複雜度為 O(R*C)

## 座標變換數組
int[] deltaX = {1,0,0,-1}
int[] deltaY = {0,1,-1,0}

```
y 0 1 2

0______|_X-1__|_______
| |
1__Y-1_|__XY__|_Y+1___
| |
2______|_X+1__|_______
X | |

```



## Number of Islands

- 首先定義座標Class
- 利用一次走四個方向的方式，將原本為island的部分變成false
- 其中，要判斷是否超出限定範圍 (inBound)
- 將所有點遍歷過一次，如果為false，不進入BFS中，判斷島嶼有幾個

```java
class Solution {
    public int numIslands(char[][] grid) {
    if(grid == null || grid.length == 0 || grid[0].length == 0) {
        return 0;
    }
    
    int row = grid.length;
    int col = grid[0].length;
    int islands = 0;

    for(int i = 0; i < row; i++) {
        for(int j = 0; j < col; j++) {
           if(grid[i][j] == '1'){
            markByBFS(grid,i, j);
            islands++;
          }
        }
    }
    return islands;
}

private void markByBFS(char[][] grid, int x, int y) {
    int[] directionX = {0, 1, -1, 0};
    int[] directionY = {1, 0, 0, -1};

    Deque<Coordinate> queue = new ArrayDeque<>();
    queue.offer(new Coordinate(x, y));
    grid[x][y] = '0';
   
    while(!queue.isEmpty()) {
      Coordinate coor = queue.pollFirst();
      for(int i = 0; i < 4; i++) {
          Coordinate adj = new Coordinate(
          coor.x + directionX[i],
          coor.y + directionY[i]
          );

          if(!isBound(adj, grid)) {
              continue;
          }
          if(grid[adj.x][adj.y] == '1') {
              grid[adj.x][adj.y] = '0';
              queue.offer(adj);
          }
      }
    }
}

private boolean isBound(Coordinate coor, char[][] grid) {
    int row = grid.length;
    int col = grid[0].length;
    return coor.x >= 0 && coor.x < row && coor.y >= 0 && coor.y < col;
  }
}

class Coordinate {
    int x, y;
    public Coordinate(int x, int y) {
        this.x = x;
          this.y = y;
    }
}
```

## 包圍島問題

- Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

- A region is captured by flipping all 'O's into 'X's in that surrounded region.

### For example
```
X X X X
X O O X
X X O X
X O X X
```
After running your function, the board should be:
```
X X X X
X X X X
X X X X
X O X X
```

## Solution - BFS
- 1. Give a Class of Points{int x, int y} to record the Node in Queue
- 2. Traversal all Edges to transfer all 'O' into '+' and also add them into a Queue
- 3. Now, we got all Edges with 'X' or '+'
- 4. Base on the '+' in the edges, we use Queue to do a BFS to all the '+'
- 5. Transfer those 'O' around(up, bottom, left and right) into '+' as well
- 6. How to use BFS? 
- Check the vaild (row - 1 >= 0) (row + 1 < rowN) (col - 1 >= 0) (col + 1 < colN)
- If broad[row-1] == 'O' , change it into 'X' and Add into the Queue
- 7. When the Queue is Empty, means we have already traversal all Nodes the start from edge O
- 8. Traversal all Nodes again: Change '+' into O, change 'O' into 'X'
- (Which means we couldn't change those 'O' that aren't be touched by edged O)


```java
class Solution {
    public void solve(char[][] board) {
        
        if(board.length == 0){
            return;
        }
        
        Deque<Point> queue = new ArrayDeque<>();
        int rowN = board.length;
        int colN = board[0].length;
        
        if(colN == 0) {
            return;
        }
        
        // Row Edges
        for(int i = 0; i < rowN; i++ ) {
            if(board[i][0] == 'O'){
                board[i][0] = '+';
                queue.offerLast(new Point(i, 0));
            }
            
            if(board[i][colN - 1] == 'O'){
                board[i][colN - 1] = '+';
                queue.offerLast(new Point(i, colN - 1));
            }
        }
            
        // Col Edges
        for(int j = 0; j < colN; j++) {
            if(board[0][j] == 'O'){
                board[0][j] = '+';
                queue.offerLast(new Point(0, j));
            }
            
            if(board[rowN - 1][j] == 'O'){
                board[rowN - 1][j] = '+';
                queue.offerLast(new Point(rowN - 1, j));
            }
        }
        
        // BFS all '+' in queue to change all 'O' met by '+'
        while(!queue.isEmpty()){
            
            Point cur = queue.pollFirst();
            int row = cur.x;
            int col = cur.y;
            
            // Upside
            if(row - 1 >= 0 && board[row - 1][col] == 'O'){
                board[row - 1][col] = '+';
                queue.offerLast(new Point(row - 1, col));
            }
                
            // Buttom
            if(row + 1 < rowN && board[row + 1][col] == 'O'){
                board[row + 1][col] = '+';
                queue.offerLast(new Point(row + 1, col));
            }
                
            // Left
            if(col - 1 >= 0 && board[row][col - 1] == 'O') {
                board[row][col - 1] = '+';
                queue.offerLast(new Point(row, col - 1));
            }
            // Right 
            if(col + 1 < colN && board[row][col + 1] == 'O'){
                board[row][col + 1] = '+';
                queue.offerLast(new Point(row, col + 1));
            } 
        }
        
        // Traversal All nodes and change '+' to 'O', 'O' to 'X'
        for(int i = 0; i < rowN ; i++){
            for(int j = 0; j < colN; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                } else if(board[i][j] == '+'){
                    board[i][j] = 'O';
                } else{
                    continue;
                }
            }
        }
    }
}

class Point{
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
```

### BFS 概要
- 使用 Queue
- while(!queue.isEmpty)
- for queue.size，拿到一個點
- for 該點的鄰居或四周
- 判斷什麼條件要再放回queue
- grid[][] 和 set 擔任去重的角色

***

## BFS vs DFS
[StackOverFlow](https://stackoverflow.com/questions/20429310/why-is-depth-first-search-claimed-to-be-space-efficient)

Your confusion is stemming from the fact that you apparently assume that DFS algorithm can be obtained from BFS algorithm by replacing the FIFO queue with a LIFO stack.

This is a popular misconception - it is simply not true. The classic DFS algorithm cannot be obtained by replacing the BFS queue with a stack. The difference between these algorithms is much more significant.

If you take a BFS algorithm and simply replace the FIFO queue with a LIFO stack, you will obtain something that can be called a pseudo-DFS algorithm. This pseudo-DFS algorithm will indeed correctly reproduce the DFS vertex forward traversal sequence, but it will not have DFS space efficiency and it will not support DFS backward traversal (backtracking).

Meanwhile, the true classic DFS cannot be obtained from BFS by such a naive queue-to-stack replacement. The classic DFS is a completely different algorithm with significantly different core structure. True DFS is a genuinely recursive algorithm that uses stack for backtracking purposes, not for storing the vertex discovery "front" (as is the case in BFS). The most immediate consequence of that is that in DFS algorithm the maximum stack depth is equal to the maximum distance from the origin vertex in the DFS traversal. In BFS algorithm (as in the aforementioned pseudo-DFS) the maximum queue size is equal to the width of the largest vertex discovery front.

The most prominent and extreme example that illustrates the difference in peak memory consumption between DFS and BFS (as well as pseudo-DFS) is a star-graph: a single central vertex surrounded by a large number (say, 1000) of peripheral vertices, with each peripheral vertex connected to the central vertex by an edge. If you run BFS on this graph using the central vertex as origin, the queue size will immediately jump to 1000. The same thing will obviously happen if you use pseudo-DFS (i.e. if you simply replace the queue with a stack). But classic DFS algorithm will need stack depth of only 1 (!) to traverse this entire graph. See the difference? 1000 versus 1. This is what is meant by better space efficiency of DFS.

Basically, take any book on algorithms, find a description of classic DFS and see how it works. You will notice that the difference between BFS and DFS is far more extensive that a mere queue vs. stack.

P.S. It should also be said that one can build an example of a graph that will have smaller peak memory consumption under BFS. So the statement about better space efficiency of DFS should be seen as something that might apply "on average" to some implied class of "nice" graphs.




***

## Remind
## 103 Binary Tree ZigZag Level Order Traveral
- 1. Queue的接口為 Deque，實現方式為ArrayDeque
```java
Deque<Integer> queue = new ArrayDeque<>(); 
```
- 2. int size = queue.size() 要放在Level Traversal 之前，因為層級遍歷會改變Queue的Size
- 3. 每次 new 一個 List 的操作也是放在 Level Traversal 之前，因為加入Answer List內的結果必須在內存中做Hard Copy
- 4. Level Traversal後，再改變 Order
- 5. 檢查 Corner Case，別忘記 input == null 的時候該做的操作

## 107 Binary Tree Level Order Traversal II
- 1. 題目改變 res 放入 ans List 的順序

## 130 Surrounded Region
- 1. new 一個 blooean Array, default value is False
- 2. boolean[] visited = new boolean[1] means there is only one space in memory, you couldn't find visited[1]
- 3. char：用 "=="  | string：用 "equals"
- 4. Matrix-> rowN = matrix.length, colN = matrix[0].length
```java
matrix[row][col]
```
- 5. 邊界判斷方式：
```java
// Top
if(row - 1 >= 0 && board[row - 1][col] == 'O'){ }    
// Buttom
if(row + 1 < rowN && board[row + 1][col] == 'O'){ } 
// Left
if(col - 1 >= 0 && board[row][col - 1] == 'O') { }
// Right 
if(col + 1 < colN && board[row][col + 1] == 'O'){ }
```
- 6. Corner Case 判斷方式： 先 matrix != null， 後 matrix[0] != null

## 199. Binary Tree Right Side View
- 1. Level Traversal and Get the last number

## 310. Minimum Height Trees
- 1.  List<Set<Integer>> lsit 的操作
- 2. list.get(i).size() 得到第i個Set的大小
- 3. list.get(i).add(j) 將j放入第i個Set當中
- 4. list.get(i).iterator().next() 取得第i個Set當中第一個數值
- 5. 如何 init 這個List
```java
for(int i = 0; i < n; i++) {
    list.get(i).add(new HashSet);
}
```
- 6. 使用Queue處理Level Traversal的時候，要先定義Size，才不會因為處理過程中改變Queue Size大小，而有取不到值的問題。

## 417 Pacific Atlantic Water Flow
- 1. 如何處理四個方向
```java
int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
for(int[] d : dir) {
    int x = cur[0] + d[0];
    int y = cur[1] + d[1];
}
```
- 2. 如何處理垂直或水平加入Queue和Array
```java
int row = matrix.length;
int col = matrix[0].length;
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
```
## 690 Employee Importance
- 1. List<Integer> list -> list.size() 
- 2. int[] num -> num.length
- 3. String s -> s.length() 

## 743 Network Delay Time
- 0. New 一個新的Class操作，不要忘記init value 和 constructor
```java
class Pair{
    int time, dest;
    public Pair(int time, int dest) {
        this.time = time;
        this.dest = dest;
    }
}
```
- 1. 操作 Map <Integer, Set<Pair>> : 先 check if key in the map 在加入(add) Value 在 Set當中
```java
if(!map.containsKey(key)) {
    map.put(key, new HashSet<>());
}

map.get(key).add(new Pair(num, num));
```
- 2. Arrays.fill(array, value) 可以填入預設值
- 3. int[] num = new int[N] -> 不要忘記 new!!!!


## 529 Mineweeper
- 1. 將 Integer 放入 char[]當中的方式：
```java
int num = 2;
char[] charList = new char[1];
char[1] = (char)(num + '0');
```
- 2. 操作 matrix 中的 dirHelper Array的時候，記得先檢查邊界
```java
int[][] helper = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, -1}, {-1, 1}, {1, 1}, {-1, -1}};

for(int[] dir : helper) {
    int newRow = x + dir[0];
    int newCol = y + dir[1];
    if(newRow < 0 || newRow >= row || newCol < 0 || newCol >= col){
        continue;
    }
```

## 542 01 Matrix
- 1. 判斷 newRow 和 newCol 是否 valid，記得不要用錯邊界值 “>=" "&&"
```java
    public boolean isValid(int newRow, int newCol, int row, int col){
        return newRow >= 0 && newCol >= 0 && newRow < row && newCol < col;
    }
```