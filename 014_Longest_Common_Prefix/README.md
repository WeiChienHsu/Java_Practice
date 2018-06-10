# Longest Common Prefix
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".
```
Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
```
## Solution 
題目要我們找出所有String Array內，重複的單字，例如 [flower, flow, flim]，我們將 flower 拿來一個一個移除單詞 (substring)，直到和第二個flow相同，再拿新的結果 flow 與 flim 比較，一個一個移除單詞(substring)

- 給予一個新的 String newString = strs[0]
- for loop 整個 string arrays
- while(newString != strs[i]) -> newString = newString.substring(0, newString.length() - 1); 一個一個消除
- String.indexOf(chr) : 如果該字段不在 String 內，會return -1，如果在裡面的話會 return 第一個 index

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0 || strs == null) return "";
        String newString = strs[0];
        for(int i = 0; i < strs.length; i++) {
            while(strs[i].indexOf(newString) != 0 && newString.length() > 0 ) {
                newString = newString.substring(0, newString.length() - 1);
                System.out.println(newString);
            }
        }
        return newString;
    }
}
```