import java.util.*;

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

        // Save the Character - not repeat
        Deque<Character> queue = new ArrayDeque<>();
        // HashSet to check if there is a repeat number
        Set<Character> set = new HashSet<>();
        int max = Integer.MIN_VALUE;

        // Separate the String into Characters
        for(char ch: s.toCharArray()) {

            while(!set.add(ch)) {
                // If there is a repeat number, remove it in both Deque and Set
                set.remove(queue.pollFirst());
            }
            // add number in queue and HashSet
            queue.offerLast(ch);
            set.add(ch);

            // Count the length and compare to the existed max number
            max = Math.max(max, queue.size());
        }

        return max;
    }

}
