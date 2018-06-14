# Product of Array Except itself


## Solution - With additional spaces

紀錄除了 current Index 以左相乘的結果在 _fromLeft 的 array 裡面
紀錄除了 current Index 以右相乘的結果在 _fromRight 的 array 裡面
遍歷一遍_fromLeft 和 _fromRIght 得到新的解。

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] _fromRight = new int[nums.length];
        int[] _fromLeft = new int[nums.length];
        int[] result = new int[nums.length];
        
        _fromLeft[0] = 1;
        
        for(int i = 0; i < nums.length - 1; i++) {
            _fromLeft[i + 1] = _fromLeft[i] * nums[i];
        }
        
        _fromRight[nums.length - 1] = 1;
        
        for(int j = nums.length - 1; j > 0; j--) {
            _fromRight[j - 1] = _fromRight[j] * nums[j];
        }
        
        for(int k = 0; k < nums.length; k++) {
         result[k] = _fromRight[k] * _fromLeft[k];   
        }
        
        return result;
    }
}
```

## Solution without additional space

The idea is simply. The product basically is calculated using the numbers before the current number and the numbers after the current number. Thus, we can scan the array twice. First, we calcuate the running product of the part before the current number. Second, we calculate the running product of the part after the current number through scanning from the end of the array.

```java
public class Solution {
public int[] productExceptSelf(int[] nums) {
    int leng = nums.length;
    int[] ret = new int[leng];
    if(leng == 0)
        return ret;
    int runningprefix = 1;
    for(int i = 0; i < leng; i++){
        ret[i] = runningprefix;
        runningprefix*= nums[i];
    }
    int runningsufix = 1;
    for(int i = leng -1; i >= 0; i--){
        ret[i] *= runningsufix;
        runningsufix *= nums[i];
    }
    return ret;
    
}
```