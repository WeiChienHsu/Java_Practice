# Lowest Common Ancestor


## Solution

```
        1
       / \
      2   3
     / \   \  
    4  5    6
   / \
  7   8
```

- 左右搜尋有無目標A和B，可能的結果有：
- A 和 B 都在左邊 return root
- A 和 B 都在右邊 return root
- A 和 B 個在一邊，一邊return null 一邊回傳目標， return != null 的那邊
- A 和 B 都回傳null，繼續回傳null(代表左右都沒找到)

```
- 找 5 & 6
從7開始，left == null & right == null return null
8 ，  left == null & right == null return null
4 ，  left == null & right == null return null
5 ， 遇到A，return 5(root)
2 ，  left == null right == 5， return right
6 ， 遇到B， reutrn 6(root)
```
 #### 結論：
 - 遇到 A || B || null -> return root(當時也是null)
 - 往左走
 - 往右走
 - 如果 left 和 right 都!= null，代表都找到了， return root
 - 左邊null 右邊找到了， return right
 - 右邊null 左邊找到了， return left
 - 兩邊都null return null (這邊可以優化！不一定需要，因為一開始root == null已經回傳了)

- 優化前：
 ```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
  if(root == null || root == p || root == q) {
      return root;
  }
  
  TreeNode left = lowestCommonAncestor(root.left, p, q);
  TreeNode right = lowestCommonAncestor(root.right, p, q);
  
  // Got LCA
  if(left != null && right != null) {
      return root;
  }
  
  if(left != null) {
      return left; 
  }
  
  if(right != null) {
      return right;
  }
  
  // left &  right == null
  return null;
}
 ```

- 優化後：
```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == q || root == p) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if(left != null && right != null) {
            return root;
        }
        
        return left == null ? right : left;
    }
}
```

## 遇上可能沒解的情況：
- ResultType 紀錄： boolean a_exist , boolean b_exist, TreeNode lca
- 一樣探討上面的狀況:
- 先處理Base Case(在夜子時）如果 root == null -> false, false, null
- 給予 left_rt(root.left, A, B)
- 給予 right_rt(root.right, A, B)
- 新的a_exist 與 b_exist值必須回傳，有三種狀況，left_rt.a_exit || right_rt.b_exist || root == A
#### 討論以下四種狀況
- root == A 或 root == B -> return (a_exist, b_exist, root)
- left_rt.node != null && right_rt.node != null -> return (a_exist, b_exist, root)
- 右邊為null -> (a_exist, b_exist, rt_right.node)
- 左邊為null -> (a_exist, b_exist, rt_left.node)
- 兩邊皆為 null -> return (false, false, null)

```java
class Solution {
    
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
      
      ResultType rt = helper(root, A, B);
      if(rt.a_exist && rt.b_exist) {
          return rt.lca;
      } else {
          return null;
      }
  }
  
  public ResultType helper(TreeNode root, TreeNode A, TreeNode B) {
      if(root == null) {
          return new ResultType(false, false, null);
      }
      
      ResultType left_rt = helper(root.left, A, B);
      ResultType right_rt = helper(root.right, A, B);
      
      boolean a_exist = left_rt.a_exist || right_rt.a_exist || root == A;
      boolean b_exist = left_rt.b_exist || right_rt.b_exist || root == B;
      
      if(root == A || root == B) {
          return new ResultType(a_exist, b_exist, root);
      }
      
      if(left_rt.lca != null && right_rt.lca != null) {
          return new ResultType(a_exist, b_exist, root);
      }
      
      if(left_rt.lca != null){
         return new ResultType(a_exist, b_exist, left_rt.lca);
      }
      
      if(right_rt.lca != null){
          return new ResultType(a_exist, b_exist, right_rt.lca);
      }
      
      return new ResultType(a_exist, b_exist, null);
  }
  
  class ResultType{
      public boolean a_exist, b_exist;
      public TreeNode lca;
      ResultType(boolean a, boolean b, TreeNode n) {
          a_exist = a;
          b_exist = b;
          lca = n;
      }
  }
  
}
```