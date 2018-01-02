 # Binary Search Tree Iterator


 # Solution
 - next(): Keep tracking the left node to the end and pull all nodes we go through into the stack
 - When meet the null, give current value the peek on stack
 - Then, go right to check the most left in this right node as well.
```java
    public int next() {
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.offerLast(cur);
                cur = cur.left;
            } else {
                cur = stack.peekLast().right;
                break;
            }
        }
        TreeNode node = stack.pollLast();
        return node.val;
    }
```

 - hasNext(): return the boolean of stak.isEmpty()
 ```java
     public boolean hasNext() {
        return !stack.isEmpty() || cur != null;
    }
```