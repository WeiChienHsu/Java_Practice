# Pascal Triangle

- 第 0 個加上 1
- 第 1 個 - 第 n - 1個 :  第i個 = 第i個 + 第i+1個


```
[1]

[1,1]

[1,1,1] -> [1,2,1]

[1,1,2,1] -> [1,3,3,1]

[1,1,3,3,1] -> [1,4,6,4,1]
```

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<Integer> res = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        for(int i = 0; i < numRows; i++) {
            
            res.add(0, 1);
            
            for(int j = 1; j < res.size() - 1; j++) {
                res.set(j, res.get(j) + res.get(j+1));
            }
            
            result.add(new ArrayList<Integer>(res));
        }
        return result;
    }
}
```