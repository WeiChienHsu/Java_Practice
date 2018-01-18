public class coursera {
    public static void main(String[] args) {
        int n = 12;
        int[] arr = new int[n+1];

    initialize(arr,n);
    union(arr,n,2,10);
    union(arr,n,7,5);
    union(arr,n,6,1);
    union(arr,n,3,4);
    union(arr,n,5,11);
    union(arr,n,7,8);
    union(arr,n,7,3);
    union(arr,n,12,2);
    union(arr,n,9,6);
    System.out.println(arr[6]);
        System.out.println(arr[3]);
        System.out.println(arr[11]);
        System.out.println(arr[9]);
    }

    public static void initialize(int[] arr, int n){
        for(int i = 1; i < n; i++){
            arr[i] = i;
        }
    }

    public static void union(int[] arr, int n, int a , int b) {
        int temp = arr[a];
        for(int i = 0 ; i < n ; i++){
            if(arr[i] == temp) {
                arr[i] = arr[b];
            }
        }
    }
}
