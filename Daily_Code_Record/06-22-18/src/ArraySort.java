public class ArraySort {

    public static void main(String[] args) {
        int[] arr = new int[]{1,5,4,2,3};
        int[] newArr = sortArray(arr);
        for(int i = 0; i < newArr.length; i++) {
            System.out.println(newArr[i]);
        }
    }

    public static int[] sortArray(int[] arr) {
        int i, j, temp, len = arr.length;
        for(i = 0; i <= len - 1; i++) {
            for (j = i; j < len; j++) {
                temp = 0;
                if(arr[i] < arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
