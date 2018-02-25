import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        // Main function and Test Cases
        int testCase1 = 3;
        int testCase2 = 10;
        int testCase3 = 20;
        System.out.println(crazy_nums(testCase1));
        System.out.println(crazy_nums(testCase2));
        System.out.println(crazy_nums(testCase3));
    }
    // Crazy numbers method
    private static List<Integer> crazy_nums(int num){
        // Used a List to record each integer
        List<Integer> array = new ArrayList<>();
        for(int n = 0; n < num; n++) {
            if(n % 3 == 0 && n % 5 == 0) {
                continue;
            }

            if(n % 3 == 0 || n % 5 == 0) {
                array.add(n);
            }
        }
        return array;
    }
}
