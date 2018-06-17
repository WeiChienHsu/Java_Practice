# Contain Duplicated III (Bucket Sort & TreeSet)


## Solution - Fail

這次的差別在於，我們要找到兩個數字差別在於 t 內，並且 index 在 k 內的數字才可以回傳 true
我原本的想法，是跟之前一樣，每次在Set中新增一個數字，並且移除 k + 1 之後的數字，然後再新增一個數字前，檢查這個數字 +- t 是否在 set 裡面。

- 此方法可以在小範圍的數字內解決問題，但如果今天 t = Integer.MAX_VALUE，代表我們必須要loop through一次兩倍長度的最大Integer，一定會產生 Time Limit Exceeded 的問題

```java
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        Set<Long> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(i > k) set.remove((long)nums[i - k - 1]);
            if(findNumberRangeInSet(set,(long)nums[i],(long)t)) return true;
            set.add((long)nums[i]);
        }
        return false;
    }
    
    public static boolean findNumberRangeInSet(Set<Long> set, long target, long t) {
        for(long i = target - t; i <= target + t; i++) {
            if(set.contains(i)) return true;
        }
        return false;
    }
}
```
```
Last executed input:
[0,2147483647]
1
2147483647
```

## Solution - Buckets Sort or TreeSet

1. 遍歷一次original array
2. 紀錄在 Bucket 或 TreeSet內
3. 找尋 nums[i] <= t <= nums[j]
4. i <= k <= j

### TreeSet

- 紅黑樹：排序的Set
- ceiling: 返回大於或等於 element 的最小元素
- floor: 返回小於或等於 element 的最大元素
- TreeSet[1,2,4,7,12] num = 8 .ceil() = 12 .floor() = 7

#### 邏輯: 每次在原始nums array內遍歷一個數字，都在 TreeSet 裡面找尋該數字 +- t 的邊界在哪
檢查 在 nums 中是否有數字介於 nums[i] + t 和 nums[i] - t 之間，利用 floor(nums[i] + t) 找比最大值小的最大數，利用ceil(nums[i] - t) 找比最小值大的最小數，因此我們拿到一個區間，再用 nums[i] 來當基準比較，只要 floor != null && floor >= nums[i] 或 ceil != null && ceil <= nums[i] 代表對nums[i]正負相差t的數字，存在於set當中。

```
8 <= floor <= 8 + 2
8 - 2 <= cel <= 8
```


```
[1, 3, 4, 8] 
t = 2

1
[]
set 內沒有數字，不符合，加入1

3
[1]
找尋 3+2 和 3-2 在TreeSet裡面的 ceil 和 floor
ceil（1) = 1
floor(5) = 1

檢查 3 是否 >= ceil(1) 或 3 是否 <= floor(5) 

```
***

## Solution - Bucket Sort

