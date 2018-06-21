## Decode Way
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

```
Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

```

## Solution - DP 問題
我們可以將這個題目分成很多subproblem，只要考慮新加入的數字，是否可以單獨成為一個valid數字，如果不行，我們就放棄前方累積下來的解，使用dp[]內預設的0，如果可以與前方數字組合成valid的數字，我們就沿用去掉前方一個數字的累積dp[]解。

- 注意條件，當第一個數字為0時，將dp內設為0，因為和後面的數字將無法組成任何可行解　

```
前方 介於 1 - 9
後方 介於 10 - 26

      1 2 2 6
dp[]  1 1 0 0 0
2 12  1 1 2 0 0
2 22  1 1 2 0 0
6 26  1 1 2 3 0

      2 7 0 5
dp[]  1 1 0 0 0
7 27  1 1 1 0 0
0 70  1 1 1 0 0
5 05  1 1 1 0 0

      0 2 1
dp[]  1 0 0
2 02  1 0 0
1 21  1 0 0
```

```java
public class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        
        for(int i = 2; i <= n; i++) {
            int oneletter = Integer.valueOf(s.substring(i-1, i));
            int twoletters = Integer.valueOf(s.substring(i-2, i));
            
            // 測試單獨一個是否可以沿用前面累積的解
            // "270" & "5"
            // 如果符合條件：沿用 "270" 可組成的解
            if(oneletter >= 1 && oneletter <= 9) {
               dp[i] += dp[i-1];  
            }
            
            // 測試與前面一個是否可組成， "03" 就無法組成
            // "27" & "05"
            // 如果符合條件，沿用"27"可以組成的解
            
            if(twoletters >= 10 && twoletters <= 26) {
                dp[i] += dp[i-2];
            }
        }
        
        return dp[n];
    }
}
```