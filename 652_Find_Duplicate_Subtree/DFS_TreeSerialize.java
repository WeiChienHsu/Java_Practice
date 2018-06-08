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
  public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
      List<TreeNode> results = new ArrayList<>();
      Map<String, Integer> duplicatdMap = new HashMap<>();
      Solution.collect(root, duplicatdMap, results);
      return results;
  }
  
   public static String collect(TreeNode root, Map<String, Integer> map, List<TreeNode> results) {
      // 先進行 Serizlize, 使用 DFS 逐一的把所有 Node 以及他的Children序列化
      // 將該次Serizlize的結果存入 Map 裡， Key:序列化結果 Value:出現次數
      // 檢查這次的 Serial 有沒有出現在 Map 裡面超過 1 次，有的話，將此 Node 加入 result<TreeNode> 當中
      if(root == null) return "#";
      String serial = root.val + "," 
                     + Solution.collect(root.left, map, results) + "," 
                     + Solution.collect(root.right, map, results);
      
      map.put(serial, map.getOrDefault(serial, 0) + 1);
      if(map.get(serial) == 2) {
          results.add(root);
      }
      return serial;
  }
}