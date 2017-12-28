import java.util.ArrayDeque;
import java.util.Deque;

public class validPare {

    public static void main(String[] args) {
        String s = "(())((())()()(*)(*()(())())())()()((()())((()))(*";
        System.out.println(checkValidString(s));
    }

    public static boolean checkValidString(String s) {
        Deque<Character> pStack = new ArrayDeque<>();
        Deque<Character> sStack = new ArrayDeque<>();

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                pStack.offerLast(s.charAt(i));
            } else if (s.charAt(i) == '*') {
                sStack.offerLast(s.charAt(i));
            } else { // ')'
                if(!pStack.isEmpty()) {
                    pStack.pollLast();
                } else if (!sStack.isEmpty() && pStack.isEmpty()){
                    sStack.pollLast();
                } else {
                    return false;
                }
            }
        }

        while(!pStack.isEmpty()) {
            if(sStack.isEmpty()) {
                return false;
            }
            sStack.pollLast();
            pStack.pollLast();
        }

        return true;
    }
}
