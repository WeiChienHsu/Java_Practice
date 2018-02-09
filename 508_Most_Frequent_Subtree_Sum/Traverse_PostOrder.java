class Solution {
  public int maxCount;
  public Map<Integer, Integer> map;
  
  public int[] findFrequentTreeSum(TreeNode root) {
      maxCount = 0;
      map = new HashMap<Integer, Integer>();
      
      postTraverse(root);
      
      List<Integer> res = new ArrayList<>();

      
      for(int key: map.keySet()){
          if(map.get(key) == maxCount) {
              res.add(key);
          }
      }
      
      int[] result = new int[res.size()];
      for(int i = 0; i < res.size(); i++) {
          result[i] = res.get(i);
      }
      
      return result;
  }
  
  public int postTraverse(TreeNode root) {
      if(root == null) {
          return 0;
      }
      
      int left = postTraverse(root.left);
      int right = postTraverse(root.right);
      
      int sum = left + right + root.val;
      int count = map.getOrDefault(sum, 0) + 1;
      map.put(sum, count);
      
      maxCount = Math.max(maxCount, count);
      return sum;
  }
}