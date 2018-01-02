public class BSTIterator {
    
    private Deque<TreeNode> stack;
    private TreeNode cur;
    
    public BSTIterator(TreeNode root) {
       stack = new ArrayDeque<>();
        cur = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty() || cur != null;
    }

    /** @return the next smallest number */
    public int next() {
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.offerLast(cur);
                cur = cur.left;
            } else {
                cur = stack.peekLast().right;
                break;
            }
        }
        TreeNode node = stack.pollLast();
        return node.val;
    }
}