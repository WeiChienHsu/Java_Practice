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
      return DFSHelper(node, map);
  }
  
  private UndirectedGraphNode DFSHelper(UndirectedGraphNode node, Map<UndirectedGraphNode,UndirectedGraphNode> map) {
      map.put(node, new UndirectedGraphNode(node.label));
      
      for(UndirectedGraphNode nei : node.neighbors) {
          UndirectedGraphNode newNei = map.get(nei);
          if(newNei == null) {
              newNei = DFSHelper(nei, map);
          }
          map.get(node).neighbors.add(newNei);
      }
      return map.get(node);
  }
}