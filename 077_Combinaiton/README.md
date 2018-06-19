## Combination


Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

```
Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
```

## Solution
找出 n 個 1 - k 的數字 Combination.

遍歷圖的題目，先放入第一層，接著放入第一層數字 + 1，利用for loop控制邊界在 n，不斷加入下一層的 dfsHelper，讓start ++，返回條件是當 combination size == 需要的 n 時，加入List當中。

```java
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if(n == 0 && k == 0) return result;
        List<Integer> combination = new ArrayList<>();
        dfsHelper(result, 1, n, k, combination);
        return result;
    }
    
    public static void dfsHelper(List<List<Integer>> result, int start, int n, int k, List<Integer> combination) {
        
        if(combination.size() == k) {
            result.add(new ArrayList<Integer>(combination));
            return;
        }
        
        for(int i = start; i <= n; i++) {
            combination.add(i);
            dfsHelper(result, i + 1, n, k, combination);
            combination.remove(combination.size() - 1);
        }
    }
}
```