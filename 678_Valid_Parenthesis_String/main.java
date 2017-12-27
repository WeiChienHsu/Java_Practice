class Solution {
    public boolean checkValidString(String s) {
        Deque<Integer> pStack = new ArrayDeque<>();
        Deque<Integer> sStack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                pStack.offerLast(i); // Offer into an index to know the position between "(" & "*"
            } else if (s.charAt(i) == '*') {
                sStack.offerLast(i);
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
        
        // **((  is useless
        
        while(!pStack.isEmpty() && !sStack.isEmpty()) {
            if(pStack.pollLast() > sStack.pollLast()) {
                return false;
            }
        }
        return pStack.isEmpty();
    }
}