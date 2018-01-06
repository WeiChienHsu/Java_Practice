# Permutations II

## Given a collection of numbers that might contain duplicates, return all possible unique permutations.

```
For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

```



## Solution

- HashSet to avoid the same start
- Swap position than add those number in orders into result List
- Used DFS to go down the disicion tree

```
Since we only need permutations of the array, the actual “content” does not change, we could find each permutation by swapping the elements in the array.

The idea is for each recursion level, swap the current element at 1st index with each element that comes after it (including itself). For example, permute[1,2,3]:

At recursion level 0, current element at 1st index is 1, there are 3 possibilities: [1] + permute[2,3], [2] + permute[1,3], [3] + permute[2,1].

Take “2+permute[1,3]” as the example at recursion level 0. At recursion level 1, current elemenet at 1st index is 1, there are 2 possibilities: [2,1] + permute[3], [2,3] + permute[1].

… and so on.

Let’s look at another example, permute[1,2,3,4,1].

At recursion level 0, we have [1] + permute[2,3,4,1], [2] + permute[1,3,4,1], [3] + permute[2,1,4,1], [4] + permute[2,3,1,1], [1] + permute[2,3,4,1].

1 has already been at the 1st index of current recursion level, so the last possibility is redundant. We can use a hash set to mark which elements have been at the 1st index of current recursion level, so that if we meet the element again, we can just skip it.
```
```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfsHelper(nums, res, 0);
        return res;
    }
    
    public void dfsHelper(int[] nums, List<List<Integer>> res, int pos) {
        if(pos == nums.length) {
            List<Integer> list = new ArrayList<>();  
            for(int num : nums) { list.add(num);}
            res.add(list);
            return;
        }
        
        Set<Integer> used = new HashSet<>();
        for(int i = pos; i < nums.length; i++) {
            if(used.add(nums[i])) {
                swap(nums, i, pos);
                dfsHelper(nums, res, pos + 1);
                swap(nums, i, pos);
            }
        }
    }
    
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```
