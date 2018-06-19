# Longest Palindromic String
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

```
Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
```

## Solution - dp

使用左右兩個指針，遍歷過一遍String，右指針在外層，左指針在內層 double for loop

使用boolean[][] 記錄每個指針走到地方，是否為一個Palindroic

建立一個boolean variable isInnerWordPalindormic，判斷一個字串是否Palindroic
- 判斷First Letter and Last Letter是否相同
- 其Inner Words ([i-1][j+1) 是否也是 Palindromic

```
abba -> a == a, bb isPalindromic

aba -> a == b, b only have one letter (i - j <= 2)
```

當 (s.charAt(i) == s.charAt(j) && isInnerWordPalindormic) 檢查目前length是否最長（利用 left ann right default == 0 紀錄）

返回 s.substring(i, j+1) -> (inclusive, exclusive)


```java
class Solution {
    public String longestPalindrome(String s) {
        int length = s.length();
        if(length < 2 || s == null) return s;
        
        int leftLongest = 0;
        int rightLongest = 0;
        
        boolean[][] isPalindrome = new boolean[length][length];
        
        for(int right = 1; right < length; right++) {
            for(int left = 0; left < right; left++) {
                boolean isInnerWordPalindorme = isPalindrome[left + 1][right - 1] || right - left <= 2;
                
                if(s.charAt(right) == s.charAt(left) && isInnerWordPalindorme) {
                    isPalindrome[left][right] = true;
                    // If the current Left and Right point to a longer String
                    if(right - left > rightLongest - leftLongest) {
                        leftLongest = left;
                        rightLongest = right;
                    }
                }
            }
        }
        return s.substring(leftLongest, rightLongest + 1);
    }
}
```