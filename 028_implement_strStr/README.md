# Implement strStr
- Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
```
Input: haystack = "hello", needle = "ll"
Output: 2
```

## Solution
- Java : indexOf 用KMP算法實現 （庫函數實現題）
- 第一個指針判斷 source， i = 0 判斷到 i <= source.length() - target.length()
- 第二個指針在 target裡面，判斷是否符合每個字符，從 j = 0 判斷到 j < target.length()
- 如果 source.charAt(i+j) != target.chatAt(j) ，代表有相異的字符， i 前進下一個
- 如果判斷完，j == target.length() - 1，代表所有數字都和source相同
- 返回當時的 i 值
- 當 i 判斷到底，都無法找到相同的值，返回 -1
- Corner Case: target = "" rerturn -1 , source.length() == 0 return 0
- Time : O(m*n)

```java
class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack == null) {
            return -1;
        }
        
        if(needle.length() == 0) {
            return 0;
        }
        
        for (int i = 0; i <= (haystack.length() - needle.length()); i++) {
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                   break; 
                }
                if(j == needle.length() - 1){
                    return i;
                }
            }
        }
        return -1;
        }
}
```