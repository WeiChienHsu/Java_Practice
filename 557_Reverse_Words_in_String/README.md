# Reverse Words in String

Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

```
Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
```

## Solution

利用 StringBuilder 重組。

```java
class Solution {
    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] strs = s.split(" ");
        for(String str : strs) {
            sb.append(reverse(str));
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public static String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

}
```