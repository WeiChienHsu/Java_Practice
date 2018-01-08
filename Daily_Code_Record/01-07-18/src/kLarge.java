public class kLarge {

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(kLarge(nums,k));
    }

    public static int kLarge(int[] nums, int k) {
        return mergeSort(nums)[nums.length - k];
    }

    private static int[] mergeSort(int[] nums) {
        int[] helper = new int[nums.length + 1];
        int start = 0;
        int end = nums.length - 1;
        doSort(nums, helper, start, end);
        return nums;
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
                // use out a
                arr[i] = helper[bCur++];
            } else if (bCur > bEnd){
                // use out b
                arr[i] = helper[aCur++];
            } else if (helper[aCur] <= helper[bCur]) {
                // a < b
                arr[i] = helper[aCur++];
            } else {
                arr[i] = helper[bCur++];
            }

        }
    }

}
