import java.util.ArrayDeque;
import java.util.Deque;

public class basicCalculator {

    public static void main(String[] args) {
        String s = "   (  3 )" ;
        System.out.println(calculate(s));
    }

    public static int calculate(String s) {

        Deque<Integer> numStack =  new ArrayDeque<>();
        Deque<String> operStack = new ArrayDeque<>();
        s = s.replaceAll("\\s+", "");
        String[] strs = s.split("");

        if(strs.length == 1) return Integer.parseInt(strs[0]);

        for (int i = 0; i < strs.length; i++) {
            if(strs[i].equals("+") || strs[i].equals("-") || strs[i].equals("(")) {
                operStack.offerLast(strs[i]);
            } else if(strs[i].equals(")")){

                while(!operStack.peekLast().equals("(")) {
                    String curOper = operStack.pollLast();
                    if(curOper.equals("+")) {
                        int curNum1 = numStack.pollLast();
                        int curNum2 = numStack.pollLast();
                        numStack.offerLast(curNum1 + curNum2);
                    } else if(curOper.equals("-")) {
                        int curNum1 = numStack.pollLast();
                        int curNum2 = numStack.pollLast();
                        numStack.offerLast(curNum2 - curNum1);
                    }
                }
                operStack.pollLast();

                }
               else {
                numStack.offerLast(Integer.parseInt(strs[i]));
            }
        }

        while(!operStack.isEmpty()){
            String curOper = operStack.pollLast();
            if(curOper.equals("+")) {
                int curNum1 = numStack.pollLast();
                int curNum2 = numStack.pollLast();
                numStack.offerLast(curNum1 + curNum2);
            } else if(curOper.equals("-")) {
                int curNum1 = numStack.pollLast();
                int curNum2 = numStack.pollLast();
                numStack.offerLast(curNum2 - curNum1);
            }
        }

        return numStack.pollLast();
    }
}
