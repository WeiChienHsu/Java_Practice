import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = "";
        if(areStringValid(text) == -1) {
            System.out.println("Success");
        } else {
            System.out.println(areStringValid(text));
        }
    }


    private static int areStringValid(String text) {

        Deque<Bracket> opening_brackets_stack;
        opening_brackets_stack = new ArrayDeque<Bracket>();

        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                Bracket left = new Bracket(next, position);
                opening_brackets_stack.offerLast(left);
            }

            if (next == ')' || next == ']' || next == '}') {
                if(opening_brackets_stack.isEmpty()) {
                    return position + 1;
                }

                Bracket right = opening_brackets_stack.pollLast();
                if((next == ')' && right.type != '(') ||
                   (next == ']' && right.type != '[') ||
                   (next == '}' && right.type != '{')) {
                    return position + 1;

                }
            }
        }

        int pos = -1;
        while (!opening_brackets_stack.isEmpty()){
            pos = opening_brackets_stack.pollLast().position + 1;
        }
            return pos;
    }
}

