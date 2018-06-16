# Wiggle Subsequence (DP)

A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.

## Solution
- 最短的為 2 -> [1,2]
- 前後兩的數字差距為 - + - + 或 + - + -
- 差距不可為 0
- Subsequence 代表著我們可以從 array 內刪除一些數字不用選，找到最長的即可

### DP
利用 up and down 紀錄到達當前最大的連續上升下降數字。
會有三種狀況：
1. nums[i] > nums[i - 1]: 從下到上， up = down + 1（不論前方累積多少，都會是前一個下降的數字 + 1)
2. nums[i] < nums[i - 1]: 從上到下， down = up + 1
3. nums[i] == nums[i - 1]: 持平，up == up, down == down 不做處理

```java
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if(nums == null || nums.length < 1) return 0;
        int up = 1, down = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i - 1]) up = down + 1;
            else if(nums[i] < nums[i - 1]) down = up + 1;
        }
        return Math.max(down, up);
    }
}
```

