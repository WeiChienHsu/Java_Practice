# Subsets
- [1,2,3]
```
[]
[1]
[2]
[3]
[1,2]
[2,3]
[1,3]
[1,2,3]
```

## DFS 解法
- 不使用BFS，因為需要將所有可能情況存入，浪費空間。
- 每一次進入下一層，都是現在這個index + 1
```java
dfsHelper(subset, nums, i + 1, res);
```


#### 如何避免 [1,2] [2,1] 這種重複性問題？
- 搜索中的去重問題：
- (錯誤) 先找到所有答案，再找重複：浪費時間
- (正確) 選代表： 先排序，選中[1,2,3]做為代表，這樣就可以用一個startIndex來避免重複放入List中

#### 如何設置Recursion
- 每一次Recursion要做的事情，把subset放入，一個startIndex，排序過的nums[]，紀錄的List
```java
defHelper(List<Integer> subset, int[] nums, int startIndex, List<List<Integer>> res){ }
```
- 將現在的subset狀態加入List中（注意：必須要new一個新的ArrayList，並加入目前的subset，因為subset只是reference，最終狀態會回到[]，我們加入nums[i]後，改變了他reference指向的地點。）
- 回傳：新的subset
- 使用for loop，每次加入一個i值進入list之後，進入下一層DFS
- Backtracking，回上一層前，必須將List的內容移除
```java
subset.remove(subset.size() - 1);
```

## DFS
```java
    private void dfsHelper(List<Integer> subset, int startIndex, List<List<Integer>> res, int[] nums) {
        
        // Add [] into result List
        res.add(new ArrayList<Integer>(subset));
        
        // BFS Path:
        // [1] -> [1,2] -> [1,2,3] -> [1,2] -> [1] -> [1,3]
        
        // for [1] -> [2] -> [3]
        for(int i = startIndex; i < nums.length; i++) {
            subset.add(nums[i]);
            // [1] -> [1,2] ->[1,2,3]
            dfsHelper(subset, i + 1, res, nums);
            // [1,2,3] -> [2,3]
            subset.remove(subset.size() - 1);
        }
    }
```
