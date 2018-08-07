import java.util.Arrays;

public class SortArray {
    public static void main(String[] args) {
        int[] res = SortArray.reverseArray(new int[]{1,2,3,4,5});
        System.out.print(Arrays.toString(res));

    }

    public static int[] reverseArray(int[] arr) {
        int i, temp, originalLen = arr.length;
        int len = originalLen;
        for(i = 0; i < originalLen / 2; i++) {
            temp = arr[ len - i - 1];
            arr[len - i - 1]  = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
}
