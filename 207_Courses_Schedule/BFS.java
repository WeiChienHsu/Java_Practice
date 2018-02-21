class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        Deque<Integer> queue = new ArrayDeque<>();
        int count = 0;
        
        // Init graph
        for(int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
        // Add neighbor into ArrayList
        for(int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][0]]++;
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        
        // Find StartNodes
        for(int i = 0; i < indegree.length; i++){
            if(indegree[i] == 0){
                queue.add(i);
                count++;
            }
        }
        
        while(!queue.isEmpty()) {
            int course = queue.pollFirst();
            for(int i = 0; i < graph[course].size(); i++) {
                int neighbor = (int)graph[course].get(i);
                indegree[neighbor]--;
                if(indegree[neighbor] == 0){
                    queue.offerLast(neighbor);
                    count++;
                }
            }
        }
        
        return count == numCourses;
    }
    

}