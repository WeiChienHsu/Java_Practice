# Regular Expression  Match
```
'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
```

## Solution
- Input :(String s, String p, int i, int j)
- a* means you could match both no charactors or n number of a
a.a*ac  abaaaac
- 判斷 p(i+1)是否 = '*'
- 等於*，代表要判斷a*得抵銷多少個數 -> 不抵銷或抵銷一個，進入Recursion
- 不等於*，直接看是否為'.'，或數字是否相同

```
                    a.a*ac                    abaaaac
p(i+1) != '*'               s(i) == p(i)
                            match(i+1, j+1)
                    .a*ac                     baaaac
p(i+1) != '*'   
                            s(i) == '.'
                            match(i+1, j+1)
                    a*ac                      aaaac
p(i+1) == '*'
                    |0            \1 
  match(i,j+2)     ac    aaaac    a*ac   aaac  match(i+1, j)
                    |                 |0      \ 1
                  FALSE         ac    aaac    a*ac  aac
                                      |         |0       \1
                                    FALSE    ac aac       a*ac   ac
                                                |           |0  
                                              FALSE       ac  ac  TRUE
```
- Base Case : i和j同時到底
```java
if(j == p.length()) return i == s.length();
```
- Case 1 : p[i+1] == '*' ：實現 match(i+1, j) 1個 / 或match(i,j+2) 0 個
- Check 當前這個點是否等於sample的數，如果不相同不需要比較，如果為.也不需要比較
- 判斷j和i是否越界
```java
 if(j < p.length() - 1 && p.charAt(j+1) == '*') {
            return i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') 
                && helper(s, p, i+1, j) || helper(s,p, i, j+2); // Match 0 or 1
```
- Case 2 : p[i+1] != '*'
- 直接判斷數字是否相同，或是等於'*'，接著往後走 helper(s,p,i+1,j+1)
```java
  } else { // Case 2 : p[i + 1] != *
      return i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && helper(s,p, i+1, j+1);
  }
```

***
- 優化：： Recursion -> Memorized Serach
```java
    private boolean helper(String s, String p, int i, int j, boolean[][] match) {
        // Base Case
        if(j == p.length()) {
            match[i][j] = (i == s.length());
            return match[i][j];
        }
        
        if(match[i][j]) return match[i][j];

        
        //Case1 : p[i + 1] == '*'
        if(j < p.length() - 1 && p.charAt(j+1) == '*') {
            match[i][j] =  i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') 
                && helper(s, p, i+1, j, match) || helper(s,p, i, j+2, match); // Match 0 or 1
            
        } else { // Case 2 : p[i + 1] != *
            match[i][j] =  i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && helper(s,p, i+1, j+1, match);
        }
        return match[i][j];
    }
}
```

- 假優化：因為如果 match[i][j] 可能是 default的value，並不是新加入的false value
- 可以用match[i][j] = 0 / 1 / -1 來判斷 ( Default = 0 / 新的state false = -1 / true = 1)
```java
if(match[i][j] != 0) {
  return match[i][j] == 1;
}
```
```java
class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return false;
        // 0 = default / 1 = true / -1 = false
        int[][] match = new int[s.length() + 1][p.length() + 1];
        helper(s, p, 0, 0, match);
        return match[s.length()][p.length()] == 1;
    }
    
    private boolean helper(String s, String p, int i, int j, int[][] match) {
        // Base Case
        if(j == p.length()) {
            match[i][j] = (i == s.length())? 1 : -1;
            return match[i][j] == 1;
        }
        
        if(match[i][j] != 0) return match[i][j] == 1;

        
        //Case1 : p[i + 1] == '*'
        if(j < p.length() - 1 && p.charAt(j+1) == '*') {
            match[i][j] =  (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') 
                && helper(s, p, i+1, j, match) || helper(s,p, i, j+2, match)) ? 1 : -1; // Match 0 or 1
            
        } else { // Case 2 : p[i + 1] != *
            match[i][j] =  (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') 
                && helper(s,p, i+1, j+1, match))? 1 : -1;
        }
        return match[i][j] == 1;
    }
}
```


