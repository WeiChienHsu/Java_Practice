public class coursera3 {
    public static void main(String[] args) {
        int n = 8;
        int[] arr = new int[n];
        int[] size = new int[n];
        initialize(arr,size,7);
        Union(arr,size,0, 1);
        Union(arr,size,1, 2);
        Union(arr,size,2, 3);
        Union(arr,size,3, 4);
        Union(arr,size,4, 5);
        Union(arr,size,5, 6);
        Union(arr,size,6, 7);

        int ans = 1;
        for(int i = 0; i < size.length; i++) {
            System.out.println(arr[i]);
        }


    }

    public static void initialize(int[] arr, int[]size, int n) {
        for(int i = 0; i <= n; i++){
            arr[i] = i;
            size[i] = 1;
        }
    }

    public static void Union(int[] arr, int[] size, int A, int B) {
        int root_A = root(arr, A);
        int root_B = root(arr, B);
        if(size[root_A] < size[root_B]) {
            arr[root_A] = arr[root_B];
            size[root_B] += size[root_A];
        } else {
            arr[root_B] = arr[root_A];
            size[root_A] += size[root_B];
        }
    }


    public static int root (int[] arr, int i) {
        while(arr[i] != i) {
            arr[i] = arr[arr[i]];
            i = arr[i];
        }
        return i;
    }



}
