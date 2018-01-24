# Binary Tree Level Order Traversal II

# Solution - BFS
- Used a queue:
- Push the root into the queue
- Give a size based on the currently queue size
- For looping through those node in the same level
- poll out the Node to record its value while add its left and right(Inorder) into queue
- When queue is empty which means the BFS is end
```java
   while(!queue.isEmpty()) {
          
          int size = queue.size();
          List<Integer> list = new ArrayList<>();
          
          for(int i = 0; i < size ; i++) {
              TreeNode cur = queue.pollFirst();
              list.add(cur.val);
              if(cur.left != null) queue.offerLast(cur.left);
              if(cur.right != null) queue.offerLast(cur.right);
          }            
          
          res.add(0, list);
      }
      return res;
```