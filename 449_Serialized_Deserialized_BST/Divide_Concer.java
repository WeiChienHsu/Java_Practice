public class Codec {

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
      if (root == null) {
          return "#!";
      }
      String res = root.val + "!";
      res += serialize(root.left);
      res += serialize(root.right);
      return res;
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
      Deque<String> queue = new ArrayDeque<>();
      String[] strings = data.split("!");
      for(String s : strings){
          queue.offerLast(s);
      }
      return recordNodes(queue);
  }
  
  public TreeNode recordNodes(Deque<String> queue){
      String val = queue.pollFirst();
      if(val.equals("#")){
          return null;
      }
      
      TreeNode head = new TreeNode(Integer.valueOf(val));
      head.left = recordNodes(queue);
      head.right = recordNodes(queue);
      return head;
  }
}