# Longest Increasing Subsequence

## Solution - DP
右指針 - 記錄到當前最長的遞增數量。
左指針 - Traversal 右指針以左的所有數字
當 右指針當前到達的 length <= 左指針指到的 lenght，更新右指針的數值(++)，代表到達左指針指的數字後，可以延伸到右指針這。

```
初始化為 1:
右指針i
左指針j

j  i
10 9 2 5 3 7 20 18
1  1 1 1 1 1  1  1

---
10 > 9 移動右指針

j    i
10 9 2 5 3 7 20 18
1  1 1 1 1 1  1  1

---
10 > 2
9 > 2 移動右指針

j      i
10 9 2 5 3 7 20 18
1  1 1 1 1 1  1  1

---
10 > 5
9  > 5
2  < 5 比較 2 和 5 的 length，因為 1 == 1， 5的Length++

j      i
10 9 2 5 3 7 20 18
1  1 1 2 1 1  1  1

---
10 > 3
9  > 3
2  < 3 比較 2 和 3 的 length，因為 1 == 1， 3的Length++
5  > 3 
移動右指針

j        i
10 9 2 5 3 7 20 18
1  1 1 2 2 1  1  1

---
10 > 7
9  > 7
2  < 7 比較 2 和 7 的 length，因為 1 == 1， 7的Length++
5  < 7 比較 5 和 7 的 length，因為 2 == 2， 7的Length++
3  < 7 比較 3 和 7 的 length，因為 2 < 3， 跳過
移動右指針

j          i
10 9 2 5 3 7 20 18
1  1 1 2 2 3  1  1

---
10 < 20 比較 10 和 20 的 length，因為 1 == 1， 20的Length++
9  < 20 比較 9 和 20 的 length，因為 1 < 2， 跳過
2  < 20 比較 2 和 20 的 length，因為 1 < 2， 跳過
5  < 20 比較 5 和 20 的 length，因為 2 == 2， 20的Length++
3  < 20 比較 3 和 20 的 length，因為 2 < 3， 跳過
7  < 20 比較 7 和 20 的 length，因為 3 == 3， 20的Length++
移動右指針

j            i
10 9 2 5 3 7 20 18
1  1 1 2 2 3  4  1

---
10 < 18 比較 10 和 18 的 length，因為 1 == 1， 18的Length++
9  < 18 比較 9 和 18 的 length，因為 1 < 2， 跳過
2  < 18 比較 2 和 18 的 length，因為 1 < 2， 跳過
5  < 18 比較 5 和 18 的 length，因為 2 == 2， 18的Length++
3  < 18 比較 3 和 18 的 length，因為 2 < 3， 跳過
7  < 18 比較 7 和 18 的 length，因為 3 == 3， 18的Length++

右指針 == nums.length -1 結束

j                i
10 9 2 5 3 7 20 18
1  1 1 2 2 3  4  4

---

Return 紀錄Array內的最大值
```

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return 1;
        
        int longestLength[] = new int[nums.length]; 
        
        // Init the Array with the longest Length of 1
        for(int i = 0; i < nums.length; i++) {
            longestLength[i] = 1;
        }
        
        for(int right = 1; right < nums.length; right++) {
           for(int left = 0; left < right; left++) {
               if(nums[left] < nums[right]) {
                   if(longestLength[right] <= longestLength[left]) {
                       longestLength[right]++;
                   }
               }
           } 
        }
        
        int longest = 0;
        for(int i = 0; i < nums.length; i++) {
            longest = Math.max(longest, longestLength[i]);
        }
    
        return longest;
    }
}
```

## Binary Search
