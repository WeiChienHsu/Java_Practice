# House Robber III
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
```
     3
    / \
   4   5
  / \   \ 
 1   3   1

 4 + 5 OR 3 + 1 + 3 + 1 OR  3 + 4 + 1 OR 3 + 5 + 1 + 3 
```

## Solution
- 考慮 搶 root  / 不搶 root 
- 不搶 root 又分成 搶root.left root.right / 不搶 root.left 搶 root.right / 不搶 root.right 搶 root.left
```
rob(root) -> rob(root.left) & rob(root.right) & rob(root.left.left) & rob(root.right.left) 
             rob(root.left.right) rob(root.right.right)

rob(root.left) -> rob(root.left.right) & rob(root.left.left)
```
- Overlapping，遇上重複考慮的狀況，把該點的值存在Map內 Map<TreeNode, value>
- DP: 考慮 “optimal substructure” + “overlapping of subproblems"
- subRob 紀錄這個分支下能夠偷到最多的value
- Max Value 得法 = max(偷root不偷下層, 不偷root偷了下層的最大值)

- 先從最底下開始找，如果root == null -> 0
- 如果該點已經被計算並放入Map內-> return map.get(root)
- 往左找，if(root.left!=null) val += robSub(root.left.left) + robSub(root.left.right)
- 往右找，if(root.right!=null) val += robSub(root.right.left) + robSub(root.right.right)
- 更新最大值 val = max(root.val + val, robSub(root.left), robSub(root.right)) 搶root或不搶root
- 更新後，將這個root的最大值加回Map內
- robSub這個function要回傳該點能夠偷到的最大值 val

## Optimaztion - int[偷,不偷]
- 考慮偷左邊和偷右邊的最大值 left = robSub[root.left] / right = robSUb[root.right]
- 考慮偷root與不偷(左與右不偷的最大值）的最大值 int[root.val + left[1] + right[1] , max(left[1],left[0]) + max(right[1] + right[0]))]