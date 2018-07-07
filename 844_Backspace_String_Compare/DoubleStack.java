class Solution {
  public boolean backspaceCompare(String S, String T) {
      Deque<Character> stackS = new ArrayDeque<>();
      Deque<Character> stackT = new ArrayDeque<>();
      
      for(int i = 0; i < S.length(); i++) {
          if(S.charAt(i) == '#') {
              if(!stackS.isEmpty()) {
                  stackS.pollLast();
              }
              continue;
          }
          stackS.offerLast(S.charAt(i));
      }
      
      for(int i = 0; i < T.length(); i++) {
          if(T.charAt(i) == '#') {
              if(!stackT.isEmpty()) {
                  stackT.pollLast();
              }
              continue;
          }
          stackT.offerLast(T.charAt(i));
      }
      
      /* Compare the contents in both Stack */
      while(!stackS.isEmpty() && !stackT.isEmpty()) {
          if(stackS.pollLast() != stackT.pollLast()) return false;
      }
      
      return stackS.isEmpty() && stackT.isEmpty();
      
  }
}