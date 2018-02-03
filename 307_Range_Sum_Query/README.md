# Range Sum Query - Segment Tree
- 實現兩個Interface: sumRange(i,j) / update(i,j)
- You may assume the number of calls to update and sumRange function is distributed evenly

## Solution
- 把數組全部存起來
```
1  3  5  7  9
  /O(n)       \ O(1)
sumRange()    update() 
```

- 存 與前面的總和
```
1  4  9  16  25
    / O(1)  \O(n)
sumRange    update() -> 改動第一個，後面都要改
```
- 目標成為 O(logN) : depth of a Binary Tree Structure
```
            0,4,25
            /    \
       0,2,9      3,4,16     
       /  \        /   \
   0,1,4  2,2,5  3,3,7  4,4,9 
    / \
0,0,1  1,1,3
```

#### Define Tree
- 樹裡面帶有sum，以及兩邊的Start/End
```java
class SegmentTreeNode{
  int start;
  int end;
  int sum;
  SegmentTreeNode left;
  SegmentTreeNode right;

  public SegmentTreeNode(int start, int end){
    this.start = start;
    this.end = end;
    this.sum = 0;
    this.left = null;
    this.right = null;
  }
}
```

#### BuildTree
- 只存入 左右Index 和 sum
- 先 Binary Index ， 分成left subtree and right subtree (左邊包含mid)
- 由上圖可知，left == right 的時候，該點的值等於 root.sum
- left = start & mid // right = mid + 1 & end
- start == end sum = nums[end]
- Sum由左右兩邊節點的sum值總和
- root.sum = root.left.sum + root.right.sum
```java
public SegmentTreeNode buildTree(int[] nums, int start, int end) {
  if(start > end) return null;
  SegmentTreeNode root = new SegmentTreeNode(start, end);
  if(start == end) {
      root.sum = nums[end];
  } else {
    int mid = start + (end - start) / 2;
    root.left = buildTree(nums, start, mid);
    root.right = buildTree(nums, mid + 1, end);
    root.sum = root.left.sum + root.right.sum;
  }
  return root;
}
```

#### Update Tree
- Update(2,3):要先更新 start == 2 && end == 2的點，並且向上更新sum
```
            0,4,25
            /    \
       0,2,9      3,4,16     
       /  \        /   \
   0,1,4  2,2,5  3,3,7  4,4,9 
    / \
0,0,1  1,1,3

            0,4,25
            /    \
       0,2,9      3,4,16     
       /  \        /   \
   0,1,4  2,2,3  3,3,7  4,4,9 
    / \
0,0,1  1,1,3

            0,4,25
            /    \
       0,2,7      3,4,16     
       /  \        /   \
   0,1,4  2,2,3  3,3,7  4,4,9 
    / \
0,0,1  1,1,3

            0,4,23
            /    \
       0,2,7      3,4,16     
       /  \        /   \
   0,1,4  2,2,3  3,3,7  4,4,9 
    / \
0,0,1  1,1,3
```
- Find Node (root.start == root.end) 
- Change value of root.sum
- Update SUM
```java
public void SegmentTreeNode update(SegmentTreeNode root, int index, int newVal){
  if(root.left == root.right){
    root.sum = newVal;
    return;
  }
  int mid = root.start + (root.end - root.start);
  if(mid >= index) { //index in left
    update(root.left, index, newVal);
  } else { // index in right
    update(root.right, index, newVl)
  }
  root.sum = root.right.sum + root.left.sum; // Renew the sum
}
```

#### sumRange
- 考慮三種狀況
- |----e--|-----| mid >= end -> left(start, end)
- |------|--s---| mid < start -> right(start, end)
- |---s--|--e---| mid >= start && mid <= end -> left(start, mid) + right(mid+1, end)
```java
public int sumRange(SegmentTreeNode root, int start, int end) {
  if(start == root.start && end == root.end) {
    return root.sum;
  }

  int mid = root.start + (root.end - root.start);
  if(mid >= end) {
    return sumRange(root.left, start, end);
  } else if(mid < start) {
    return sumRange(root.right, start, end);
  } else{
    return sumRange(root.left, start, mid) + sumRange(root.right, mid + 1, end);
  }
}
```