# Tree Traversal

## PostOrder - Each Node is procrssed after substrees
```
         7
      /     \
     3       6
    / \     / \
   1   2   4   5
```
```java
public void postOrder(TreeNode root) {
    if(root != null) {
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }
}

```

## InOrder - Each Node is processed between subtrees
```
         4
      /     \
     2       6
    / \     / \
   1   3   5   7
```
```java
public void postOrder(TreeNode root) {
    if(root != null) {
        postOrder(root.left);
        System.out.println(root.val);
        postOrder(root.right);
    }
}

```
## PreOrder - Each Node is processed before subtrees
```
         1
      /     \
     2       5
    / \     / \
   3   4   6   7
```
```java
public void postOrder(TreeNode root) {
    if(root != null) {
        System.out.println(root.val);
        postOrder(root.left);
        postOrder(root.right);
    }
}

```

## Solution - Iteriation for InOrder Traversal

- Go throught the left nodes and offer those nodes into the stack
- When you arrive at the leave, poll out, add into the list and check if there is a right node
- If yes, keep tracking the left to the end
- If No, poll out the next node in the stack.

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()) {
            while(cur != null) {
                stack.offerLast(cur);
                cur = cur.left;
            }
            cur = stack.pollLast();
            list.add(cur.val);
            cur = cur.right; // if no right node, it will still check if stack is empty or not in the while loop
        }
        return list;
    }
}
```