# Find Duplicate Subtree
Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

```
Example 1: 
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:
      2
     /
    4
and
    4
```

Therefore, you need to return above trees' root in the form of a list.

## Solution - DFS
1. 先進行 Serizlize, 使用 DFS 逐一的把所有 Node 以及他的Children序列化
```java
public static String collect(TreeNode node) {
  if(node == null) return "#";
  String serial = node.val + "," + collect(node.left) + "," + collect(node.right);
}

```
2. 將該次Serizlize的結果存入 Map 裡， Key:序列化結果 Value:出現次數 (getOrDefault(serial, 0))
```java
  map.put(serial, map.getOrDefault(serial, 0) + 1);
```
3. 檢查這次的 Serial 有沒有出現在 Map 裡面超過 1 次，有的話，將此 Node 加入 result<TreeNode> 當中

```java
if(map.get(serial) > 1) {
  results.add(node);
}
```