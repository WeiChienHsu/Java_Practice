# Restore IP Address
Given a string containing only digits, restore it by returning all possible valid IP address combinations.
```
Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
```

## Solution
將一串數字切分成四個區塊，每個區塊最高為三個數字，最少為一個數字，並且不可以 0 為開頭。

1. 先用三把刀來切數字， i, j, k
- i 從 1 開始， i < len - 2 要留最少三個數字， i < 4 因為最多只能三個數字
- j 從 i + 1 開始， j < len - 1 && j < i + 4
- k 從 j + 1 開始， k < len && k < j + 4

2. 利用 String.substring(begin, end) 來切開四個s (end 是 exclusive)
- s1 = s.substring(0, i)
- s2 = s.substring(i, j)
- s3 = s.substring(j, k)
- s4 = s.substring(k, len)

3. 使用 isStringValid 來檢查，這串String是否符合ip address
- len < 4 && len > 0
- first letter couldn't be '0' when s.length() != 0
- Integer.parseInt(s) <= 255


```java
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if(s.length() < 4) return result;
        int len = s.length();
        
        // Get three seperate point i j k
        for(int i = 0; i < 4 && i < len - 2; i++) {
            for(int j = i + 1; j < i + 4 && j < len - 1; j++) {
                for(int k = j + 1; k < j + 4 && k < len; k++) {
                    String s1, s2, s3, s4;
                    s1 = s.substring(0, i);
                    s2 = s.substring(i, j);
                    s3 = s.substring(j, k);
                    s4 = s.substring(k, len);
                    if(isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(s1);
                        sb.append(".");
                        sb.append(s2);
                        sb.append(".");
                        sb.append(s3);
                        sb.append(".");
                        sb.append(s4);
                        result.add(sb.toString());
                    }
                }
            }
        }
        return result;
    }
    
    public static boolean isValid(String s) {
        if(s.length() > 3 || s.length() == 0 ||
          (s.charAt(0) == '0' && s.length() > 1) || Integer.parseInt(s) > 255) return false;
        return true;
    }
}
```