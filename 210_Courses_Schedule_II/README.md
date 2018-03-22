# 



## My Solution - primitive O(n^2)

1. Get the Indegree of Each Class and getGraph is to get all the courses that point to other courses
- [[1,2], [3], [3], []]
2. Put those courses which have Indegree equal to 0 into Queue
3. Decreas the indegree of courses which was pointed by the curCourse we poll out the Queue
4. If Queue if empty during we process thos courses means it's impossible to finish the courses, return the empty array
5. Each time, when we poll a new Course from Queue, add it into out resut array

```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        
        if(numCourses == 0) {
            return ans;
        }
        
        if(prerequisites == null){
            for(int i = 0; i < numCourses; i++) {
                ans[i] = numCourses--;
            }
            return ans;
        }
        
        List<Set<Integer>> graph = getGraph(numCourses, prerequisites);
        int[] indegree = getIndegree(prerequisites, numCourses);
        
        // put all indegree == 0 into a Queue
        Deque<Integer> queue = new ArrayDeque<>();
        
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                queue.offerLast(i);
            }
        }
        
        int[] emptyArray = new int[0];
        
        // Reduce All indegree to 0 and record the index of Courses
        int curNum = 0;
        
        while(numCourses > 0) {
            int size = queue.size();
            if(size == 0)  return emptyArray;
            
            for(int i = 0; i < size; i++) {
                int curCourse = queue.pollFirst();
                ans[curNum++] = curCourse;
                numCourses--;
                
                // Go Througth the Graph to Reduce the number of indegree which curCourse point to
                for(int n : graph.get(curCourse)) {
                    indegree[n]--;
                    if(indegree[n] == 0){
                        queue.offerLast(n);
                    }
                }   
            }
        }
        
        return ans;
        
    }
    
    // [0, 1, 1, 2]
    public int[] getIndegree(int[][] pre, int n) {
        int[] indegreeNum = new int[n];
        for(int i = 0; i < pre.length; i++){
            indegreeNum[pre[i][0]]++;
        }
        return indegreeNum;
    }
    
    // [[1,2], [3], [3], []]
    public List<Set<Integer>> getGraph(int n, int[][] pre) {
        List<Set<Integer>> res = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            res.add(new HashSet<>());
        }
        
        for(int i = 0; i < pre.length; i++) {
            res.get(pre[i][1]).add(pre[i][0]);
        }
        
        return res;
    }
}
```

## Better Solution in Leetcode


```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        List<List<Integer>> adjs = new ArrayList<>(numCourses);
       
        initialiseGraph(indegree, adjs, prerequisites);
         //return solveByBFS(incLinkCounts, adjs);
        return solveByBFS(indegree, adjs);
    }
    
    private void initialiseGraph(int[] indegree, List<List<Integer>> adjs, int[][] prerequisites){
        int n = indegree.length;
         while (n-- > 0) adjs.add(new ArrayList<>());
        
        for (int[] edge : prerequisites) {
            indegree[edge[0]]++;
            adjs.get(edge[1]).add(edge[0]);
        }
    }
    
    private int[] solveByBFS(int[] indegree, List<List<Integer>> adjs){
        
        int[] order = new int[indegree.length];
        Queue<Integer> toVisit = new ArrayDeque<>();
        
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) toVisit.offer(i);
        }
        
        int visited = 0;
        while (!toVisit.isEmpty()) {
            int from = toVisit.poll();
            order[visited++] = from;
            for (int to : adjs.get(from)) {
                indegree[to]--;
                if (indegree[to] == 0) toVisit.offer(to);
            }
        }
        
        return visited == indegree.length ? order : new int[0]; 
    }
}
```