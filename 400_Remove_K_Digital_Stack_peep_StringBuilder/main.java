class Solution {
    public String removeKdigits(String num, int k) {
        if(k == num.length()) {
            return "0";
        }

        Stack<Character> stack = new Stack<>();
        // pointer to scan the string
        int i = 0;
        while(i < num.length()) {
            // If the next digit less then the previous one, pop out the pre and Offer the new one
            while(k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                // Remove K number
                k -- ;
            }

            stack.push(num.charAt(i));
            // Move to the next number
            i ++;
        }

        // Deal with the corner case like "1111"
            while (k > 0) {
                stack.pop();
                k --;
            }
        // Construct the number from stack to String
            StringBuilder sb = new StringBuilder();
             while(!stack.isEmpty()) {
                sb.append(stack.pop());
            }

            sb.reverse();

             // Remove all the 0 at head ex. 00400

        while(sb.length()>1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.length() == 0? "0" : sb.toString();
    }
}