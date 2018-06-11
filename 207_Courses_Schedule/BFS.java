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