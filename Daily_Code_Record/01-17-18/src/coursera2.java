public class coursera2 {
    public static void main(String[] args) {
        int n = 12;
        int[] arr = new int[n+1];
        int[] size = new int[n+1];
        initialize(arr,size,12);
        Union(arr,size,2, 10);
        Union(arr,size,7, 5);
        Union(arr,size,6, 1);
        Union(arr,size,3, 4);
        Union(arr,size,5, 11);
        Union(arr,size,7, 8);
        Union(arr,size,7, 3);
        Union(arr,size,12, 2);
        Union(arr,size,9, 6);
        int ans = 1;
        for(int i = 0; i < size.length; i++) {
            System.out.println(size[i]);
        }


    }

    public static void initialize(int[] arr, int[]size, int n) {
        for(int i = 1; i <= n; i++){
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
