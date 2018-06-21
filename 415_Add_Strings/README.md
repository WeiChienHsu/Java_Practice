# Add Strings
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

```
Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
```

## Solution

用兩個Pointers處理兩數字每個位數的相加，carry 紀錄進位的數字。
處理StringBuilder的時候，因為append進入的數字將是相反的，所以最後要reverse()，這裡不要使用StringBuilder sb.insert(0, xxx)，耗費更多的時間，直接reverse整個Instance比較快。

```java
class Solution {
    public String addStrings(String num1, String num2) {
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry != 0; i--, j--) {
            int n1 = i < 0 ? 0 : num1.charAt(i) - '0';
            int n2 = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            sb.append(sum % 10);
        }
        
        return sb.length() == 0 ? "0" : sb.reverse().toString();
    }
}
```