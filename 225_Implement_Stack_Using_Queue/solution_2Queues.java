class MyStack {
    
  Deque<Integer> queue1;
  Deque<Integer> queue2;

  /** Initialize your data structure here. */
  public MyStack() {
      queue1 = new ArrayDeque<>();
      queue2 = new ArrayDeque<>();
  }
  
  /** Push element x onto stack. */
  public void push(int x) {
      queue1.offerLast(x);
  }
  
  /** Removes the element on top of the stack and returns that element. */
  public int pop() {
      Deque<Integer> current = queue1.isEmpty() ? queue2 : queue1;
      int size = 0;
      int top = 0;
      
      while(!queue1.isEmpty()) {
          top = current.pollFirst();
          queue2.offerLast(top);
          size ++;
      }
      
      size --;
      
      for(int i = 0; i < size; i++) {
          queue1.offerLast(queue2.pollFirst());
      }
      
      queue2.pollFirst(); /* Clear queue2 */
      
      return top;
  }
  
  /** Get the top element. */
  public int top() {
  
      int top = 0;
      
      while(!queue1.isEmpty()) {
          top = queue1.pollFirst();
          queue2.offerLast(top);
      }
      
      while(!queue2.isEmpty()) {
          queue1.offerLast(queue2.pollFirst());
      }
      
      return top;
  }
  
  /** Returns whether the stack is empty. */
  public boolean empty() {
      return queue1.isEmpty();
  }
}

/**
* Your MyStack object will be instantiated and called as such:
* MyStack obj = new MyStack();
* obj.push(x);
* int param_2 = obj.pop();
* int param_3 = obj.top();
* boolean param_4 = obj.empty();
*/