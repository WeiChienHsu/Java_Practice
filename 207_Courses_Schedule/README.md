# Courses Schedule
- There are a total of n courses you have to take, labeled from 0 to n - 1.
```
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

1 -> 0
```


# Solution - DFS

 - Explanation for the DFS solution : the solution basically starts at every node in the graph which corresponds to a course and traverses all the courses (nodes) that can be taken subsequently. If we ever encounter the a course we have already visited , then we know there is a cycle. Note that in the solution , as the recursion unwinds all the visited status is set to false. Hence every time DFS is started in the can finish function , the visted boolean array is guaranteed to be all false. That is why this method works.


```
0  ->  1     
↑      ↑
3  ->  2
  ↘   ↙     
    4   
```
- Init the graph(List<List<Integer>>) -> Create a List -> Add all prerequist into graph
- 存法： ArrayList內對應位置代表著某課程，裡面的[]代表著先修課
- [[3],[0,2],[3],[],[3,2]]

```java
      ArrayList[] graph = new ArrayList[numCourses];
      
      // Create List 
      for(int i = 0; i < numCourses; i++) {
          graph[i] = new ArrayList();
      }
      
      // Add into the list
      for(int[] vector: prerequisites) {
          graph[vector[1]].add(vector[0]);
      }
      
      // [0,1] : 1 -> 0 save 
```

- Boolean[] to see if we have already visited
- Go through all courses in graph by DFS, if return false, reutn false, or if not return true.
- DFS檢查每一個節點，如果碰上曾經造訪的，回傳false，每次回歸上一層都要backtracking，把visited狀況改回false

### DFS
- 遇上visit[course] = false -> return false
- 遍歷所有course的鄰居
- ArrayList是Object，要轉成int
- 如果在下層遇上visited - > 回傳 false
- return 前要把 visited 狀態回復

```java
      private boolean dfs(ArrayList[] graph, boolean[] visited, int course) {
          if(visited[course]) {
              return false;
          } else {
              visited[course] = true;
          }
          
          // DFS to all negibors
          for(int i = 0; i < graph[course].size(); i++) {
              if(!dfs(graph, visited, (int)graph[course].get(i)))
                  return false;
          }
          visited[course] = false;
          return true;
      }

```

# Solution - BFS
- 找到出發點（不需要prerequitsite）當做第一層，依據prerequitsite給訂下一層，Level by Level
- 先走一遍graph，將所有的Node標上Level
```java
  // Init the Graph
        for(int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList();
        }
        
        // Init the Level
        for(int i = 0; i < prerequisites.length; i++){
            degree[prerequisites[i][0]]++;;
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        
        
        for(int i = 0; i < degree.length; i++) {
            if(degree[i] == 0) {
                queue.offerLast(i);
                count++;
            }
        }
```

- 將 prerequitsite = 0 的放入 Queue，彈出時，將他連接的Node Level -1，再將 prequitsite = 0的放入
- 每次有一個加入Queue -> count++ ，最後比對count == numCourses ? true : false
```java
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
```


### Solution - Best
- int[] indegree 記錄每堂課的 indegree
- ArrayList[] graph 記錄每堂課指向的課程
- count 計算有多少indegree == 0 
- 1. 先init graph，new ArrayList放進 ArrayList Array內
- 2. 算每個點的indegree，遍歷一次prerequisites，加入int[] indegree ，同時將箭頭指向的課程加入ArrayList[]中
- 3. 遍歷一次 indegree，找尋 StartNodes (indegree == 0)
- 4. 用BFS，使用queue來記錄indegree==0的點，代表著出發的起點，從queue中取出一點，遍歷其指向的課程，將其indegree--，如果此時indegree == 0，加入queue中，繼續當起點，count++


注意： 
```java
for(int i = 0; i < prerequisites.length; i++) {
```
```java
for(int i = 0; i < indegree.length; i++) {
```
```java
  int neighbor = (int)graph[curCourse].get(i);
```

***

#### BFS

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        ArrayList[] graph = new ArrayList[numCourses];
        Deque<Integer> queue = new ArrayDeque<>();
        int count = 0;
        
        // Init graph
        for(int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList<Integer>();
        }
        
        // Put neighbors into graph and count the indegree
        for(int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][0]]++;
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        
        // Find startNodes and put into Queue (also count)
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                queue.offerLast(i);
                count++;
            }
        }
        
        // BFS
        while(!queue.isEmpty()) {
            int curCourse = queue.pollFirst();
            for(int i = 0; i < graph[curCourse].size(); i++) {
                int neighbor = (int)graph[curCourse].get(i);
                indegree[neighbor]--;
                if(indegree[neighbor] == 0) {
                    count++;
                    queue.offerLast(neighbor);
                }
            }
        }
        return count == numCourses;
    }
}
```