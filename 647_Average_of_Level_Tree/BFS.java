while(!queue.isEmpty()) {
          
  int size = queue.size();
  List<Integer> list = new ArrayList<>();
  
  for(int i = 0; i < size ; i++) {
      TreeNode cur = queue.pollFirst();
      list.add(cur.val);
      if(cur.left != null) queue.offerLast(cur.left);
      if(cur.right != null) queue.offerLast(cur.right);
  }            
  
  res.add(0, list);
}
return res;