# Jump Game
```
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
```

## Solution -  Graph (BFS)
- 兩個指針，一個不段紀錄每個點的最短路徑。(i=1)
```java
int i = 1; i < nums.length; i++
```
- 另一個指針，紀錄當前的出發點，如果步伐數限制已達到上限，指針前進。
```java
int j = curIndex; j < i; j++
```

- 如果 當前出發點 + 該點數字 （最遠的距離) >= i(我們要記錄的點index)
- j < i ???? 因為如果 j = i - 1時，還無法到達i，代表i是無法到達的。
- min[i] 當前紀錄的點的最小值為出發點min[j] + 1 （1步）
- curIndex 更新為 j
- 結束紀錄i點
```java
    public int jump(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] min = new int[nums.length];
        min[0] = 0;
        int curIndex = 0;
        
        for(int i = 1; i < nums.length ; i++) {
            for(int j = curIndex; j < i; j++ ) {
                if(nums[j] + j >= i) {
                    min[i] = min[j] + 1;
                    curIndex = j;
                    break;
                }
            }
        }
        return min[nums.length -1]; 
    }
```