# Binary Tree Paths

- 左邊得到解，右邊得到解。
- 將左邊的解加入List中，接著將右邊的解加入List中。
- 注意root為單獨點的時候，root.left == null && root.right == null
- 將root.val與""一起加入

```java
class Solution {
  public List<String> binaryTreePaths(TreeNode root) {
      List<String> paths = new ArrayList<String>();
      if(root == null) {
          return paths;
      }
      
      if(root.left == null && root.right == null) {
          paths.add("" + root.val);
          return paths;
      }
      
      List<String> leftPaths = binaryTreePaths(root.left); // 2 -> 5
      List<String> rightPaths = binaryTreePaths(root.right); // 3


      
      for(String path : leftPaths) {
          paths.add(root.val + "->" + path);
      }
      
      for(String path : rightPaths) {
          paths.add(root.val + "->" + path);
      }
      return paths;
  }
}
```