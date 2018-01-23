/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
      if(node == null) return null;
      Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
      return BFSHelper(node);
  }
  
  private UndirectedGraphNode BFSHelper(UndirectedGraphNode node) {
      Map<UndirectedGraphNode,UndirectedGraphNode> map = new HashMap<>();
      Deque<UndirectedGraphNode> queue = new ArrayDeque<>();
      
      queue.offerLast(node);
      map.put(node, new UndirectedGraphNode(node.label));
      
      while(!queue.isEmpty()) {
          UndirectedGraphNode cur = queue.pollFirst();
          UndirectedGraphNode copy = map.get(cur);
          for(UndirectedGraphNode nei : cur.neighbors) {
              UndirectedGraphNode newNei = map.get(nei);
              if(newNei == null) {
                  // Create Node for neighbor and put into queue & map
                  queue.offerLast(nei);
                  map.put(nei, new UndirectedGraphNode(nei.label));
              }
              // Create edge starting from current coppied
              copy.neighbors.add(map.get(nei));
          }
      }
      return map.get(node);
  }
}