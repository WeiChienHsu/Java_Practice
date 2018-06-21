## 459. Repeated Substring Pattern

```
Example 1:
Input: "abab"

Output: True

Explanation: It's the substring "ab" twice.
Example 2:
Input: "aba"

Output: False
Example 3:
Input: "abcabcabcabc"

Output: True

Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
```

## Solution - test the reasonable length of string

本來是想用從 1個到 ... 長度 /2 個數字不段的嘗試，生成出測試用的substring，中間如果有和後方不同的數字出現時，跳出for loop，或是長度無法整除於原始string長度的時候，不進入下一層for loop檢查，但由於input可能會到非常大，所以剛開始直接生成Substring的方法不可行。

但！我發現可以直接用長度來判斷，例如input可能是 1xxxxxxx，我們只需要嘗試 1xxxxx % i == 0 的substring就可，所以改由先檢查長度，再生成substring就可行了～

```java
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        for(int i = 1; i <= s.length() / 2 + 1 ; i++) {
            
            
            // 如果擷取的重複字串已經大於元字串一半長，後面不需要判斷
            if(i * 2 > s.length()) return false;
            // 如果擷取的字串無法整除原字串長度，不需要考慮後面是否相同
            if(s.length() % i != 0) continue;
            
            // 驗證後面是否出現不同的數目
            
            String currentRecord = s.substring(0, i);
            boolean pass = true;
            
            int k;
            for(k = i; k < s.length() - i + 1; k = k + i) {
                String nextRecord = s.substring(k, k + i);
                if(!nextRecord.equals(currentRecord)) {
                    pass = false;
                    break;
                }
            }
            
            if(pass && k >= s.length()) return true;
            pass = true;
        }
        return false;
    }
}
```