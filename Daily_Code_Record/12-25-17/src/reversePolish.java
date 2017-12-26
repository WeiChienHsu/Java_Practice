import java.util.ArrayDeque;
import java.util.Deque;

public class reversePolish {
    public static void main(String[] args) {
        String[] tokens = {"0","3","/"};
        System.out.println(evalRPN(tokens));

    }

    private static int evalRPN(String[] tokens) {

        Deque<Integer> stack = new ArrayDeque<>();// number stack
        int res = 0;

        for(int i = 0; i < tokens.length; i++) {
            if(tokens[i].equals("+")) {
                int num1 = stack.pollLast();
                int num2 = stack.pollLast();
                stack.offerLast(num1 + num2);
            } else if (tokens[i].equals("-")) {
                int num1 = stack.pollLast();
                int num2 = stack.pollLast();
                stack.offerLast(num2 - num1);
            } else if (tokens[i].equals("*")) {
                int num1 = stack.pollLast();
                int num2 = stack.pollLast();
                stack.offerLast(num1 * num2);
            } else if (tokens[i].equals("/")) {
                int num1 = stack.pollLast();
                int num2 = stack.pollLast();
                stack.offerLast(num2 / num1);
            } else { //  number
                int num = Integer.parseInt(tokens[i]);
                stack.offerLast(num);
            }
        }

        return stack.pollLast();
    }
}
