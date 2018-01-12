public class bubbleSort {

  public static void main(String[] args) {
      int[] nums = {5,8,4,2,3,1};
      sort(nums);
      for(int num : nums) {
          System.out.println(num);
      }
  }

  private static void sort(int[] nums) {
      for(int i = 1; i < nums.length; i++) {
          for(int j = 0; j < i; j++ ) {
              if(nums[i] > nums[j]) {
                  break;
              }
              swap(nums, i, j);
          }
      }
  }

  private static void swap(int[] nums, int i, int j) {
      int temp = nums[j];
      nums[j] = nums[i];
      nums[i] = temp;
  }
}
