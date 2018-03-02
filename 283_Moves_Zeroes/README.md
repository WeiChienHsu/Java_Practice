# Move Zeroes - Array with two Pointers
- i pointers: skip when meeting 0
- j pointers: Stop and Swithch number with cur i
- Corner Case: When there is only 1 Zero, i should not go out of the boundry
- After each round, i and j both move one step to next

```java
class Solution {
    public void moveZeroes(int[] nums) {
        
        int i = 0;
        int j = 0;
        while(i < nums.length) {
            
            while( i < nums.length && nums[i] == 0 )  {
                i++;
            }
            
            if(i >= nums.length) {
                break;
            }
            
            if(nums[j] == 0) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
            
            i++;
            j++;
        }
    }
}

```