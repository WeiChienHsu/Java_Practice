# Longest Palindrome
```
Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
```

## Solution
先將所有的字母放入Map當中（大小寫有別），然後遍歷一次Map，如果出現基數個字母時，將結果標記為基數（要+1），然後將每個字母有的數字先除以二，得到一個整數，之後再乘以二，代表可以造成 Palindrome 的字串長度。

```java
    public static int longestPalindrome(String s) {
        if(s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int len = 0;
        boolean isOdd = false;

        for(int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for(char c : map.keySet()) {


            if(map.get(c) % 2 != 0 && !isOdd) isOdd = true;
            len += (map.get(c) / 2) * 2;
        }

        return len = isOdd ? len + 1 : len ;
    }
```