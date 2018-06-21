# Multiply Strings
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
```
Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
```

## Solution - Failure

無法處理 Input 過大的情況，不可以直接將String換成Integer

```java
class Solution {
    public String multiply(String num1, String num2) {
        char[] chars = new char[]{'0','1','2','3','4','5','6','7','8','9'};
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < chars.length; i++) {
            map.put(chars[i], i);
        }
        
        int sum = parseString(num1, map) * parseString(num2, map);
        return parseInteger(sum, chars);
        
    }
    
    public static String parseInteger(int num, char[] chars) {
        StringBuilder sb = new StringBuilder();
        while(num >= 10) {
            int current = num % 10;
            sb.insert(0, chars[current]);
            num /= 10;
        }
        
        sb.insert(0, chars[num]);
        
        
        
        return sb.toString();
    }
    
    public static int parseString(String s, Map<Character, Integer> map) {
        int num = 0;
        for(char c : s.toCharArray()) {
            num *= 10;
            num += map.get(c);
        }
        return num;
    } 
}
```

## Solution - 分析乘法原理
Double For Loops 分別遍歷 String，從個位數開始(length - 1)， i 和 j 會影響到的是 i + j 以及 i + j + 1 的位數，我們用一個 int[] pos 來記錄相乘之後的結果（pos[0]為個位數）。

1. 先算出 mult = num[i] * num[j] (可能會超過個位數 ex 12，要將上一位的進位加入結果中)
2. 處理前一位的進位 mult += pos[i+j+1]
3. 放入對應的position當中, 個位數直接放 pos[i+j+1] = mult % 10, 進位數要加上去原本的 pos[i+j] += mult / 10
4. 因為position已經是照著位數順序擺放 [1, 2, 4, 0, 8]，我們只要用 StringBuilder依序的append回sb裡面，即可得到 "12408" （注意第一位放入不可為0，即是長度 == 0的時候，不可以加入 0)

```java
class Solution {
    public String multiply(String num1, String num2) {
        int[] pos = new int[num1.length() + num2.length()];
        for(int i = num1.length() - 1; i >= 0; i--) {
            for(int j = num2.length() - 1; j >= 0; j--) {
                int p1 = i + j; // 十位數
                int p2 = i + j + 1; // 個位數
                int mult = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + pos[p2]; // 補上前一個的十位數
                pos[p1] += mult / 10;
                pos[p2] = mult % 10;        
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int n : pos) {
            if(sb.length() == 0 && n == 0) continue;
            sb.append(n);
        }
        
        return sb.length() == 0? "0" : sb.toString() ;
    }
}

```

