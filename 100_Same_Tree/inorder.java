class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        List<Integer> tree1 = new ArrayList<>();
        List<Integer> tree2 = new ArrayList<>();
        treeToList(p, tree1);
        treeToList(q, tree2);
        return tree1.equals(tree2)? true : false;
    }
    
    public void treeToList(TreeNode root, List<Integer> list){
        if(root == null){
           list.add(0);
            return;
        } 
        list.add(root.val);
        treeToList(root.left, list);
        treeToList(root.right, list);   
    }
}