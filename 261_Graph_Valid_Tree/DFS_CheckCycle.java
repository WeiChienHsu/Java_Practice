public class Solution {
    public boolean validTree(int n, int[][] edges) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.put(i, new ArrayList<>());
            }
            for (int i = 0; i < edges.length; i++) {
                map.get(edges[i][0]).add(edges[i][1]);
                map.get(edges[i][1]).add(edges[i][0]);
            }
            boolean[] visited = new boolean[n];
    
            if (findCircle(0, -1, map, visited)) {
                // check if there is loop
                return false;
            }
    
            // check if every node is included
            for (boolean isVisited : visited) {
                if (!isVisited) {
                    return false;
                }
            }
            return true;
        }
        
        private boolean findCircle(int curr, int prev, Map<Integer, List<Integer>> map,
                                   boolean[] visited) {
            if (visited[curr]) {
                return true;
            }
            visited[curr] = true;
            
            for (int next : map.get(curr)) {
                if (next != prev && findCircle(next, curr, map, visited)) {
                    return true;
                }
            }
            return false;
        }
    }