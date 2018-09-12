






## N-ary Tree PostOreder Traversal

```java
class Solution {
  public List<Integer> postorder(Node root) {
      List<Integer> result = new ArrayList<>();
      helper(root, result);
      return result;
  }
  
  public void helper(Node root, List<Integer> list) {
      if(root == null) return;
      List<Node> childrenList = root.children;
      
      for(int i = 0; i < childrenList.size(); i++) {
          helper(childrenList.get(i), list);
      }
      
      list.add(root.val);
  }
}
```


## N-ary Tree Level Order Traversal

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        
        Deque<Node> queue = new ArrayDeque<>();
        queue.offerLast(root);
        
        while(!queue.isEmpty()) {
            /* Do a Level Traversal */
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            
            for(int i = 0; i < size; i++) {
                Node currentNode = queue.pollFirst();
                list.add(currentNode.val);
                /* Traverse through current node's all children */
                List<Node> childrenList = currentNode.children;
                for(int j = 0; j < childrenList.size(); j++) {
                    /* Add the child into temporary list */
                    if(childrenList.get(j) != null) {
                        queue.offerLast(childrenList.get(j));
                    }
                }
            }
            /* Gather all results in the same level and insert in the first positoin of the result list */
            result.add(list);
        }
        
        return result;
    }
}

```

## N-ary Tree PreOreder Traversal

```java
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;
    }
    public void helper(Node root, List<Integer> list) {
        if(root == null) return;
        list.add(root.val);
        
        List<Node> childrenList = root.children;
        for(int i = 0; i < childrenList.size(); i++) {
            helper(childrenList.get(i), list);
        }
    }
}

```