class Solution {
    public int findKthLargest(int[] nums, int k) {
        if(nums.length == 0) return 0;
        
        int[] helper = new int[nums.length + 1];
        int start = 0;
        int end = nums.length - 1;
        doSort(nums, helper, start, end);
        return nums[nums.length - k];
    }

    public static void doSort(int[] arr, int[] helper, int start, int end) {
        if(start >= end) return;
        int mid = start + (end - start) / 2;
        doSort(arr, helper, start, mid);
        doSort(arr, helper, mid + 1, end);
        merge(arr, helper, start, mid, end);
    }

    private static void merge(int[] arr, int[] helper, int aStart, int aEnd, int bEnd) {
        // Copy arr from aStart to bEnd
        for (int i = aStart; i <= bEnd; i++ ) {
            helper[i] = arr[i];
        }
        
        int aCur = aStart;
        int bCur = aEnd + 1;

        for(int i = aStart; i <= bEnd; i++ ) {
            if (aCur > aEnd){
                arr[i] = helper[bCur++];
            } else if (bCur > bEnd){
                arr[i] = helper[aCur++];
            } else if (helper[aCur] <= helper[bCur]) {
                arr[i] = helper[aCur++];
            } else {
                arr[i] = helper[bCur++];
            }
        }
    }
}