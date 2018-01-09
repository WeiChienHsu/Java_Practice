public class kLargeR {

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(kLargeR(nums,k));
    }

    public static int kLargeR(int[] nums, int k) {
        if(nums == null || nums.length < k) return Integer.MIN_VALUE;
        return findKthLargest(nums, 0, nums.length - 1, nums.length - k);
    }

    public static int findKthLargest(int[] nums, int start, int end, int k) {
        if(start > end) {
            return Integer.MIN_VALUE;
        }
        int pivot = nums[end];
        int pos = start;

        for(int i = 0; i < end ; i++) {
            if(nums[i] <= pivot) {
                swap(nums,pos++,i);
            }
        }

        swap(nums,pos,end); // set pivot to the partition position

        if(pos == k) {
            return nums[pos];
        } else {
            return pos < k ? findKthLargest(nums, pos + 1 , end, k) : findKthLargest(nums, start, pos - 1 , k);
        }
    }

    public static void swap(int[] arr , int i , int j) {
        if(arr[i] == arr[j]) return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
