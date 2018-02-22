# Combination Sum
Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

- [2, 3, 6, 7] target = 7
- output -> [2,2,3] / [7]

### Difference from Subset Question
- There will be Duplicate numbers in List
- Input number will be duplicate [2,2,2,3] -> [2,3]
- There is a target number

## DFS Solution (Dont forget to SORT the array fitst!!!!!!!)

```java
public void helper(int[] candidates, 
                    int startIndex,
                    int remainTarget;
                    List<Integer> combination, 
                    List<List<Integer>> results) {
```
- 標記使用過的數字，來去重，碰到相同的數字直接continue跳過
#### 遞歸的定義：
- 找到所有以combination開頭的那些合為 target 的組合，
- 並丟到 results 當中，其中剩餘的需要加入combination 的數何為 remainTarget，
- 並且下一個加入 combination 的數，至少從 candidates 的startIndex開始
```java
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        
        if(candidates == null) {
            return results;
        }
        Arrays.sort(candidates);
        List<Integer> combination = new ArrayList<>();
        
        helper(candidates, 0, target, combination, results );
        return results;
```

#### 遞歸拆解：
- 一個for loop 從 startIndex開始
- 先寫combination加入與移除，代表著我們討論從7出發的第一層
- 加入recursion在中間，變動的值是remainTarget - candidates[i]
- 判斷remainTarget < candidate[i] 的狀況
- 判斷兩個duplicate數字的狀況(candidates[i] == candidates[i-1])
```java
for(int i = startIndex; i < candidates.length; i++) {
        
        if(remainTarget < candidates[i]) {
            break;
        }
        
        if(i != 0 && candidates[i] == candidates[i-1]){
            continue;
        }
        
        
        combination.add(candidates[i]); // [2]
        helper(candidates, i, remainTarget - candidates[i], combination, results);
        combination.remove(combination.size() - 1);
    }
```
#### 遞歸的出口：
- 當 remainTarget == 0 的時候，將combination加入results中，return!
```java
if(remainTarget == 0) {
    results.add(new ArrayList<Integer>(combination));
    return;
}
```