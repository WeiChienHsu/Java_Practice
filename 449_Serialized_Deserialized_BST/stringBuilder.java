public class Codec {
    
  public static final String MARK = "#";
  public static final String NULL = "n";

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
      StringBuilder sb = new StringBuilder();
      serialize(root, sb);
      return sb.toString();
  }
  
  public void serialize(TreeNode root, StringBuilder sb){
      if(root == null) {
          sb.append(NULL + MARK);
      } else{
          sb.append(root.val + MARK);
          serialize(root.left, sb);
          serialize(root.right, sb); 
      }
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
      // Seperate String into Integer and put them into a Queue
      Deque<String> queue = new ArrayDeque<>();
      String[] strings = data.split(MARK);
      for(String s : strings){
          queue.offerLast(s);
      }
      return buildTree(queue);
  }
  
  public TreeNode buildTree(Deque<String> queue) {
      String val = queue.pollFirst();
      if(val.equals(NULL)){
          return null;
      }
      
      TreeNode root = new TreeNode(Integer.valueOf(val));
      root.left = buildTree(queue);
      root.right = buildTree(queue);
      return root;
  }
}