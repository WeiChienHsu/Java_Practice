class Solution {
    
    public int findKthLargest(int[] nums, int k) {
       if(nums == null || nums.length < k) return Integer.MIN_VALUE;
       return kLargeR(nums, 0, nums.length - 1, nums.length - k);
   }

   public int kLargeR(int[] nums, int start, int end, int k) {
       if(start > end) {
           return Integer.MIN_VALUE;
       }
       int pivot = nums[end];
       int pos = start;

       for(int i = start; i < end ; i++) {
           if(nums[i] <= pivot) {
               swap(nums,pos++,i);
           }
       }

       swap(nums,pos,end); // set pivot to the partition position

       if(pos == k) {
           return nums[pos];
       } else {
           return pos < k ? kLargeR(nums, pos + 1 , end, k) : kLargeR(nums, start, pos - 1 , k);
       }
   }

   public  void swap(int[] arr , int i , int j) {
           int temp = arr[i];
           arr[i] = arr[j];
           arr[j] = temp;
       }
   }
