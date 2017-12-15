import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many number of Strings you want to enter ?");
        int inputNumber = scanner.nextInt();

        System.out.println("Please input " + inputNumber + " String separately");
        int[] input = new int[inputNumber];

        for (int i = 0; i < inputNumber; i++) {
            input[i] = scanner.nextInt();
        }

        System.out.println(rub(input));

    }

//=========================================================

    public static int rub(int[] nums) {
        // Remove Corner Value
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        // Compare Two conditions -> do not rub the LAST / rub the LAST without first
        return Math.max(subRob(nums, 0, nums.length - 2), subRob(nums, 1, nums.length - 1));
    }

        private static int subRob(int[] n, int start, int last){

        int prev = 0;
        int current = n[start];

        for(int i = start + 1; i <= last; i++) {
            int next = Math.max(current, prev + n[i]);
            prev = current;
            current = next;
        }

        return current;
    }
}
