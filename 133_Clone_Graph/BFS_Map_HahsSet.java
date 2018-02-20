public class Solution {
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
      // Coner Case
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