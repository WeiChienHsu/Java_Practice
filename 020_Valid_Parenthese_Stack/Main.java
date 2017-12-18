class Solution {
    public boolean isValid(String s) {
        if(s ==  null || s.length() == 0) {
            return true;
        }
        
        char[] str = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        
        for (char ch : str) {
            // Meet "{", "(", "["
            if(ch == '{' || ch == '(' || ch == '[' ) {
                stack.offerLast(ch);
            } else { // Meet "}", "]", ")"
                if(stack.isEmpty()){
                    return false;
                }
                char left = stack.pollLast();
                if( (ch == ')' && left == '(') ||
                    (ch == '}' && left == '{') ||
                    (ch == ']' && left == '[')) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
        
    }
}