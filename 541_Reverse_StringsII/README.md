# Reverse Strings II
Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.

```
Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"
```

## Solution
因為加入了 0 -> k 需要反轉，k -> 2k 不需要反轉的條件，我們要判斷：
1. 每k個轉一次，另外k個不轉，所以要記錄當下狀況是否要轉。
2. 因為 for loop 每次跳 4個，所以要判斷最後 index 不等於 string.length() 的時候，代表還有剩下的數字，繼續沿用剛才的紀錄，如果要反轉就反轉，不需要則否。
3. 注意： reverse() 是使用在 StringBuilder 上的。

```java
class Solution {
    public String reverseStr(String s, int k) {
        StringBuilder sb = new StringBuilder();
        boolean needToReverse = true;
        
        // Corner Case
        if(k > s.length()) {
            sb.append(s);
            return sb.reverse().toString();
        }
        
        // Init index out of the for scope since we need to track the rest of our string
        int i;
        for(i = 0; i <= s.length() - k; i = i + k) {
            StringBuilder temp = new StringBuilder();
            if(needToReverse) {
                temp.append(s.substring(i, i + k)).reverse();
            } else {
                temp.append(s.substring(i, i + k));
            }
            
            sb.append(temp);
            needToReverse = !needToReverse;
        }
        
        
        // Rest of the String not larger than k, will have two situation
        if(i != s.length()) {
            if(needToReverse) {
                StringBuilder last = new StringBuilder(s.substring(i, s.length()));
                sb.append(last.reverse());  
            } else {
                sb.append(s.substring(i, s.length()));
            }
            
        }
        
        return sb.toString();
    }
}
```