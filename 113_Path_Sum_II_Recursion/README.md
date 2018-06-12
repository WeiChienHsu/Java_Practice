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


***

## 在當層處理好 Sum 的方法，不需要判斷下層是否為Null

```java
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        // result.add(root.val);
        dfsHelper(root, sum, answer, result);
        return answer;
    }
    
    public static void dfsHelper(TreeNode root, int sum, List<List<Integer>> answer, List<Integer> result) {
        if(root == null) return;
        
        // 讓扣除Current Value的工作在每一層中處理
        result.add(root.val);
        sum -= root.val;
        
        if(root.left == null && root.right == null && sum == 0) {
                List<Integer> res = new ArrayList<>(result);
                answer.add(res);
        }
        
        // 分治法：處理左右子樹
        dfsHelper(root.left, sum, answer, result);
        dfsHelper(root.right, sum, answer, result);
        
        // 當這層處理完畢，回到上層時，需要backtracking，將array內新加入的這層數值移出
        result.remove(result.size() - 1);
    }
}

```

## 在當層處理好下層的Sum，需要先確定root.left 和 root.right 不是 Null

```java
    private void helper(TreeNode root, int sum, List<Integer> list, List<List<Integer>> res) {
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
    }
```