# 739. Daily Temperatures


## Solution - Failure

Status: Time Limit Exceeded

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Arrays.fill(result, 0);
        for(int i = result.length - 1; i > 0; i--) {
            int count = 1;
            for(int j = i - 1; j >= 0; j--) {
                if(temperatures[i] > temperatures[j])
                    result[j] = result[j] == 0? count : Math.min(result[j], count);
                count++;
            }
        }
        
        for(int res : result) {
            System.out.print(res + " ");
        }
        
        return result;
    }
}
```

## Solution - Stack

用 Stack 紀錄每個待處理的數字，只有在 當前處理 temp 比 peek 還要大的時候，才要更新result

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < result.length; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pollFirst();
                result[index] = i - index;
            }
            stack.offerFirst(i);
        }
        return result;
    }
}
```