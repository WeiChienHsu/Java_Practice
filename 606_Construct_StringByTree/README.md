# Construct String from Binary Tree


You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.

The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.

```
Example 1:
Input: Binary tree: [1,2,3,4]
       1
     /   \
    2     3
   /    
  4     

Output: "1(2(4))(3)"

Explanation: Originallay it needs to be "1(2(4)())(3()())", 
but you need to omit all the unnecessary empty parenthesis pairs. 
And it will be "1(2(4))(3)".
Example 2:
Input: Binary tree: [1,2,3,null,4]
       1
     /   \
    2     3
     \  
      4 

Output: "1(2()(4))(3)"

Explanation: Almost the same as the first example, 
except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
```

## Solution - PreOrder
1. 先加入 Root.val
2. 檢查左邊或右邊節點是否 ==  null 
3. 先Traversal 左邊節點，加入 "(" ， root.left 丟入 helper function 內
4. Traversal 結束後，將這個點關閉，加入")"
5. 檢查右邊是否為 null，如果右邊有Node，加入"("
6. Traversal 右邊root.right 丟入 helper function 內
7. Traversal 結束後，將這個點關閉，加入")"

```java
public String tree2str(TreeNode t) {
  StringBuilder sb = new StringBuilder();
  helper(sb,t);
  return sb.toString();
}
public void helper(StringBuilder sb,TreeNode t){
  if(t!=null){
      sb.append(t.val);
      if(t.left!=null||t.right!=null){
          sb.append("(");
          helper(sb,t.left);
          sb.append(")");
          if(t.right!=null){
            sb.append("(");
            helper(sb,t.right);
            sb.append(")");
          }
      }
  }
}

```