# Combination Sum II
- 跟一的差別在於：不能重複使用數字
- 注意：要判斷是否取了不同位置，但是相同大小的數字
- 例如: [1,1,2] target 3 -> 只要取第一個1 (利用 i>start && nums[i-1] == nums[i] 過號不選)
- 每進入下一輪DFS時，startIndex必須 + 1 !

## Solution - DFS

跟 Combination I 有兩點不同：
- 不要重複取相同的值（如果前面已經跳過，代表需要的數量已達到）
```java
            if(startIndex != i && candidates[i] == candidates[i-1]) {
                continue;
            }
```
- 每一次DFS從下個數字開始（不重複使用數字）
```java
dfsHelper(combination, i + 1, remainTarget - candidates[i], candidates, results );
```

- Code:

```java
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if(candidates == null) {
            return results;
        }
        Arrays.sort(candidates);
        List<Integer> combination = new ArrayList<>();
        dfsHelper(combination, 0, target, candidates, results);
        return results;
    }
    
    private void dfsHelper(List<Integer> combination, 
                            int startIndex, 
                            int remainTarget, 
                            int[] candidates, 
                            List<List<Integer>> results) {
        
        if(remainTarget == 0) {
            results.add(new ArrayList<>(combination));
            return;
        }
        
        for(int i = startIndex ; i < candidates.length; i++ ) {
            
            if(startIndex != i && candidates[i] == candidates[i-1]) {
                continue;
            }
            
            if(remainTarget < candidates[i]) {
                break;
            }
            
            combination.add(candidates[i]);
            dfsHelper(combination, i + 1, remainTarget - candidates[i], candidates, results );
            combination.remove(combination.size() - 1);
            
        }
    }
}
```