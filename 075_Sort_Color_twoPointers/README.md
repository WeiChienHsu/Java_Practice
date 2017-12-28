# Sort Color 荷蘭國旗算法
### Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

```java
package com.liuzhen.array_2;

public class HollandFlagProblem {
    //输出荷兰国旗问题后的排序结果，时间复杂度为O(n)，空间复杂度为O(1)
    public void getHollandSort(int[] A){
        int begin = 0;
        int current = 0;
        int end = A.length - 1;
        while(current <= end){
            //值得注意的是：此处if语句是使用if-else if-else if，而没有使用if-if-if。这样使用保证每一次循环只执行一个条件，
            //否则，若使用if-if-if，可能会形成一次循环执行两到三个if条件，造成最终结果错误（PS:即在循环结束前，发生current > end）
            if(A[current] == 0){
                swap(A,begin,current);
                begin++;
                current++;
            }
            else if(A[current] == 1)
                current++;    
            else if(A[current] == 2){
                swap(A,current,end);
                end--;
            }
        }
        
        //输出排完序后的数组A相应元素
        System.out.println("对数组A进行划分后的元素顺序为：");
        for(int i = 0;i < A.length;i++)
            System.out.print(A[i]+" ");
    }
    
    //交换数组A中m位置和n位置上元素的值
    public void swap(int[] A,int m,int n){
        int temp = A[m];
        A[m] = A[n];
        A[n] = temp;
    }
    
    public static void main(String[] args){
        HollandFlagProblem test = new HollandFlagProblem();
        int[] A = {2,0,2,0,0,2,1,1,0,2,1,0,1,2,0,1,2,0,1,0,2,1,0,2,0,1,2,0,1,2,0,2,1,0};
        test.getHollandSort(A);
    }
}
```



```
2 0 1 2 0 1
->
0 0 1 1 2 2
```

## Solution - One Path
```
 2   0   1   2   0   1  
 l                   r
 s

 meet 2  -> switch with r / r move left / s--

 1   0   1   2   0   2  
 l               r
s

 s++ -> meet 1 -> move on 

  1   0   1   2   0   2 
  l               r
      s

 meet 0 -> swith with l / l move right / s ++

  0   1   1   2   0   2
      l           r
          s

meet 1 -> move on 

  0   1   1   2   0   2
      l           r
              s

 meet 2  -> switch with r / r move left / s--

  0   1   1   0   2   2
      l       r
           s    

s++ -> meet 0 -> swith with l / l move right / s ++

 0   0   1   1   2   2
         l   r
             s 


```
- Try to separate them into three groups (subArray)
- Use Two pointers (left&right) and one scanner
```java
int left = 0; //Left boundary of processed 0
int right = nums.length -1; // Right boundary of processed 2
int cur = 0; // Scanner for un processed data
```


- If meet 2, switch the number of scanner with right pointer and move left the right pointer
* Need to move backforward scanner since we didn't sure if the right pointer pointing to a value which is smaller than 1
```java
if(nums[cur]  == 2) {
    nums[cur] = nums[right];
    nums[right] = 2;
    right --;
    cur --;
```


- If meet 1, just move on
```java
cur ++;
```

- If meet 0, swith the number of scanner with left pointer and move right the left pointer
* Now we don't need to move back scanner since the numbers behind it are always be 0
```java
else if(nums[cur] == 0) {
    nums[cur] = nums[left];
    nums[left] = 0;
    left ++;
} 
```

***

## Solution - Swap

```java
    public void sortColors(int[] nums) {
        if(nums == null || nums.length < 1){
            return;
        }
        
        int left = 0;
        int right = nums.length - 1;
        int cur = 0;
        
        while(cur <= right){
            if(nums[cur] == 0) {
                swap(nums, cur++, left++);
            } else if(nums[cur] == 2) {
                swap(nums, cur, right--);
            } else{
                cur++;
            }
        }
    }
    
    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
```
