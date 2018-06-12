# Count and Say
The count-and-say sequence is the sequence of integers with the first five terms as following:
```
1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
```


## Solution - 字串處理
- 利用 Recursion 的方式解題，Base Case 就是當 n == 1 的時候 return 1
- 每次 Recursion funciton 都身成一個新的String給上個 Recursion Function 解決
- 判斷邊界條件，如果有重複的話 count ++ ，如果沒有重複，直接將 count 和 charAt(i-1) append 入 StringBuilder
- 注意邊界處理：如果要與「前一個比較」，第一個字就是默認地count++，並且檢查 i - 1 是否在邊界值內

```java
class Solution {
    public String countAndSay(int n) {
        // Base Case 
        if(n == 1) return "1";
        
        String currentString = countAndSay(n - 1); // Get into recursive step to get current String
        int count = 0;
        StringBuilder sb = new StringBuilder();
        // 將 String 轉換成 char array
        char[] c = currentString.toCharArray();
        
        for(int i = 0; i <= c.length; i++) {
          // 當 i 超過 邊界，或是在邊界內且與前個char相同時，處理加入
            if(i == c.length || (i - 1 >= 0 && c[i] != c[i - 1])) {
                sb.append(count);
                sb.append(c[i - 1]);
                count = 0;
            }
            // 預設第一個數字已經被計算
            count ++;
        }
        return sb.toString();
    }
}
```