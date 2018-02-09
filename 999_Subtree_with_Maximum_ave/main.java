public class Solutinon {
  privae class ResultType{
    public int sum, size;
    public ResultType(int sum, int size) {
      this.sum = sum;
      this.size = size;
    }
  }

  private TreeNode maxAveSubtree = null;
  private ResultType subtreeResult = null;

  public TreeNode finSubtree2(TreeNode root) {
    helper(root);
    return maxAveSubtree;
  }

  public ResultType helper(TreeNode root) {
    if(root == null) {
      return new ResultType(0,0);
    }

    ResultType left = helper(root.left);
    ResultType right = helper(root.right);

    // Result -> root
    ResultType rootResult = new ResultType(
      left.sum + right.sum + root.val,
      left.size + right.size + 1
      );
    
      if(maxAveSubtree == null ||
      //subtreeResult.sum / subtreeResult.size  < rootResult.sum / rootResult.size
        subtreeResult.sum * rootResult.size < rootResult.sum * subtreeResult.size
      ) {
        maxAveSubtree = root;
        subtreeResult = root;
      }
      return rootResult;
  }
}