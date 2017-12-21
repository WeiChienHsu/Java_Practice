# Binary Tree Zigzag Level Order Traversal

## Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

```
    3            <-
   / \
  9  20          ->
    /  \
   15   7        <-

->

[
  [3],
  [20,9],
  [15,7]
]
```

## Solution
- For this question, we know it's about DFS which means we need to go through each leavel.
- Then we know we need to set a size for each level and use a isEmpty() to set up the while loog
```java
while(!deque.isEmpty()) {
    int size = deque.size();
```

- For Deque, we neeed addLast/ addFirst/ pullLast/ pullFirst
- Set a List for recording the result and add in result List in the end
```java
List<List<Integer>> res = new ArrayList<List<Integer>>();
List<Integer> list = new ArrayList<Integer>();
```


- Discuss about the rules in this problem
* Seperate each Level to isLeftToRight and !isLeftToRight
* Used a deque since we could handle the in and out just by one side

```
    ------
 ->     3  
    ------
-------- Add the root

For the second Level -> isLeftToRight

     ------
 <-      3   
     ------
- current = pullFirst out
- Add in a List
- For this level: Add Left then Right

------
  9  20     <-
------

==========================


For the third Level -> !isLeftToRight

------
  9  20     ->
------

- current = pullLast out
- Add in a List
- For this level: Add Right then Left

     ----------
->   15  7 | 9
     ----------
```

- Thus, we gain the conclusion for thw different levels
```java
    for (int i = 0; i < size; i++) {
    if(isLeftToRight) {
        TreeNode current = deque.pollFirst();
        list.add(current.val);
        // First Left next Right
        if(current.left != null) {
            deque.addLast(current.left);
        } 
        
        if(current.right != null) {
            deque.addLast(current.right);
        }
    } else {
        TreeNode current = deque.pollLast();
        list.add(current.val);
        if(current.right != null) {
            deque.addFirst(current.right);
        }
        if(current.left != null) {
            deque.addFirst(current.left);
        }
```