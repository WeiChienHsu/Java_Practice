class MyQueue {

  Deque<Integer> stack1;
  Deque<Integer> stack2;
  
  /** Initialize your data structure here. */
  public MyQueue() {
      stack1 = new ArrayDeque<>();
      stack2 = new ArrayDeque<>();
  }
  
  /** Push element x to the back of queue. */
  public void push(int x) {
      stack1.offerFirst(x);
  }
  
  /** Removes the element from in front of queue and returns that element. */
  public int pop() {
      while(!stack1.isEmpty()) {
          stack2.offerFirst(stack1.pollFirst());
      }
      
      int peek = stack2.pollFirst();
      
      while(!stack2.isEmpty()) {
          stack1.offerFirst(stack2.pollFirst());
      }
      
      return peek;
  }
  
  /** Get the front element. */
  public int peek() {
      
      while(!stack1.isEmpty()) {
          stack2.offerFirst(stack1.pollFirst());
      }
      
      int peek = stack2.peek();
      
      while(!stack2.isEmpty()) {
          stack1.offerFirst(stack2.pollFirst());
      }
      
      return peek;
      
  }
  
  /** Returns whether the queue is empty. */
  public boolean empty() {
      return stack1.isEmpty();
  }
}

/**
* Your MyQueue object will be instantiated and called as such:
* MyQueue obj = new MyQueue();
* obj.push(x);
* int param_2 = obj.pop();
* int param_3 = obj.peek();
* boolean param_4 = obj.empty();
*/