import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
//        Set<Integer> set = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13));
//        System.out.println(set.contains(4));

//        String newString = "apple";
//
//        System.out.println(newString.charAt());

        List<Integer> test = new ArrayList<>();
        test.add(0, 20);
    }

    public static int getPrime(int num) {
        int count = 0;
        int currentNumber = 2;
        while(count < num) {
            if(isPrime(currentNumber)) {
                System.out.print(currentNumber);
                System.out.print(" ");
                count++;
            }
            currentNumber++;
        }
        return count;
    }

    public static boolean isPrime(int num) {
        if(num == 1) return false;
        for(int i = 2; i < num; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }

    public static int printResult(int[] result) {
        return result[0];
    }

    public static int countTwoPowerNumber(int num) {
        int ten = 1;
        for(int i = 0; i < num; i++) {
            ten *= 10;
        }

        int count = 0;
        int two = 1;
        while(two < ten) {
            two *= 2;
            count++;
        }
        System.out.println(two);
        return count;
    }
}
