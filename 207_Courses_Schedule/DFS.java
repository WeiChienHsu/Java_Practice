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