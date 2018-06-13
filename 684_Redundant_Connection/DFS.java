class Solution {
  public int[] findRedundantConnection(int[][] edges) {
      
      if (edges == null) return new int[]{};
      
      Map<Integer, List<Integer>> graph = new HashMap<>();
      
      for(int[] edge : edges) {
          // Get the start and end point
          int u = edge[0];
          int v = edge[1];
          Set<Integer> visited = new HashSet<>();
          
          if(Solution.hasPath(u, v, graph, visited)) {
              return edge;
          }
          
          // Put the neighbor into the Map
          List<Integer> neighbors = graph.getOrDefault(u, new ArrayList<>());
          neighbors.add(v);
          graph.put(u, neighbors);
          
          neighbors = graph.getOrDefault(v, new ArrayList<>());
          neighbors.add(u);
          graph.put(v, neighbors);
      }
      
      return new int[]{ };
  }
  
  public static boolean hasPath(int start, int end, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
      if (start == end) return true;
      visited.add(start);
      if(!graph.containsKey(start) || !graph.containsKey(end)) return false;
      
      for(int neighbor : graph.get(start)) {
          if(visited.contains(neighbor)) continue;
          if(Solution.hasPath(neighbor, end, graph, visited)) return true;
      }
      return false;
  }
  
}