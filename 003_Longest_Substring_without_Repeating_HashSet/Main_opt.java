    mport java.util.*;

public class TestHashSet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(lengthOfLongestSubstring(s));
    }
    
    private static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int start = 0;
        int end = 0;
        // Add an array to check if there is a repeated number
        int[] map = new int[128];
        int max = Integer.MIN_VALUE;

        // Separate the String into Characters array
        char[] sToChar = s.toCharArray();

            while(end < sToChar.length) {

                // > 0 meant there is a same number

                while(map[sToChar[end]] > 0) {
                    map[sToChar[start++]]--;
                }

                map[sToChar[end++]]++;
                max = Math.max(max, end - start);
            }
        return max;
    }
}