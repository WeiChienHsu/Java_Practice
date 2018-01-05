public class mergeSort {
    public static void main(String[] args) {
        int[] nums = {4,5,1,9,20,3,2,7};
        int [] sorted_nums = sort(nums);
        for(int num : sorted_nums) {
            System.out.println(num);
        }
    }

    public static int[] sort(int[] nums){
        if(nums == null) return nums;
        int[] helper = new int[nums.length];
        doSort(nums, helper, 0, nums.length - 1) ;
        return nums;
    }

    public static void doSort(int[] nums, int[] helper, int start, int end){
        if(start >= end) return;
        int mid = start + (end - start) / 2;
        doSort(nums, helper, start, mid);
        doSort(nums, helper, mid + 1, end);
        merge(nums,helper,start,mid,end);
    }

    private static void merge(int[] nums, int[]helper, int startA, int endA, int endB){
        for(int i = startA ; i <= endB; i++){
            helper[i] = nums[i];
        }

        int curA = startA;
        int curB = endA + 1;

        for(int i = startA; i <= endB; i++) {
            if(curA > endA) {
                nums[i] = helper[curB++];
            } else if (curB > endB) {
                nums[i] = helper[curA++];
            } else if (helper[curA] <= helper[curB]) {
                nums[i] = helper[curA++];
            } else {
                nums[i] = helper[curB++];
            }
        }

    }

}
