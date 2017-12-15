import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many number of Strings you want to enter ?");
        int inputNumber = scanner.nextInt();

        System.out.println("Please input " + inputNumber + " String separately");
        ArrayList<String> input = new ArrayList<>();

        for (int i = 0;  i < inputNumber; i++){
            input.add(scanner.next());
        }

        System.out.println(evalRPN(input));
    }

//=========================================================

    public static int evalRPN(ArrayList<String> tokens) {
        // Check if the input can be count
        if(tokens == null || tokens.size() == 0){
            return 0;
        }

        Deque<Integer> stack = new ArrayDeque<Integer>();
        // There is only be a number in the Deque

        // Pointer, from the first number to the end
        for (int i = 0; i < tokens.size(); i++) {
            switch (tokens.get(i)){
                // Meet Operator, Pull two numbers and count it, Save into Deque
                case "+":
                    stack.offerLast(stack.pollLast() + stack.pollLast());
                    break;
                case "-":
                    int numTwo = stack.pollLast();
                    int numOne = stack.pollLast();
                    stack.offerLast(numOne - numTwo);
                    break;
                case "*":
                    stack.offerLast(stack.pollLast() * stack.pollLast());
                    break;
                case "/":
                    int numTwo1 = stack.pollLast();
                    int numOne1 = stack.pollLast();
                    stack.offerLast(numOne1 / numTwo1);
                    break;
                // Meet the number Save into Deque
                default:
                    stack.offerLast(Integer.parseInt(tokens.get(i)));
                    break;
            }

        }
        // Pull out the last number
        return stack.pollLast();
    }
}
