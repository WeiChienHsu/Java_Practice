# Contain Duplicated

## Solution
- 不可以Sort Array，因為要檢查兩個相同的數字之間的差，是否 <= k
- 最簡單的方法就是用 double for loop O(n^2) 的方式解題，找出相同的數字，並檢查其差別是否 <= k

```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for(int i = 0; i < nums.length - 1; i++) {
            for(int j = i + 1; j < nums.length; j++ ) {
                if(nums[i] == nums[j] && j - i <= k) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

### 進階解法 Time O(n) Space O(n)

- 利用一個Set紀錄所有數字，當指針到達 k + 1 這個位置時，先將 nums[current - k - 1]這數字從set中移除
- 過程中，如果在set裡面已經有目前指向的數字，return true，否則 return false

```java
class Solution {
  
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        
        for(int i = 0; i < nums.length; i++) {
            if(i > k) set.remove(nums[i - k - 1]);
            if(!set.add(nums[i])) return true;
        }
        return false;
    }       
}
```