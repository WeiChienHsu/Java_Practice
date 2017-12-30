import java.util.ArrayDeque;
import java.util.Deque;

public class removeK {
    public static void main(String[] args) {

        String s = "10";
        int k = 3;
        System.out.println(removeKdigits(s,k));
    }
    public static String removeKdigits(String num, int k) {
        if(num.length() == 0) return "0";

        Deque<Character> stack = new ArrayDeque<>();

        // peek > next number : replace peek by polling it out
        // peek <= next number : offer into stack
        // count the rest K and pointer i

        int i = 0;
        while (i < num.length()) {
            while (k > 0 && !stack.isEmpty() && stack.peekLast() > num.charAt(i)) {
                stack.pollLast();
                k--;
            }

            stack.offerLast(num.charAt(i));
            i++;
        }

        while(k > 0) {
            stack.pollLast();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }

        sb.reverse();

        while(sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.length() == 0? "0" : sb.toString();

    }

}
