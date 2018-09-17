/*
 1. Put all nodes connected into the Map.
 2. If the target node doesn't exist in the Map's keySet, return the empty result
 3. Need a Queue and Array to Record the current level nodes and visited nodes.
 4. Start from the Target (Put it into the queue) to make the level Traversal.
 5. After each level, decrement the K by 1 until it equals to 0 add those nodes in the Map as result.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
      Map<TreeNode, List<TreeNode>> map = new HashMap<>();
      /* add root's connected nodes into the list */
      buildMap(map, root, null);
      List<Integer> result = new ArrayList<>();
      
      if(!map.containsKey(target)) return result;
      
      Deque<TreeNode> queue = new ArrayDeque<>();
      Set<TreeNode> visited = new HashSet<>();
      
      queue.offerLast(target);
      visited.add(target);
      
      while(!queue.isEmpty()) {
          int size = queue.size();
          
          /* Meet the level those nodes have K distance with target node */
          if(K == 0) {
              for(int i = 0; i < size; i++) {
                  result.add(queue.pollFirst().val);
              }
              return result;
          }
          /* Traversal all nodes in this level and add them into Queue and marked as visited */
          for(int i = 0; i < size; i++) {
              TreeNode currentNode = queue.pollFirst();
              visited.add(currentNode);
              /* add those neighbors into the queue and marked as visited */
              List<TreeNode> connectedList = map.get(currentNode);
              System.out.println("Node: " + currentNode.val);
              System.out.println("len: " + connectedList.size());
              
              for(int j = 0; j < connectedList.size(); j++) {
                  TreeNode neighbor = connectedList.get(j);
                  System.out.println(neighbor.val);
                  
                  if(visited.contains(neighbor)) continue;
                  queue.offerLast(neighbor);
                  visited.add(neighbor);
              }
          }
          /* After each level traversal, decrement K by 1 */
          K --; 
      }
      
      return result;
      
      
  }
  
  
  public void buildMap(Map<TreeNode, List<TreeNode>> map, TreeNode root, TreeNode parent) {
      if(root == null) return;
      
      if(!map.containsKey(root)) {
          map.put(root, new ArrayList<TreeNode>());            
      }
      
      if(parent != null) {
          map.get(root).add(parent);
      }
      
      if(root.left != null) {
          map.get(root).add(root.left);
      }
      
      if(root.right != null) {
          map.get(root).add(root.right);
      }
      
      buildMap(map, root.left, root);
      buildMap(map, root.right, root);
  }
}