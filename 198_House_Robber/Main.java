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

        System.out.println(rob(input));

    }

//=========================================================

    public static int rob(int[] nums) {
        // Remove Corner Value
        if(nums == null || nums.length == 0) {
            return 0;
        } else if(nums.length == 1){
            return nums[0];
        }

        int prev = 0;
        int current = nums[0];

        for(int i = 1; i < nums.length; i++) {
            int next = Math.max(current, prev + nums[i]);
            prev = current;
            current = next;
        }

        return current;
    }
}
