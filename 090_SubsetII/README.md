# Subset II

Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

```
Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
```

## Solution - Combination DFS

利用List<Integer> array.contains來去重複，其餘部分一樣使用 DFS Combination 解題。

```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        result.add(new ArrayList<>(combination));
        if(nums == null) return result;
        
        Arrays.sort(nums);
        dfsHelper(nums, 0, result, combination);
        return result;
    }
    
    public static void dfsHelper(int[] nums, int start, List<List<Integer>> result, List<Integer> combination) {
        
        if(!result.contains(combination)) {
            result.add(new ArrayList<>(combination));
        }
        
        for(int i = start; i < nums.length; i++) {
            combination.add(nums[i]);
            dfsHelper(nums, i + 1, result, combination);
            combination.remove(combination.size() - 1);
        }
    }
}
```