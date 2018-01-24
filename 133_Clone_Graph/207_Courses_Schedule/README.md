

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

