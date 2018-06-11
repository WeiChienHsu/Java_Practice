# Courses Schedule
- There are a total of n courses you have to take, labeled from 0 to n - 1.
```
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

1 -> 0
```


## Solution - DFS (Backtracking)

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

***

## Solution - BFS
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
        if(prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) return true;
        
        int[] indegree = new int[numCourses];
        ArrayList[] graph = new ArrayList[numCourses];
        Deque<Integer> queue = new ArrayDeque<>();
        int count = 0;
            
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
        
        // Find the one indegree == 0 and put it into the Queue
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                queue.offerLast(i);
                count ++;
            }
        }
        
        // BFS 
        while(!queue.isEmpty()) {
            // poll out the course in the Queue as the start course
            int currentCourse = queue.pollFirst();
            // 遍歷現階段課程的所有後續課程，將其indegree--
            for(int i = 0; i < graph[currentCourse].size(); i++) {
                int nextCourse = (int)graph[currentCourse].get(i);
                indegree[nextCourse]--;
                if(indegree[nextCourse] == 0) {
                    count++;
                    queue.offerLast(nextCourse);
                }
            }
        }
        
        return count == numCourses;
        
    }
}
```

***

## DFS  利用兩個額外Set紀錄造訪狀況

1. 使用和 BFS 一樣的list和ArrayList紀錄課程關係
2. 額外使用兩個Set，一個紀錄是否passed(無環)，一個紀錄是否造訪 visited(重複造訪產生環)
3. 遍歷整個 graph，如果 passed == truee 略過不看，沒有passed的丟入DFS中檢查是否有環，全部遍歷完畢，沒有環出現，return true
4. DFS: boolean containCircle()
- 如果passed == true 沒有環 return false
- 如果visited == true 重複造訪，有環return true
- 都沒有，進行鄰居的遍歷，依據graph裡面記錄得到該點指向的所有點，如果其中一個點有環(visited == ture)，return true
- 如果順利遍歷完都沒有環，marked passed 然後 return true


```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        if(prerequisites == null || prerequisites.length == 0 ) return true;
        
        Set<Integer> visited = new HashSet<>();
        Set<Integer> passed = new HashSet<>();
        ArrayList[] graph = new ArrayList[numCourses];
        
        // Init the graph and put all courses into the Array
        for(int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < prerequisites.length; i++) {
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        
        // Traverse the graph
        for(int i = 0; i < numCourses; i++) {
            if(passed.contains(i)) continue;
            
            if(Solution.containsCircle(i, passed, visited, graph)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean containsCircle(int course, Set<Integer> passed, Set<Integer> visited, ArrayList[] graph) {
        if(passed.contains(course)) return false;
        if(visited.contains(course)) return true;
        
        // Marked the course as visited
        visited.add(course);
        
        // Traverse all neighbors of current course
        for(int i = 0; i < graph[course].size(); i++) {
            int nextCourse = (int) graph[course].get(i);
            if(Solution.containsCircle(nextCourse, passed, visited, graph)){
                System.out.println("Contains Circle");
                return true;
            }
        }
        // After DFS, if there is no circle means this course is passed
        passed.add(course);
        return false;
    }
}
```