# Combination SUM III 
從 1 - 9 的數字中，找出 k 個可以相加為 n 的組合。

## Solution DFS + Backtracking
標準 DFS Combination 樣板。

- 出口 combination.size() = k || n == 0 的時候。
- DFS 內部， for loop 從 1 到 9 ，裡面不斷實現 backtrack algorithm 
- 加入新的數， target - 該數，丟入新的dfs當中嘗試（丟入下層），結束dfs，把array內最後一個數字移除（從下層回到上層）


```java
class Solution {
    
    //- 出口 combination.size() = n || k == 0 的時候。
    // - DFS 內部， for loop 從 1 到 9 ，裡面不斷實現 backtrack algorithm 
    // - 加入新的數， target - 該數，丟入新的dfs當中嘗試（丟入下層），結束dfs，把array內最後一個數字移除（從下層回到上層）

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        Solution.dfsHelper(res, combination, n, k, 1);
        return res;
    }
    
    public static void dfsHelper(List<List<Integer>> res, List<Integer> combination, int target, int total, int startNumber) {
        // if(startNumber > target || combination.size() > total) return;
        
        if(target == 0 && combination.size() == total) {
            System.out.println("hi");
            List<Integer> ans = new ArrayList<>(combination);
            res.add(ans);
            return;
        }
        
        for(int i = startNumber; i < 10; i++) {
            combination.add(i);
            Solution.dfsHelper(res, combination, target - i, total, i + 1);
            combination.remove(combination.size() - 1);
        }
    }
}

```