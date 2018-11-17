import java.util.ArrayDeque;
import java.util.Deque;

public class Solution1 {
    public static void main(String[] args) {
        String str = ")(()()))())";
        String result = removeInvalid(str);

        System.out.println(result);
    }

    public static String removeInvalid(String str) {
        StringBuilder sb = new StringBuilder(str);
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < sb.length(); i++) {
            if(sb.charAt(i) == '(') {
                stack.offerFirst(i);
            } else {
                if(!stack.isEmpty()) {
                    stack.pollFirst();
                } else {
                    /* Remove ')' from StringBuilder */
                    sb.deleteCharAt(i);
                    i--;
                }
            }
        }

        return sb.toString();
    }
}
