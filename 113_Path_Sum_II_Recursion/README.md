# Path Sum II
## Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

```
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
```

## Recursion
- Check All path -> Record the Path ->
- Add list into Result when list.sum == target ->
- Pass sum - root.val down next level ->
- Before Remove back list.remove(list.size() -1 )

* Need a List<Integer> list to record answer
* Need a List<List<Integer>> rse to return result
```java
List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>(); //Record Each result in the leaf
```

- Logic for the recursion

```java
helper(root, sum, list, res);

if (root == null) return;

// Add the number we go through
list.add(root.val);

if( sum == root.val && root.right == null && root.left == null ) {
    res.add(new ArrayList<Integer>(list));
} else {
    helper(root.left, sum - root.val, list, res);
    helper(root.right, sum - root.val, list, res); 
}

// Remove the node value from list since we'll back to last level
list.remove(list.size() - 1);
```
## Demo of Recursion

#### helper(root, sum, list, res);
```
            * 5         root 5
             / \
            4   8        sum 22
           /   / \
          11  13  4      list []
         /  \    / \
        7    2  5   1    res []
```

#### list.add(root.val);

```

            * 5         root = 5
             / \
            4   8        sum = 22 
           /   / \
          11  13  4      list = [5]
         /  \    / \ 
        7    2  5   1    res
```

####  helper(4, 17, list, res);
```

              5          root = 4
             / \
          * 4   8        sum = 17
           /   / \
          11  13  4      list = [5, 4]
         /  \    / \ 
        7    2  5   1    res
```

####  helper(11, 13, list, res);
```

              5          root = 11
             / \
            4   8        sum = 13
           /   / \
        * 11  13  4      list = [5, 4, 11]
         /  \    / \ 
        7    2  5   1    res
```
####  helper(7, 2 , list, res);
```

              5          root = 7
             / \
            4   8        sum = 2
           /   / \
          11  13  4      list = [5, 4, 11, 7]
         /  \    / \ 
      * 7    2  5   1    res

```

####  helper(null , sum - root.val, list, res);
```
root == null return;
```

####  helper(null, sum - root.val, list, res);
```
root == null return;

              5          root = 7
             / \
            4   8        sum = 0
           /   / \
          11  13  4      list = [5, 4, 11]
         /  \    / \ 
      * 7    2  5   1    res

```
#### list.remove(list.size() - 1);
```

              5          root = 7
             / \
            4   8        sum = 0
           /   / \
          11  13  4      list = [5, 4, 11]
         /  \    / \ 
      * 7    2  5   1    res

```

#### Back to  helper(11,right, 2, list, res);
```

              5          root = 2
             / \
            4   8        sum = 2
           /   / \
          11  13  4      list = [5, 4, 11, 2]
         /  \    / \ 
        7   *2  5   1    res = [[5, 4, 11, 2]]

```
#### left and right are both null
#### list.remove(list.size() - 1); 
```

              5          root = 2
             / \
            4   8        sum = 2
           /   / \
         *11  13  4      list = [5, 4, 11]
         /  \    / \ 
        7    2  5   1    res = [[5, 4, 11, 2]]

```

#### list.remove(list.size() - 1);
```

              5          root = 2
             / \
           *4   8        sum = 2
           /   / \
          11  13  4      list = [5, 4]
         /  \    / \ 
        7    2  5   1    res = [[5, 4, 11, 2]]

```

#### list.remove(list.size() - 1);
```

            * 5          root = 2
             / \
            4   8        sum = 2
           /   / \
          11  13  4      list = [5]
         /  \    / \ 
        7    2  5   1    res = [[5, 4, 11, 2]]

```
#### Start the helper(root.right) and back to 5