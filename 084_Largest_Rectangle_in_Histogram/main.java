public class Solution {
    public int largestRectangleArea(int[] height) {
        if(height == null || height.length == 0) return -1;
        Deque<Integer> stack = new ArrayDeque<>(); // Store the index
        int max = 0;
        for(int i = 0; i <= height.length; i++) {
            // Each element will be push once and poll once
            // 1. Check whether this element can be pushed into the stack
            int curVal = i == height.length ? 0 : height[i]; // ?????
            // set curVal to the min value to guarantee the last element to be put into stack
            
            while(!stack.isEmpty() && height[stack.peekLast()] >= curVal) {
                int curHeight = height[stack.pollLast()];
                //Find Left and Right Boundary from current rectangle
                int leftBound = stack.isEmpty() ? 0 : stack.peekLast() + 1; // number in stack means the smaller boundary
                int rightBound = i;
                max = Math.max(max, curHeight * (rightBound - leftBound));
            }
            // 2. Push the element into the stack 
            stack.offerLast(i);
        }
        return max;
}
}