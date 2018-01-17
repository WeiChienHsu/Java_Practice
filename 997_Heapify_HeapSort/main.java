public class HeapSort {

  public static void main(String[] args) {
      int[] arr = {12,11,13,5,3,6};
      int n = arr.length;

      HeapSort ob = new HeapSort();
      ob.sort(arr);

      System.out.println("Sort array is: ");
      printArray(arr);
  }

  private void sort(int[] arr) {
      int n = arr.length;

      // Build heap(rearrange array)
      for(int i = 0; i < n; i++) {
          heapify(arr, n, i);
      }

      for(int i = n -1; i >= 0; i--) {
          int temp = arr[0];
          arr[0] = arr[i];
          arr[i] = temp;

          heapify(arr, i, 0);
      }
  }


  // Call max heapify on the reduced haep
  // an index in arr[] , n is the size of heap , root with node i
  private static void heapify(int[] arr, int n, int i) {
      int largest = i; // Initialize largest as root
      int l = 2*i + 1;
      int r = 2*i + 2;

      // If left child is larger than root
      if(l < n && arr[l] > arr[largest]) {
          largest = l;
      }

      // If right child is larger than root
      if(r < n && arr[r] > arr[largest]) {
          largest = r;
      }

      // If largest is not root
      if(largest !=  i) {
          int swap = arr[i];
          arr[i] = arr[largest];
          arr[largest] = swap;
          heapify(arr, n, largest);
      }
  }

  private static void printArray(int[] arr) {
      int n = arr.length;
      for(int i = 0; i < n; i++) {
          System.out.println(arr[i] + " ");
      }
      System.out.println();
  }

}
