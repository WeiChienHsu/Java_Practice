# Pascal Triangle II


## Solution

每次處理的時候，在第一個位置增加1，把前一個循環的數字往後推(add(1,value))，然後從index == 1 的地方開始，將該值與後面一個值相加，改變(set(j, newValue))，相加的處理由前一個list的size來判斷，例如 [1,1]的下一層，j < 2，只需要處理一次。

```
1
11
111 -> 1 (1+1) 1 -> 121
1121 -> 1 (1+2) (2+1) 1 -> 1331
11331 -> 1 (1+3) (3+3) (3+1) 1 -> 14641
114641 -> 1 (1+4) (4+6) (6+4) (4+1) 1 -> 15101051
```

```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if(rowIndex < 0) return res;
        
        for(int i = 0; i < rowIndex + 1; i++) {
            
            // Add number 1 in the first position
            res.add(0, 1);
            
            // Sum the two consecutive number from index of 1 to the length - 1
            for(int j = 1; j < res.size() - 1; j++) {
                res.set(j, res.get(j) + res.get(j + 1));
            }
        }
        return res;
    }
}
```