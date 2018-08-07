public class ArrayOpearaion {
    public static void main(String[] args) {
        int[] res = ArrayOpearaion.replaceValues(new int[]{0,1,2});
        for(int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

    public static int[] replaceValues(int arr[]) {
        int i, j, len = arr.length;
        if(len % 2 == 0) {
            for(i = 0; i < len; i++) {
                arr[i] = 0;
            }
        } else {
            for(j = 0; j < len; j++) {
                arr[j] = 1;
            }
        }
        return arr;
    }
}
