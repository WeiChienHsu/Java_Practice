# Most Frequent Subtree Sum

- Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

## Solution
- 利用一個Map<總和, 出現頻率>，作為全局變量，另外還要有個 maxCount做紀錄
- 第一部分記錄下 frequency
```
PostOrder Travers 遍歷整棵樹：
- 紀錄左子樹
- 紀錄右子數
- 紀錄 sum = left + right + root.val
- 更新 maxCount 
```
- 第二部分，將Map內的數紀錄回int[]中
```
- 將所有的keySet遍歷一遍，取出value == maxCount 的 key
- 將Key放入int[]中
```

```java
class Solution {
    public int maxCount;
    public Map<Integer, Integer> map;
    
    public int[] findFrequentTreeSum(TreeNode root) {
        maxCount = 0;
        map = new HashMap<Integer, Integer>();
        
        postTraverse(root);
        
        List<Integer> res = new ArrayList<>();
        
        System.out.println(maxCount);
        
        for(int key: map.keySet()){
            if(map.get(key) == maxCount) {
                res.add(key);
            }
        }
        
        int[] result = new int[res.size()];
        for(int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        
        return result;
    }
    
    public int postTraverse(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        int left = postTraverse(root.left);
        int right = postTraverse(root.right);
        
        int sum = left + right + root.val;
        int count = map.getOrDefault(sum, 0) + 1;
        map.put(sum, count);
        
        maxCount = Math.max(maxCount, count);
        return sum;
    }
}
```