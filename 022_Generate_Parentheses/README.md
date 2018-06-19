# Generate Parentheses

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

```
For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```


## Solution - DFS

利用左右兩個int來記錄已經放入combination的數量。

條件：

1. 如果 left < right -> 不合理，因為 left 應該永遠 > right
2. left != n 繼續 DFS left + 1
3. right != n 繼續 DFS right + 1
4. 當 left == n && right == n 將目前的StringBuilder結果加入 combinaiton 當中

```java
class Solution {
    public static List<String> generateParenthesis(int num) {

        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        dfsHelper(result, 0, 0, num, sb);
        return result;
    }

    public static void dfsHelper(List<String> result, int left, int right, int num, StringBuilder sb){
        if(right > left) return;
        
        if(left != num) {
            sb.append("(");
            dfsHelper(result, left + 1, right, num, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        if(right != num) {
            sb.append(")");
            dfsHelper(result, left, right + 1, num, sb);
            sb.deleteCharAt(sb.length() - 1);   
        }

        if(right == num && left == num) {
            result.add(sb.toString());
            return;
        }
    }
}
```