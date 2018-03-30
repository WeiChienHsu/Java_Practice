import java.util.ArrayDeque;
import java.util.Deque;

public class test {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("a");
        sb.append('b');
        System.out.println(sb.toString());
        String s = "abc";
        char c = 'a';
        s.s

        System.out.println(Character.isDigit(c));
        System.out.println(s.charAt(0));
    }
}
    public static StringBuilder decode(String s) {
        // Stack is deprecated so using double-ended Q
        Deque<Integer> multipliers = new ArrayDeque<>();
        Deque<StringBuilder> result = new ArrayDeque<>();
        result.push(new StringBuilder());
        int multiplier = 0;

        // Would be nice to use an 'enhanced' for loop, but don't want
        // the expense of converting the String to an array (ie toCharArray)
        // for (char ch : s.toCharArray()) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                multiplier = multiplier * 10 + Character.getNumericValue(ch);
            } else if (ch == '[') {
                multipliers.push(multiplier);
                multiplier = 0; //reset
                result.push(new StringBuilder());
            } else if (ch == ']') {
                StringBuilder str2Multiply = result.pop();
                int times = multipliers.pop();
                StringBuilder multipliedStr = new StringBuilder();
                for (int j = 0; j < times; j++) {
                    multipliedStr.append(str2Multiply);
                }
                result.push(result.pop().append(multipliedStr));
            } else {
                result.push(result.pop().append(ch));
            }
        }

        return result.pop();
    }