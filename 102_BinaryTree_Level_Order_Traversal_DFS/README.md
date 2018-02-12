# 102 Binary Tree Level Order Traversal

## DFS Problem: Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).


## For example:
- Given binary tree [3,9,20,null,null,15,7],


```
    3
   / \
  9  20
    /  \
   15   7
```

- return its level order traversal as:

```
[
  [3],
  [9,20],
  [15,7]
]

```

## Solution
- Use DFS to find each number in different level
- Queue : First in First out 
```

add(3)

   / \
  9  20
    /  \
   15   7

 ________
|     3
|________

poll(3)
add in List
add(3.left)
add(3.right)


    /  \
   15   7

 ________
|  9  20   
|________

[[3],]

poll(9)
add in List
poll(20)
add in List
add(20.left)
add(20.right)



 ________
|  15 7   
|________
[[3],[9,20],]

poll(15)
add in List
poll(20)
add in List
queue.isEmpty()

[[3],[9, 20], [15,7]]
```
- Record the size at first in each Level then you could know how many children are in next level
```java
int size = queue.size();
```

- Need a result list: List<List<Integer>>, record list : List<Integer> and a queue : Deque<TreeNode> queue = new ArrayDeque<>()
```java
List<List<Integer>> result = new ArrayList<List<Integer>>();
if(root == null) return result;

Deque<TreeNode> queue = new ArrayDeque<>();

queue.add(root); // add the first level
```

- DFS processes
```java
// pop out the first level to record in a List and add it's children in the same time
while (!queue.isEmpty()) {
    // Record the size for knowing size of next level
    int size = queue.size();
    // Create a List to record numbers in each level and add in a result List
    List<Integer> list = new ArrayList<>();
    
    for(int i = 0; i < size; i++) {
        TreeNode current = queue.pollFirst();
        list.add(current.val);
        // Check if those are null, and add the children nodes of previous root
        if(current.left != null) queue.add(current.left);
        if(current.right != null) queue.add(current.right);
    }
    result.add(list);
}
return result;
}
```

***

## Another DFS Methods
```java
public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        levelHelper(res, root, 0);
        return res;
    }
    
    public void levelHelper(List<List<Integer>> res, TreeNode root, int height) {
        if (root == null) return;
        if (height >= res.size()) {
            res.add(new LinkedList<Integer>());
        }
        res.get(height).add(root.val);
        levelHelper(res, root.left, height+1);
        levelHelper(res, root.right, height+1);
    }
```