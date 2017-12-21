public class merge {
    public static void main(String[] args) {
        int[] test = {1, 2, 40, 23, 10, 39};
        int[] test_sorted = mergeSort(test);
        for( int i = 0; i < test.length; i++ ){
            System.out.println(test_sorted[i]);
        }

    }

    public static int[] mergeSort(int[] arr) {
        if(arr == null) return arr;
        int[] helper = new int[arr.length];
        doSort(arr, helper, 0, arr.length -1);
        return arr;
    }

    private static void doSort(int[] arr, int[] helper, int start, int end) {
        // End the recursion
        if(start >= end) return;

        int mid = start + (end - start) / 2;
        // Sort left side
        doSort(arr, helper, start, mid);
        // Sort right side
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
